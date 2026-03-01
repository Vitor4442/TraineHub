CREATE TABLE exercises (
                          id              BIGSERIAL PRIMARY KEY,
                          name            VARCHAR(150) NOT NULL,
                          primary_muscle  VARCHAR(150) NOT NULL,
                          equipment       VARCHAR(150) NOT NULL,
                          video_url       TEXT,
                          description       TEXT,
                          created_at      TIMESTAMP,
                          updated_at      TIMESTAMP,
                          deleted_at      TIMESTAMP
);
