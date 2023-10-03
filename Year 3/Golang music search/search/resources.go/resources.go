package resources

import (
	"bytes"
	"encoding/json"
	"io/ioutil"
	"net/http"

	"github.com/gorilla/mux"
)

const (
	KEY = ``
)

func searchSong(w http.ResponseWriter, r *http.Request) {
	var api_data API_data
	api_data.API_token = KEY

	if err := json.NewDecoder(r.Body).Decode(&api_data); err != nil {
		w.WriteHeader(400)
		return
	}
	if api_data.Audio == "" {
		w.WriteHeader(400)
		return
	}

	finalJSON, err := json.Marshal(api_data)
	if err != nil {
		w.WriteHeader(500)
		return
	}

	resp, err := http.Post("https://api.audd.io/recognize", "application/json", bytes.NewBuffer(finalJSON))
	if err != nil {
		w.WriteHeader(400)
		return
	}
	defer resp.Body.Close()

	responseBody, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		w.WriteHeader(400)
		return
	}

	var responseMap map[string]interface{}
	err = json.Unmarshal(responseBody, &responseMap)
	if err != nil {
		w.WriteHeader(500)
		return
	}

	if result, ok := responseMap["result"].(map[string]interface{}); ok {
		if title, ok := result["title"].(string); ok {
			w.WriteHeader(200)
			response := Response{
				ID: title,
			}
			json.NewEncoder(w).Encode(response)
		} else {
			w.WriteHeader(500)
		}
	} else { // if the map interface doesn't hold the title then return a 400
		w.WriteHeader(404)
	}
}

func Router() http.Handler {
	r := mux.NewRouter()
	r.HandleFunc("/search", searchSong).Methods("POST")
	return r
}
