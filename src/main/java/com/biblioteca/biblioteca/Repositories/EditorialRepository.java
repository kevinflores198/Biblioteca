package com.biblioteca.biblioteca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblioteca.biblioteca.Entities.Editorial;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {

}
