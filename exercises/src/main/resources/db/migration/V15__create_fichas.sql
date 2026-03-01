CREATE TABLE fichas (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    nome        VARCHAR(100) NOT NULL,
    descricao   TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at  TIMESTAMP
);