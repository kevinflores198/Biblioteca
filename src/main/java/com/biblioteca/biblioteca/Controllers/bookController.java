package com.biblioteca.biblioteca.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.biblioteca.biblioteca.Entities.Autor;
import com.biblioteca.biblioteca.Entities.Book;
import com.biblioteca.biblioteca.Entities.Editorial;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Services.BookService;
import com.biblioteca.biblioteca.Services.EditorialService;
import com.biblioteca.biblioteca.Services.AutorService;

@Controller
@RequestMapping("/book")
public class bookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private EditorialService editorialService;
    @Autowired
    private AutorService autorService;

    /**
     * @param model
     * @return
     */
    @GetMapping("/signin")
    public String signin(ModelMap model) {

        List<Autor> autors = autorService.autorList();
        List<Editorial> editorials = editorialService.editorialList();

        model.addAttribute("autors", autors);
        model.addAttribute("editorials", editorials);

        return "book_form.html";
    }

    @PostMapping("/signedin")
    public String signedin(
            @RequestParam(required = false) Long isbn,
            @RequestParam String title,
            @RequestParam(required = false) Integer type,
            @RequestParam String idAutor,
            @RequestParam String idEditorial,
            ModelMap model) {

        try {
            bookService.makeBook(isbn, title, type, idAutor, idEditorial);
            model.put("Success", "Book was charged correctly");
        } catch (MyException e) {
            List<Autor> autors = autorService.autorList();
            List<Editorial> editorials = editorialService.editorialList();

            model.addAttribute("autors", autors);
            model.addAttribute("editorials", editorials);
            model.put("Error", e.getMessage());
            return "book_form.html";
        }
        return "index.html";
    }

    @GetMapping("/list")
    public String listing(ModelMap model) {
        List<Book> books = bookService.booklist();

        model.addAttribute("books", books);

        return "book_list.html";
    }

    @GetMapping("/modify/{isbn}")
    public String modify(@PathVariable String isbn, ModelMap model) {
        model.put("book", bookService.getOne(isbn));
        return "book_modify.html";
    }

    @PostMapping("/modify/{isbn}")
    public String modify(@PathVariable Long isbn, String title, Integer type, String idAutor, String idEditorial,
            ModelMap model) {
        try {
            List<Autor> autors = autorService.autorList();
            List<Editorial> editorials = editorialService.editorialList();

            model.addAttribute("autors", autors);
            model.addAttribute("editorials", editorials);

            bookService.modifyBook(isbn, title, type, idAutor, idEditorial);

            return "redirect:../list";

        } catch (MyException ex) {
            List<Autor> autors = autorService.autorList();
            List<Editorial> editorials = editorialService.editorialList();

            model.put("Error", ex.getMessage());

            model.addAttribute("autors", autors);
            model.addAttribute("editorials", editorials);

            return "book_modify.html";
        }
    }

}
