package com.biblioteca.biblioteca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.biblioteca.biblioteca.Entities.Editorial;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {

    @Query("SELECT e FROM Editorial e WHERE e.name = :name")
    public Editorial searchAutor(@Param("name") String name);
}
