package com.ongvida.repositories;;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ongvida.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

    List<Student> findByName(String nome);
    List<Student> findByNameContaining(String nome);

}
