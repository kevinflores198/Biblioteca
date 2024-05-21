package com.biblioteca.biblioteca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.biblioteca.biblioteca.Entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

    @Query("SELECT a FROM Autor a WHERE a.name = :name")
    public Autor searchAutor(@Param("name") String name);
}
