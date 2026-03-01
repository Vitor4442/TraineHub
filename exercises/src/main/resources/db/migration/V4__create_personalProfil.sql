CREATE TABLE personal_users (
                                id              BIGSERIAL PRIMARY KEY,
                                owner_id        BIGINT,
                                name            VARCHAR(150) NOT NULL,
                                email           VARCHAR(150) NOT NULL UNIQUE,
                                password        VARCHAR(150) NOT NULL,
                                phone           VARCHAR(20),
                                role            VARCHAR(30) NOT NULL,
                                active          BOOLEAN DEFAULT TRUE,
                                birth_date      DATE,
                                gender          VARCHAR(20),
                                created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
                                updated_at      TIMESTAMP DEFAULT NOW(),
                                deleted_at      TIMESTAMP

);
