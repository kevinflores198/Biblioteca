package com.biblioteca.biblioteca.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biblioteca.biblioteca.Entities.Autor;
import com.biblioteca.biblioteca.Entities.Book;
import com.biblioteca.biblioteca.Entities.Editorial;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Repositories.AutorRepository;
import com.biblioteca.biblioteca.Repositories.BookRepository;
import com.biblioteca.biblioteca.Repositories.EditorialRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void makeBook(Long isbn, String title, Integer type, String idAutor, String idEditorial)
            throws MyException {

        validate(isbn, title, type, idAutor, idEditorial);

        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();

        Book book = new Book();

        book.setIsbn(isbn);
        book.setTitle(title);
        book.setType(type);
        book.setAlta(new Date());
        book.setAutor(autor);
        book.setEditorial(editorial);

        bookRepository.save(book);
    }

    public Editorial getOne(String id){
        return editorialRepository.getReferenceById(id);
    }

    public List<Book> booklist(){

        List<Book> books = new ArrayList<>();
        books = bookRepository.findAll();
        return books;
    }

    public void modifyBook(Long isbn, String title, Integer type, String idAutor, String idEditorial)
            throws MyException {
        validate(isbn, title, type, idAutor, idEditorial);

        Optional<Book> bookAnswer = bookRepository.findById(isbn);
        Optional<Autor> autorAnswer = autorRepository.findById(idAutor);
        Optional<Editorial> editorialAnswer = editorialRepository.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (autorAnswer.isPresent()) {

            autor = autorAnswer.get();
        }

        if (editorialAnswer.isPresent()) {

            editorial = editorialAnswer.get();
        }

        if (bookAnswer.isPresent()) {

            Book book = bookAnswer.get();

            book.setTitle(title);
            book.setAutor(autor);
            book.setEditorial(editorial);
            book.setType(type);

            bookRepository.save(book);
        }
    }

    private void validate(Long isbn, String title, Integer type, String idAutor, String idEditorial)
            throws MyException {

        if (isbn == null) {
            throw new MyException("ISBN cannot be null.");
        }

        if (title == null || title.isEmpty()) {
            throw new MyException("Title cannot be null or empty.");
        }

        if (type == null) {
            throw new MyException("Type of book cannot be null.");
        }

        if (idAutor == null || idAutor.isEmpty()) {
            throw new MyException("ID cannot be null or empty.");
        }

        if (idEditorial == null || idEditorial.isEmpty()) {
            throw new MyException("ID cannot be null or empty.");
        }
    }

}
