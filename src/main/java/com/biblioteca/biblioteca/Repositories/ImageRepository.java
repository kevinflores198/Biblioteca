package com.biblioteca.biblioteca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.Entities.Image;

public interface ImageRepository extends JpaRepository<Image, String> {

    
}
