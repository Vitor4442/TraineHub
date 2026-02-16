package com.vtr.exercises.repository;

import com.vtr.exercises.model.PersonalUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalUserRepository extends JpaRepository<PersonalUsers, Long> {

    @Query("SELECT u FROM PersonalUsers u WHERE u.email =:email")
    PersonalUsers findByUserEmail(@Param("email") String email);

}
