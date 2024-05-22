package com.biblioteca.biblioteca.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.biblioteca.Entities.Autor;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Repositories.AutorRepository;
import jakarta.transaction.Transactional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void makeAutor(String name) throws MyException {

        validate(name);

        Autor autor = new Autor();

        autor.setName(name);

        autorRepository.save(autor);
    }

    public List<Autor> autorList() {
        List<Autor> autors = new ArrayList<>();
        autors = autorRepository.findAll();
        return autors;
    }

    public void modifyAutor(String id, String name) throws MyException {

        validate(name);
        Optional<Autor> autorAnswer = autorRepository.findById(id);

        if (autorAnswer.isPresent()) {

            Autor autor = autorAnswer.get();
            autor.setName(name);
            autorRepository.save(autor);
        }
    }

    private void validate(String name) throws MyException {
        if (name == null || name.isEmpty()) {
            throw new MyException("Name cannot be null or empty.");
        }
    }
}
