ALTER TABLE workout_exercises ADD COLUMN workout_id BIGINT NOT NULL;

ALTER TABLE workout_exercises
    ADD CONSTRAINT fk_workout
        FOREIGN KEY(workout_id)
            REFERENCES workouts(id)
            ON DELETE CASCADE;