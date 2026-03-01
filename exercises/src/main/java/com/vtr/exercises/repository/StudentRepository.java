package com.vtr.exercises.repository;

import com.vtr.exercises.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Query("UPDATE Student s SET s.active = false WHERE s.id = :id")
    void disableStudent(@Param("id") Long id);

    @Query("SELECT s FROM Student s WHERE s.name LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Student> findStudentByName(@Param("name") String name, Pageable pageable);
}
