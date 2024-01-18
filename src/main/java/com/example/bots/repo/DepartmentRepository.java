package com.example.bots.repo;

import com.example.bots.model.Degree;
import com.example.bots.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT COUNT(l) FROM Department d JOIN d.lectors l WHERE d.id = :departmentId AND l.degree = :degree")
    Long countLectorsByDegree(@Param("departmentId") Long departmentId, @Param("degree") Degree degree);
}








