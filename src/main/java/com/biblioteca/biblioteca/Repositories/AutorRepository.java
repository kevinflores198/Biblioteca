package com.biblioteca.biblioteca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblioteca.biblioteca.Entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {

}
