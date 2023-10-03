package resources

import (
	"bytes"
	"encoding/json"
	"io/ioutil"
	"net/http"
	"strings"

	"github.com/gorilla/mux"
)

func search(w http.ResponseWriter, r *http.Request) {
	var audio Audio

	err := json.NewDecoder(r.Body).Decode(&audio)
	if err != nil {
		w.WriteHeader(400)
		return
	}

	if audio.Audio == "" {
		w.WriteHeader(400)
		return
	}

	postBody, _ := json.Marshal(audio)
	responseBody := bytes.NewBuffer(postBody)

	responseSearch, err := http.Post("http://localhost:3001/search", "application/json", responseBody)
	if err != nil {
		w.WriteHeader(500)
		return
	}
	defer responseSearch.Body.Close()

	body, err := ioutil.ReadAll(responseSearch.Body)
	if err != nil {
		w.WriteHeader(500)
		return
	}
	//format the output of search so that the ID can be passed to tracks
	auddId := string(body)
	auddId = strings.TrimSuffix(auddId, "\n")
	auddId = strings.TrimPrefix(auddId, "{\"Id\":")
	auddId = strings.Replace(auddId, "\"", "", -1)
	auddId = strings.Replace(auddId, "}", "", -1)
	auddId = strings.Replace(auddId, " ", "+", -2)
	audio.Id = auddId

	/*** FROM HERE DOWNWARDS IT WORKS NEMAS PROBLEMAS IT HAS TO DO WITH THE FUCKING SEARCH THINGIE ***/
	audioJSON, err := json.Marshal(audio)
	if err != nil {
		w.WriteHeader(500)
		return
	}

	url_tracks := "http://localhost:3000/tracks/" + audio.Id
	request, err := http.NewRequest("GET", url_tracks, bytes.NewBuffer(audioJSON))
	if err != nil {
		w.WriteHeader(500)
		return
	}

	client := &http.Client{}
	responseTracks, err := client.Do(request)
	if err != nil {
		w.WriteHeader(500)
		return
	}
	defer responseTracks.Body.Close()
	if responseTracks.StatusCode == 200 {
		w.WriteHeader(200)

		responseBody2, err := ioutil.ReadAll(responseTracks.Body)
		if err != nil {
			w.WriteHeader(500)
			return
		}

		tracksResponse := string(responseBody2)

		//So what we tryna do here is get the tracksresponse into a JSON (so that it outputs correctly)
		err = json.Unmarshal([]byte(tracksResponse), &audio)
		if err != nil {
			//handle
		}

		response := Response{
			Audio: audio.Audio,
		}
		json.NewEncoder(w).Encode(response)

	} else {
		w.WriteHeader(responseTracks.StatusCode)
	}
}

func Router() http.Handler {
	r := mux.NewRouter()
	r.HandleFunc("/cooltown", search).Methods("POST")
	return r
}
