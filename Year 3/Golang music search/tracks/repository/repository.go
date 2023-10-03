package repository

import (
	"database/sql"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

type Repository struct {
	DB *sql.DB
}

// type Track struct {
// 	Id    string
// 	Audio string
// }

var repo Repository

func Initdb() {
	if db, err := sql.Open("sqlite3", "tracks.db"); err == nil {
		repo = Repository{DB: db}
	} else {
		log.Fatal("Database initialisation")
	}
}

func Create() int {
	const sql = "CREATE TABLE IF NOT EXISTS Tracks" +
		"(Id TEXT PRIMARY KEY, Audio TEXT)"
	if _, err := repo.DB.Exec(sql); err == nil {
		return 0
	} else {
		return -1
	}
}

func Clear() int {
	const sql = "DELETE FROM Tracks"
	if _, err := repo.DB.Exec(sql); err == nil {
		return 0
	} else {
		return -1
	}
}

func Update(track Track) int64 {
	const sql = "UPDATE Tracks SET Audio = ? WHERE id = ?"
	if stmt, err := repo.DB.Prepare(sql); err == nil {
		defer stmt.Close()
		if res, err := stmt.Exec(track.Audio, track.Id); err == nil {
			if n, err := res.RowsAffected(); err == nil {
				return n
			}
		}
	}
	return -1
}

func Insert(track Track) int64 {
	const sql = "INSERT INTO Tracks (Id, Audio) VALUES (?, ?)"
	if stmt, err := repo.DB.Prepare(sql); err == nil {
		defer stmt.Close()
		if res, err := stmt.Exec(track.Id, track.Audio); err == nil {
			if n, err := res.RowsAffected(); err == nil {
				return n
			}
		}
	}
	return -1
}

func Read(id string) (Track, int64) {
	const sql = "SELECT * FROM Tracks WHERE Id = ?"
	if stmt, err := repo.DB.Prepare(sql); err == nil {
		defer stmt.Close()
		var track Track
		row := stmt.QueryRow(id)
		if err := row.Scan(&track.Id, &track.Audio); err == nil {
			return track, 1
		} else {
			return Track{}, 0
		}
	}
	return Track{}, -1
}

func Readall() ([]string, int64) {
	rows, err := repo.DB.Query("SELECT * FROM Tracks")
	if err != nil {
		empty := make([]string, 0)
		return empty, -1
	}
	defer rows.Close()

	trackIds := make([]string, 0)

	for rows.Next() {
		var track Track
		if err := rows.Scan(&track.Id, &track.Audio); err != nil {
			empty := make([]string, 0)
			return empty, -1
		}
		trackIds = append(trackIds, track.Id)

		if err = rows.Err(); err != nil {
			empty := make([]string, 0)
			return empty, -1
		}
	}
	return trackIds, 1
}

func Delete(id string) int64 {
	const sql = "DELETE FROM Tracks WHERE ID = ?"
	if stmt, err := repo.DB.Prepare(sql); err == nil {
		defer stmt.Close()
		if res, err := stmt.Exec(id); err == nil {
			if n, err := res.RowsAffected(); err == nil {
				return n
			}
		}
	}
	return -1
}
