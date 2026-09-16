DROP TABLE IF EXISTS asistente;
DROP TABLE IF EXISTS reunion;
DROP TABLE IF EXISTS persona;

CREATE TABLE reunion (
                         id BIGSERIAL PRIMARY KEY,
                         asunto VARCHAR(128) NOT NULL,
                         fecha TIMESTAMP NOT NULL
);

CREATE TABLE persona (
                         id BIGSERIAL PRIMARY KEY,
                         nombre VARCHAR(32) NOT NULL,
                         apellidos VARCHAR(32) NOT NULL
);

CREATE TABLE asistente (
                           reunion_id BIGINT,
                           persona_id BIGINT,
                           PRIMARY KEY (reunion_id, persona_id),
                           FOREIGN KEY (reunion_id) REFERENCES reunion(id),
                           FOREIGN KEY (persona_id) REFERENCES persona(id)
);