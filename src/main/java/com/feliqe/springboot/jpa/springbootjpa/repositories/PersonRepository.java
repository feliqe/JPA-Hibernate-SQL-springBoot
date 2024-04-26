package com.feliqe.springboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.feliqe.springboot.jpa.springbootjpa.dto.PersonDto;
import com.feliqe.springboot.jpa.springbootjpa.entities.Person;

//funcion para ejecutar el CRUD de SQL (consultas solo por id no por cualquier campos de la tabla)
public interface PersonRepository extends CrudRepository<Person, Long> {

    //consulta por un areglo (array), en la condicion WHERE IN
    //con el campo que indica ?1 es el campo dinamico
    @Query("select p from Person p where p.id in ?1")
    public List<Person> getPersonByIds(List<Long> ids);
    // -------------------------------------------------------------------------------

    //consulta con condicion por el caracter del campo name con el caracter minimo
    @Query("select p.name, length(p.name) from Person p where length(p.name) = (select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("select p from Person p where p.id = (select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    // -------------------------------------------------------------------------------

    //agregacion de campos numericos
    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumAggregationFunction();

    // -------------------------------------------------------------------------------

    // MAX o MIN - consultar por le maximo o minimo de un campo numerico
    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    @Query("select count(p) from Person p")
    Long getCountId();

    //LENGTH - consultar para contar los caracter de un campo
    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNanmeLength();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    // -------------------------------------------------------------------------------

    //ORDER BY - consulta indicando el orden de los campos
    //consulta por dos campos de ordenamiento
    @Query("select p from Person p order by p.name desc, p.lastname asc")
    List<Person> getAllOrden();

    // -------------------------------------------------------------------------------

    // BETWEEN - consultar por consola los dos metodo que tenga la condicion
    @Query("select p from Person p where p.id between ?1 and ?2")
    List<Person> findAllBetweenId(Long c1, Integer C2);

    // -------------------------------------------------------------------------------

    //BETWEEN - motrar la lista con la condicion que este entre dos valores de un mismo campos
    //consultar por ID
    // @Query("select p from Person p where p.id between 4 and 5")
    //consultar por NAME
    @Query("select p from Person p where p.name between 'A' and 'Q' order by p.name desc")
    List<Person> findAllBetweenName();

    // -------------------------------------------------------------------------------

    //CONCAT concatenar de manera dinamica por una lista este caso

    //forma uno
    // @Query("select CONCAT(p.name,' ',p.lastname) from Person p")

    // forma dos
    // @Query("select p.name || ' ' || p.lastname from Person p")

    //colcoar el concatenado en MAYUSCULA
    // @Query("select upper(concat(p.name,' ',p.lastname)) from Person p")

    // colcoar el concatenado en MINUSCULA
    @Query("select lower(concat(p.name,' ',p.lastname)) from Person p")
    List<String> findAllFullNameConcat();

    // -------------------------------------------------------------------------------

    //consulta con palabras claves
    @Query("select distinct(p.name) from Person p")
    List<String> findAllNames();

    //consulta con un COUNT
    @Query("select count(distinct(p.programingLenguaje)) from Person p")
    Long findAllProgramingLenguajeCount();

    // -------------------------------------------------------------------------------

    //consulta dinamica personalizada - usando en contructor personalizado con solo estos campos
    @Query("select  new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjetPersonPersonalize();

    //consulta al Dto
    @Query("select  new com.feliqe.springboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllObjetPersonDto();

    // -------------------------------------------------------------------------------

    //consulta para obtener un solo dato con una consulta directa
    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    //resultado de la consulta por un numnero entero
    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    //resultado concatenado en un solo campos
    @Query("select CONCAT(p.name,' ',p.lastname) as fullName from Person p where p.id=?1")
    String getFullNameById(Long id);

    // -------------------------------------------------------------------------------

    //validar si tiene datos la consulta
    //findOne mostra solo un dato
    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    //otra forma del LIKE colocando la plabra Containing en el nombre del metodo, es un like por de tras que realiza spring
    Optional<Person> findByNameContaining(String name);

    // -------------------------------------------------------------------------------

    //funcion para consultas personalisadas por el campos definido en la funcion
    List<Person> findByProgramingLenguaje(String programingLenguaje);

    // 2 condiciones la consulta
    //crear cfuncion condicionada con una query de la clase no de SQL
    @Query("select p from Person p where p.programingLenguaje=?1 AND p.name=?2")
    List<Person> BuscarPorDefinicionQuery(String programingLenguaje, String name);

    //otra forma es sin escribir @Query indicando la condicion en el nombre de la lista
    List<Person> findByProgramingLenguajeAndName(String programingLenguaje, String name);

    // -------------------------------------------------------------------------------

    //consulta dinamica
    @Query("select p, p.programingLenguaje from Person p")
    List<Object[]> findAllMixPerson();

    //obtener todo los datos de la tabla
    @Query("select p.id, p.name, p.lastname, p.programingLenguaje from Person p")
    List<Object[]> obtenerPersonDataFullList();

    // obtener un campo de todo los listado
    @Query("select p.id, p.name, p.lastname, p.programingLenguaje from Person p where p.id=?1")
    Optional<Object> obtenerPersonDataFullById(Long id);

    //consulta sin definir la clase
    // se puede indicar condicion lo mismo que las demas list anterior
    @Query("select p.name, p.programingLenguaje from Person p")
    //el Object es una array [] por que trae mas de una campos en la consulta
    List<Object[]> obtenerPersonData();

}
