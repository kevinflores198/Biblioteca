package com.biblioteca.biblioteca.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.biblioteca.biblioteca.Entities.Book;
import com.biblioteca.biblioteca.Entities.Editorial;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    public Book searchBook(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.autor.name = :name")
    public List<Book> searchAutor(@Param("name") String name);

    @Query("SELECT b FROM Book b WHERE b.editorial.name = :name")
    public List<Editorial> searchEditorial(@Param("name") String name);

}
