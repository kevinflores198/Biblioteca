package com.biblioteca.biblioteca.Entities;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Autor {

    @Id
    // Lo que hace esto es que el valor del id se genere de manera automatica
    // al momento de que elk repo persiste la entidad
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String ID;
    private String name;

    //Cuando el libro se registe en el sistema se registrara la fecha de alta
    @Temporal(TemporalType.DATE)
    private Date alta;

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    
    
}
