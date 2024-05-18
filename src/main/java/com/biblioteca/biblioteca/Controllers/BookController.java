package com.biblioteca.biblioteca.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.biblioteca.biblioteca.Entities.*;
import com.biblioteca.biblioteca.Repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


// Haremos todas las direcciones para que las personas puedan consumir y usar, actualizar, borrar, leer y todo lo que tenga que ver con un Book
// Es una estructura de backend que da las url para poder actualizar un recurso
@RestController
@RequestMapping("/products")
public class BookController {
    
    // vamos a hacer los valores get post put delete
    // inyectamos una instancia a nuestro repositorio, usando Autowired
    @Autowired
    private BookRepository bookRepository;

    // mapeamos toda la lista de Books que tiene la base de datos
    @GetMapping
    public List<Book> getAllBooks() {

        // usamos findAll para traer todos los Books
        return bookRepository.findAll();
    }

    @GetMapping("/{Id}")
    public Book getBookById(@PathVariable Long Id) {
        return bookRepository.findById(Id)
                // Si no encuentra el ID mandamos ese error por pantalla
                .orElseThrow(() -> new RuntimeException("Produtco could not be found by ID: " + Id));
    }

    // devuelve el body que se crea del objeto
    @PostMapping
    public Book crearBook(@RequestBody Book Book) {
        // Guardamos en la base de datos con el .save
        return bookRepository.save(Book);
    }

    // editar
    @PutMapping("/{Id}")
    public Book updateBook(@PathVariable Long Id, @RequestBody Book BookDetail) {
        // si no lo encuentra devuelve un error, si lo encuentra, lo muestra
        Book Book = bookRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Produtco could not be found by ID: " + Id));

        // despues de actualizar el Book, lo seteamos y traemos el cambio,
        // retornamos el Book actualizado
        Book.setTitule(BookDetail.getTitule());
        Book.setType(BookDetail.getType());

        return bookRepository.save(Book);
    }

    @DeleteMapping("/{Id}")
    public String deleteBook(@PathVariable Long id) {
        Book Book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produtco could not be found by ID: " + id));
        // usamos .delete para eliminar un Book de la lista
        bookRepository.delete(Book);

        return "The product by ID: " + id + " was deleted sucesfully.";

    }
}





