package com.biblioteca.biblioteca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.biblioteca.Entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    
}
