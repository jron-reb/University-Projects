package resources

import (
	"encoding/json"
	"net/http"
	"tracks/repository"

	"github.com/gorilla/mux"
)

func updateTrack(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id := vars["id"]
	var track repository.Track
	if err := json.NewDecoder(r.Body).Decode(&track); err == nil {
		if id == track.Id {
			if n := repository.Update(track); n > 0 {
				w.WriteHeader(204) /* No Content */
			} else if n := repository.Insert(track); n > 0 {
				w.WriteHeader(201) /* Created */
			} else {
				w.WriteHeader(500) /* Internal Server Error */
			}
		} else {
			w.WriteHeader(400) /* Bad Request */
		}
	} else {
		w.WriteHeader(400) /* Bad Request */
	}
}

func readTrack(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id := vars["id"]
	if track, n := repository.Read(id); n > 0 {
		w.WriteHeader(200) /* OK */
		json.NewEncoder(w).Encode(track)
	} else if n == 0 {
		w.WriteHeader(404) /* Not Found */
	} else {
		w.WriteHeader(500) /* Internal Server Error */
	}
}

func deleteTrack(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id := vars["id"]
	if n := repository.Delete(id); n > 0 {
		w.WriteHeader(204) /* OK */
	} else if n == 0 {
		w.WriteHeader(404) /* Not Found */
	} else {
		w.WriteHeader(500) /* Internal Server Error */
	}
}

func listTracks(w http.ResponseWriter, r *http.Request) {
	if tracks, n := repository.Readall(); n > 0 {
		w.WriteHeader(200) /* OK */
		json.NewEncoder(w).Encode(tracks)
	} else {
		w.WriteHeader(500) /* Internal Server Error */
	}
}

func Router() http.Handler {
	r := mux.NewRouter()

	r.HandleFunc("/tracks/{id}", updateTrack).Methods("PUT")

	r.HandleFunc("/tracks/{id}", readTrack).Methods("GET")

	r.HandleFunc("/tracks", listTracks).Methods("GET")

	r.HandleFunc("/tracks/{id}", deleteTrack).Methods("DELETE")

	return r
}
