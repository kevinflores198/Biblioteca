package com.biblioteca.biblioteca.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.biblioteca.Entities.Book;

// Creamos una interface que extiende el objeto y el tipo de ID, ex: <Book,long>
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
 
    @Query("select b from book b where b.title = :title")
    public Book searchForBook(String title);

    @Query("select b from book b.autor.name = :name")
    public List<Book> searchForAutor(@Param ("name") String name);

}
