CREATE TABLE workout_exercises (
                                   id                BIGSERIAL PRIMARY KEY,
                                   exercise_id       BIGINT NOT NULL,
                                   sets              INTEGER NOT NULL,
                                   reps              VARCHAR(50),
                                   advanced_technique VARCHAR(255),
                                   rest_time         VARCHAR(50),
                                   notes             TEXT,
                                   created_at        TIMESTAMP,
                                   updated_at        TIMESTAMP,
                                   deleted_at        TIMESTAMP,

                                   CONSTRAINT fk_exercise
                                       FOREIGN KEY(exercise_id)
                                           REFERENCES exercises(id)
                                           ON DELETE CASCADE
);