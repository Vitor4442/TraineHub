package com.vtr.exercises.repository;

import com.vtr.exercises.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
