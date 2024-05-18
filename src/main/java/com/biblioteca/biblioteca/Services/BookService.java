package com.biblioteca.biblioteca.Services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.Entities.Autor;
import com.biblioteca.biblioteca.Entities.Book;
import com.biblioteca.biblioteca.Entities.Editorial;
import com.biblioteca.biblioteca.Repositories.AutorRepository;
import com.biblioteca.biblioteca.Repositories.BookRepository;
import com.biblioteca.biblioteca.Repositories.EditorialRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    // agregamos los id autor editorial para completar el libro con todos los datos
    public void makeBook(Long isbn, String title, Integer type, String idAutor, String idEditorial) {

        Autor autor = autorRepository.findById(idAutor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();

        Book book = new Book();

        // Pedimos toda la informacion y la agregamos a un nuevo libro
        book.setIsbn(isbn);
        book.setTitule(title);
        book.setType(type);
        book.setAlta(new Date());

        // Agregamos autores y editoriales al libro para completar
        book.setAutor(autor);
        book.setEditorial(editorial);

        // Creamos el libro y lo guardamos
        bookRepository.save(book);
    }
}
