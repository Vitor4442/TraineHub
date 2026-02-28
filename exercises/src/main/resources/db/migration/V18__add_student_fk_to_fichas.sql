ALTER TABLE fichas
    ADD COLUMN student_id BIGINT NOT NULL;

ALTER TABLE fichas
    ADD CONSTRAINT fk_fichas_students
        FOREIGN KEY (student_id)
            REFERENCES students(id)
            ON DELETE CASCADE;