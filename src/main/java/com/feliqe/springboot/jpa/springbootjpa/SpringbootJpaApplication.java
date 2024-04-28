package com.feliqe.springboot.jpa.springbootjpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.feliqe.springboot.jpa.springbootjpa.dto.PersonDto;
import com.feliqe.springboot.jpa.springbootjpa.entities.Person;
import com.feliqe.springboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	//trae las dependencias de la clase person
	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//mostramos el contenido que esta en el metodo
		// list();
		// findOne();
		// create();
		update();
		// delete2();
		// personalizedQuery2();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperAndLowerCase();
		// personalizeQueriesBetween();
		// queriesFunctionAggregation();
		// subQueries();
		// whereIn();
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("===== consulta where in =====");
		List<Person> persons = repository.getPersonByIds(Arrays.asList(1L, 2L, 5L, 7L));
		persons.forEach(System.out::println);
	}
	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("===== consulta por el nombre mas corto y largo =====");
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name="+name+", length="+length);
		});

		System.out.println("===== consultar para obtener el ultimo registro de la persona =====");
		Optional<Person> optionalPerson = repository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {

		System.out.println("===== conusltar por el total de campos =====");
		Long count = repository.getCountId();
		System.out.println(count);
		System.out.println("===== consultar por el minimo del registro de id =====");
		Long min = repository.getMinId();
		System.out.println(min);
		System.out.println("===== consultyar por el maximo del registro del id =====");
		Long max = repository.getMaxId();
		System.out.println(max);

		//consultar por el nombre y muestra el largo de los caracter de cada nombre a mostrar
		System.out.println("===== consultar por el nombre y el largo =====");
		List<Object[]> regs = repository.getPersonNanmeLength();
		 regs.forEach(reg -> {
				String name = (String) reg[0];
				Integer length = (Integer) reg[1];
				System.out.println("name="+name+", length="+length);
			});

		//consulta con LENGTH y MIN o MAX
		System.out.println("===== consultar por el nombre mas corto =====");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println(minLengthName);

		System.out.println("===== consultar por el nombre mas largo =====");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println(maxLengthName);

		System.out.println("===== consulta resumen de funciones de agregacion min, max, sum, avg, count =====");
		Object[] resumenReg = (Object[]) repository.getResumAggregationFunction();
		System.out.println(
						"min=" + resumenReg[0] +
						", max=" + resumenReg[1] +
						", suma=" + resumenReg[2] +
						", avg=" + resumenReg[3]+
						", count="+resumenReg[4]);
	}

	@Transactional(readOnly = true)
	public void personalizeQueriesBetween() {

		System.out.println("===== consulta que muestra resultado entre un rango definido =====");
		List<Person> persons = repository.findAllBetweenName();
		persons.forEach(System.out::println);

		System.out.println("===== consultar por rango pero definido por el proceso =====");
		List<Person> personName = repository.findAllBetweenId(4L, 5);
		personName.forEach(System.out::println);

		//consulta de order by
		System.out.println("===== consulta por order =====");
		List<Person> personOrder = repository.getAllOrden();
		personOrder.forEach(System.out::println);
	}

	public void personalizedQueriesConcatUpperAndLowerCase() {

		//consta personalizada concatenado el nombre con el apellido
		System.out.println("====== consulta de nombre y apellido de personas concatenados =====");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);
	}

	//consultas con palabras claves
	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("===== consulta con nombres de personas =====");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("===== consulta con total de cantida de un campo por un COUNT y DISTINCT =====");
		Long totalLenguaje = repository.findAllProgramingLenguajeCount();
		System.out.println("la cantidad de campos de lenguje de programacion unicos son: "+totalLenguaje);
	}

	//indicamos que	es una consulta con @Transactional(readOnly=true)
	@Transactional(readOnly=true)
	public void personalizedQuery2() {

		System.out.println("===== consulta por objeto perosna y lenguaje de programacion =====");
		//usamoa la consulta dinamica
		List<Object[]> personsRegs = repository.findAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("ProgrammingLenguge= "+reg[1]+", person= "+reg[0]);
		});

		//muestra la consulta personalizada mostrando los valor del cmapos definido en el contructor name y lastname, mostrando los demas campos en null
		System.out.println("===== consulta que puebla y devuelve objeto entity de una instancia personalizada =====");
		List<Person> persons = repository.findAllObjetPersonPersonalize();
		persons.forEach(System.out::println);

		//puebla la clase PersonDto y la consulta DTO
		System.out.println("===== consulta que puebla y devuelve objeto DTO de una clase personalizada =====");
		List<PersonDto> personsDto = repository.findAllObjetPersonDto();
		personsDto.forEach(System.out::println);
	}

	//indicamos que es una consulta
	@Transactional(readOnly = true)
	public void personalizedQuery() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("================= consulta sobre el nombre por el id =======================");
		System.out.println("Ingres el id:");
		Long id = scanner.nextLong();
		scanner.close();

		//usamos la funcion de buscar directo
		System.out.println("===== mostrando solo el nombre =====");
		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("===== mostrando solo el id =====");
		Long idBd = repository.getIdById(id);
		System.out.println(idBd);

		System.out.println("===== mostrando el nombre completo con CONCAT =====");
		String fullName = repository.getFullNameById(id);
		System.out.println(fullName);

		//consulta por todos los campos de la tabla segun le id inrgesado
		System.out.println("===== consultar por campos personlizados por el id =====");
		Optional<Object> optionalReg = repository.obtenerPersonDataFullById(id);
		//validamos el optional para el array
		if (optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.orElseThrow();
			System.out.println("id=" + personReg[0] + " nombre=" + personReg[1] + " apellido=" + personReg[2] + " lenguaje="+ personReg[3]);
		}
		//consulta por todos los campos de la tabla sin condicion
		System.out.println("===== consultar por campos personalizados lista =====");
		List<Object[]> regs = repository.obtenerPersonDataFullList();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + " nombre=" + reg[1] + " apellido="
				+ reg[2] + " lenguaje=" + reg[3]));
	}

	// otra forma de eliminar registro
	@Transactional
	public void delete2() {
		// mostramos el contenido de la tabla primero
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese le ID a eliminar");
		Long id = scanner.nextLong();

		// forma mas cencilla de validar y eliminar
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(repository::delete,
		()-> System.out.println("Lo sentimos no existe la persona con ese ID"));

		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void delete() {
		//mostramos el contenido de la tabla  primero
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese le ID a eliminar");
		Long id = scanner.nextLong();
		//se pasa el id a la funcion de spring para eliminar el registro
		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	//funcion para editar valor
	public void update() {
		//consultar el id de la tabla person de SQL, para traer los datos
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona");
		//alamcenamos en una variable el id ingresado por la consola como Long
		Long id = scanner.nextLong();

		//consultamos el ID en la base de datos
		Optional<Person> optinoalPerson = repository.findById(id);

		// //validamos si el ID consultado existe en nla base de datos
		// optinoalPerson.ifPresent(person -> {

		//otra opcion de validar y mostra un mensaje cuando no sea valido el ID ingresado
		if(optinoalPerson.isPresent())
		{
			//creamos la relacion del dato consultado con la clase de la base de datos
			Person personBd = optinoalPerson.orElseThrow();

			//mostramos el resulatdo de la consulta por ID de la base de datos
			System.out.println(personBd);
			System.out.println("Ingresa el lenguaje de programacion");
			String programingLenguaje = scanner.next();
			personBd.setProgramingLenguaje(programingLenguaje);
			//guardamos el nuevo valor del campo
			Person personUpodated = repository.save(personBd);
			//mostramos el nuevo valor modificado en la base de datos
			System.out.println(personUpodated);
		} else {
			System.out.println("El ID del usuario no existe en la base de datos!!");
		}
		// });

		//cerramos la consulta de scanner
		scanner.close();
	}

	// @Transactional - es para encansular los metodo de CREATE, UPDATE Y DELETE
	@Transactional
	//funcion para crear valor
	public void create(){

		//para  solicitar campos por consola
		Scanner scanner = new Scanner(System.in);
		//listamos los campos que seran solictados por consola
		System.out.println("Ingrese el nombre");
		String name = scanner.next();
		System.out.println("Ingrese el apellido");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion");
		String programingLenguaje = scanner.next();
		//cerramos la peticion por consola
		scanner.close();

		Person person = new Person(null,name,lastname,programingLenguaje);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		//validar y consultar el ID de dato recien creado
		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	// @TransactionalreadOnly = true) - es para encansular los metodo de SELECT
	@Transactional(readOnly = true)
	//funcion para buscar por un solo valor
	public void findOne() {
		//mostrar el campos por id sin necesidad de crear consultas por query
		//validacion del campo si existe para mostrar
		// Person person = null;
		// //realizamos la consulta
		// Optional<Person> optionalPerson = repository.findById(2L);
		// //validamos
		// if(optionalPerson.isPresent()){
		// 	person= optionalPerson.get();
		// }
		// System.out.println(person);

		//otra forma simple de validacion
		// repository.findById(1L).ifPresent(System.out::println);

		//trae los datos de Optional del repositorio
		// repository.findOneLikeName("se").ifPresent(System.out::println);
	}

	// @TransactionalreadOnly = true) - es para encansular los metodo de SELECT
	@Transactional(readOnly = true)
	//funcion para buscar y mostra por mas de un valor
	public void list() {
		// extraemos todos los datos de la class person
		// List<Person> persons = (List<Person>) repository.findAll();

		// CONSULTA PERSONALIZADA, por el campos definido
		// List<Person> persons = (List<Person>)
		// repository.findByProgramingLenguaje("javaScript");

		// CONSULTA PERSONALIZADA, por funcion de la @QUERY definida por la clase
		List<Person> persons = (List<Person>) repository.findByProgramingLenguajeAndName("java", "Andres");

		persons.stream().forEach(person -> {
			System.out.println(person);
		});

		List<Object[]> personsValues = repository.obtenerPersonData();
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en: " + person[1]);
		});
	}

}
