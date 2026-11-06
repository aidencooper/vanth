CREATE TABLE IF NOT EXISTS libraries (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    type TEXT NOT NULL CHECK (type IN ('MOVIES', 'SHOWS'))
);

CREATE TABLE IF NOT EXISTS library_folders (
    library_id INTEGER NOT NULL REFERENCES libraries (id) ON DELETE CASCADE,
    path TEXT NOT NULL,
    PRIMARY KEY (library_id, path)
);