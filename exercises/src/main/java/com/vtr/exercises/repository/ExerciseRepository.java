package com.vtr.exercises.repository;

import com.vtr.exercises.model.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercises, Long> {
}
