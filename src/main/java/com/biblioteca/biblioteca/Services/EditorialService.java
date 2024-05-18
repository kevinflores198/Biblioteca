package com.biblioteca.biblioteca.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.biblioteca.Entities.Editorial;
import com.biblioteca.biblioteca.Repositories.EditorialRepository;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public void makeEditoial(String name) {

        Editorial editorial = new Editorial();

        editorial.setName(name);

        editorialRepository.save(editorial);
    }
}
