package main

import (
	"log"
	"net/http"
	"tracks/repository"
	"tracks/resources"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	repository.Initdb()
	repository.Create()
	repository.Clear()
	log.Fatal(http.ListenAndServe(":3000", resources.Router()))
}
