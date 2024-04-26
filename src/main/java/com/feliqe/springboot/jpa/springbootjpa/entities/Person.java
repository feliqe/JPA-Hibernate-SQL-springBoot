package com.feliqe.springboot.jpa.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//nombre de la tabla SQL
//se puede omitir si la tabla se llama igual a la clase
@Table(name = "persons")
public class Person {

    //indicamos el campo auto incrementable en este caso el ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //se puede cambiar IDENTITY por AUTO, aun que no es recomendado
    private Long id;

    private String name;
    private String lastname;

    //en caso que tenga un caracter que no se puede colocar en la clase se mapea el campo, en este caso con _ entre las palabras
    @Column(name = "programing_lenguaje")
    private String programingLenguaje;

    //se usas el constructor vacio para pasar los datos, siempre vacio para JPA
    public Person() {
    }

    //consulta dinamica personalida - contructor instancian de mostra a estos dos datos enves de selecionarlo en la QUERY
    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    //contructor para poder realizar INSERT a la tabla por la clase
    public Person(Long id, String name, String lastname, String programingLenguaje) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programingLenguaje = programingLenguaje;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getProgramingLenguaje() {
        return programingLenguaje;
    }

    public void setProgramingLenguaje(String programingLenguaje) {
        this.programingLenguaje = programingLenguaje;
    }

    // para relaiza consultas de los campos
    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", lastname=" + lastname + ", programingLenguaje="
                + programingLenguaje + "]";
    }
}
