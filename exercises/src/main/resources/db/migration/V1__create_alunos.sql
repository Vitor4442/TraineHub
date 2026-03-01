CREATE TABLE students (
                          id              BIGSERIAL PRIMARY KEY,
                          name            VARCHAR(150) NOT NULL,
                          email           VARCHAR(150) UNIQUE,
                          phone           VARCHAR(20),
                          birth_date      DATE,
                          gender          VARCHAR(20),
                          height_cm       NUMERIC(5,2),   -- ex: 175.50
                          weight_kg       NUMERIC(5,2),   -- ex: 78.30
                          goal            VARCHAR(255),   -- ex: Emagrecimento, Hipertrofia
                          medical_notes   TEXT,           -- restrições, lesões, etc
                          active          BOOLEAN DEFAULT TRUE,
                          created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
                          updated_at      TIMESTAMP,
                          deleted_at      TIMESTAMP
);
