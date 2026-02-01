DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS event_participants;

CREATE TABLE events(
    id SERIAL,
    name VARCHAR(255) UNIQUE NOT NULL,
    address VARCHAR(255) NOT NULL,
    date_time timestamp NOT NULL,
    description TEXT NOT NULL,
    max_participants INT NOT NULL,
    is_surstromming BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE event_participants(
    id VARCHAR(11) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    event_id INT NOT NULL,
    PRIMARY KEY(event_id, id)
);