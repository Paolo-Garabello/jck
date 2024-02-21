CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY NOT NULL,
    username VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
    id INTEGER PRIMARY KEY NOT NULL,
    text TEXT NOT NULL,
    sender UNSIGNED INT NOT NULL, 
    recipiet UNSIGNED INT NOT NULL,

    FOREIGN KEY (sender) REFERENCES users(id)
    FOREIGN KEY (recipiet) REFERENCES users(id)
)