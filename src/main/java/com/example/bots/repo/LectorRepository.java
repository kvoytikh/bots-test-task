package com.example.bots.repo;

import com.example.bots.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = "SELECT l FROM Lector l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Lector> searchLectors(@Param("query") String query);

    @Modifying
    @Query("UPDATE Lector l SET l.degree = CASE " +
            "WHEN l.degree = 'ASSISTANT' THEN 'ASSOCIATE_PROFESSOR' " +
            "WHEN l.degree = 'ASSOCIATE_PROFESSOR' THEN 'PROFESSOR' " +
            "ELSE l.degree END " +
            "WHERE l.id = :lectorId AND l.degree != 'PROFESSOR'")
    int promoteLector(@Param("lectorId") Long lectorId);
}







