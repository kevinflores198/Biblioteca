package com.biblioteca.biblioteca.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.biblioteca.Entities.Editorial;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Repositories.EditorialRepository;
import jakarta.transaction.Transactional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void makeEditoial(String name) throws MyException{

        validate(name);

        Editorial editorial = new Editorial();

        editorial.setName(name);

        editorialRepository.save(editorial);
    }

    public List<Editorial> editoriallist() {

        List<Editorial> editorials = new ArrayList<>();
        editorials = editorialRepository.findAll();
        return editorials;
    }

    public void modifyEditorial(String name, String id) {
        Optional<Editorial> editorialAnswer = editorialRepository.findById(id);

        if (editorialAnswer.isPresent()) {

            Editorial editorial = editorialAnswer.get();
            editorial.setName(name);
            editorialRepository.save(editorial);
        }
    }

     private void validate(String name) throws MyException {
        if (name == null || name.isEmpty()) {
            throw new MyException("ID cannot be null.");
        }
    }
}
