CREATE TABLE workouts (
    id          BIGSERIAL PRIMARY KEY,
    ficha_id    BIGINT NOT NULL,
    nome        VARCHAR(50) NOT NULL,
    ordem       INTEGER,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_ficha
      FOREIGN KEY(ficha_id)
          REFERENCES fichas(id)
          ON DELETE CASCADE
);