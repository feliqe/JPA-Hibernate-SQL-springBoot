package com.feliqe.springboot.jpa.springbootjpa.dto;

public class PersonDto {

    private String name;
    private String lastname;

    public PersonDto(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
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

    //para listar las consultas por arreglos
    @Override
    public String toString() {
        return "PersonDto [name=" + name + ", lastname=" + lastname + "]";
    }
}
