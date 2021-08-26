package com.ongvida.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ongvida.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{

    Teacher findByName(String name);
    List<Teacher> findByNameContaining(String name);
    Optional<Teacher> findById(Long id);

}
