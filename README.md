## JPA e hibérnate

- es una herramienta de ORM, para la manipulación los datos de la BD  de forma a de clase y objeto.
- tipos lenguajes hibernase query lenguaje(HQL), criterio API, SQL nativo
-  asociacionismo @ManytoOne, @OnetoMany, @OnetoOne, @ManytoMany
-  página de documentación para Spring JPA
	https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html

------------------------------------ CARGAR STARTERS ---------------------------------------

Dependencies de Proyecto
* mySQL driver
* spring data JPA
* spring boot devTools

Los resultados se mostraran por consola.

------------------------------------ CONCEPTOS --------------------------------------

- en application.properties es donde se realiza la configuración de la conexión al base de datos
- para crear campos que se pre inserten al momento de crear la tabla se crea una hoja con extensión SQL en resoruces, se ingresar los INSERT INTO que se insertaran al tabla después de crear la  tablas, según las clases creadas.
- se crea la modal de persona donde estarán los datos de la tabla
- en PersonRepository se crea las funciones de buscador y listar
- en SpringbootJpaApplication es donde se realiza la funciones crud de SQL create, update, delete, select usando las funciones de buscar y listar de PersonRepository
- DTO - consultas personalizadas se crea el package Dto donde tendrás las class (models) donde se definirá los campos que se desea mostrar en la listas, es como un select pero con le interprete de spring usando las class (models)
- consultas con funciones de SQL de contar carácter como número y  consultas dinámicas con varias condiciones
- el CICLOD E PERSISTENCIA es referente a la fecha en donde se ejecuta la acción de create, update, delete

-----------------------------------------------------------------------------------
orden de archivos crear y ejecutar
- SpringbootJpaApplication
	se crear la implementas y el método
			implements CommandLineRunner
- se crear el package entities
	-- class Person
- package repositories
	-- class PersonRepository
- resources
	- campo de configuración
		- se configura la conexión y la configuración de consultas a la base de datos de MySQL
- descargar e instalar MySQL
	- https://dev.mysql.com/downloads/
	- se descargar el MySQL community Server versión (macOS 13 (ARM, 64-bit), DMG Archive)
	- se descargara y seguir los pasos de la instalación del sistema, colocar credenciales establecidas en application.properties
	- se descarga DBeaver para visualizar base de datos MySQL
		- si hay problemas al realizar la conexión se tiene que liminar la validación de SLL
	- para crear los campos en la base de datos se tiene que correr el código, esto es solo a nivel de pruebas ya que cada ves que se inicia el sistema borrar la información de las tabla ya que la creara de nuevo.
- se crear el archivo import.sql en resources
	- es para cuando se inicie a correr el sistema ejecute el contenido de este archivo (contiene los insert)
- se crea el package dto y se crear la class PersonDto
