package com.biblioteca.biblioteca.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalController {

    // @Autowired
    // private BookRepository bookRepository;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    // @GetMapping
    // public List<Book> getAllBooks() {
    //     return bookRepository.findAll();
    // }

    // @GetMapping("/{Id}")
    // public Book getBookById(@PathVariable Long Id) {
    //     return bookRepository.findById(Id)
    //             // Si no encuentra el ID mandamos ese error por pantalla
    //             .orElseThrow(() -> new RuntimeException("Produtco could not be found by ID: " + Id));
    // }

    // // devuelve el body que se crea del objeto
    // @PostMapping
    // public Book crearBook(@RequestBody Book Book) {
    //     // Guardamos en la base de datos con el .save
    //     return bookRepository.save(Book);
    // }

    // // editar
    // @PutMapping("/{Id}")
    // public Book updateBook(@PathVariable Long Id, @RequestBody Book BookDetail) {
    //     // si no lo encuentra devuelve un error, si lo encuentra, lo muestra
    //     Book Book = bookRepository.findById(Id)
    //             .orElseThrow(() -> new RuntimeException("Produtco could not be found by ID: " + Id));

    //     // despues de actualizar el Book, lo seteamos y traemos el cambio,
    //     // retornamos el Book actualizado
    //     Book.setTitle(BookDetail.getTitle());
    //     Book.setType(BookDetail.getType());

    //     return bookRepository.save(Book);
    // }

    // @DeleteMapping("/{Id}")
    // public String deleteBook(@PathVariable Long id) {
    //     Book Book = bookRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Produtco could not be found by ID: " + id));
    //     // usamos .delete para eliminar un Book de la lista
    //     bookRepository.delete(Book);

    //     return "The product by ID: " + id + " was deleted sucesfully.";

    // }
}
