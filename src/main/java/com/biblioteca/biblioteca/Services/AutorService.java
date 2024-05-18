package com.biblioteca.biblioteca.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.biblioteca.Entities.Autor;
import com.biblioteca.biblioteca.Repositories.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public void makeAutor(String name) {

        Autor autor = new Autor();

        autor.setName(name);

        autorRepository.save(autor);
    }
}
