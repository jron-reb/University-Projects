package main

import (
	"cooltown/resources"
	"log"
	"net/http"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	log.Fatal(http.ListenAndServe(":3002", resources.Router()))
}
