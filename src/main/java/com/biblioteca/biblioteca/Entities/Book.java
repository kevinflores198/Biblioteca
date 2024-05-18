package com.biblioteca.biblioteca.Entities;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// Va a ser una entidad y tenemos que nombrarlo con lo que es
@Entity
public class Book {

    // El identificador unico de cada libro, clave primaria de cada registro en
    // base de datos
    @Id
    private Long isbn;
    private String titule;
    private Integer type;

    //Cuando el libro se registe en el sistema se registrara la fecha de alta
    @Temporal(TemporalType.DATE)
    private Date alta;

    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;
    public Long getIsbn() {
        return isbn;
    }
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
    public String getTitule() {
        return titule;
    }
    public void setTitule(String titule) {
        this.titule = titule;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Date getAlta() {
        return alta;
    }
    public void setAlta(Date alta) {
        this.alta = alta;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public Editorial getEditorial() {
        return editorial;
    }
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    
    
}
