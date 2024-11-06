# Repositorio Proyecto Fin de Curso - Spring Boot - Todo List

## Descripción

Este proyecto es una aplicación que permite a los usuarios crear, editar y eliminar tareas. Se utiliza Spring Boot para la construcción de la aplicación y Spring Data JPA para la gestión de la base de datos.

## Requerimientos

- Java 17
- Maven
- Git

## Instalación

1. Clonar el repositorio en tu máquina local.
2. Abrir la terminal y navegar hasta el directorio del proyecto.

## Contenido

El proyecto contiene 4 subproyectos:

- todo-api-rest: la api esta protegida con JWT para la autenticación
- todo-list-mvc: la aplicacion web usa thymeleaf para renderizar la interfaz, la logica de negocio se encuentra en el mismo proyecto
- todo-backend: contiene una logica de negocio para la aplicación todo-frontend
- todo-frontend: interfaz web para la aplicación que utiliza como el proyecto todo-backend para la logica de negocio


## Estructura de la base de datos

Se tiene dos tablas en la base de datos:

- tbl_user: contiene información sobre los usuarios registrados en la aplicación
    campos:
        - id: identificador único del usuario
        - email: dirección de correo electrónico del usuario
        - name: nombre del usuario  
        - password: contraseña del usuario

- tbl_todo: contiene información sobre las tareas creadas en la aplicación
    campos:
        - id: identificador único de la tarea
        - state: estado de la tarea (1 para completada, 0 para no completada)
        - timestamp: fecha y hora de creación de la tarea
        - user_id: identificador del usuario que creó la tarea
        - description: descripción de la tarea
        - title: título de la tarea

## Funcionamiento general

La aplicacion permite a los usuarios buscar, crear, editar y eliminar tareas. Solo la api rest que esta protegida con JWT para la autenticación se puede crear tambien usuarios, el resto de sub-proyectos no lo requieren.

## Api Rest - Funcionamiento

### Endpoints

| Método | URL                                          | Autorización | Body (JSON)                               |
|--------|----------------------------------------------|--------------|-------------------------------------------|
| POST   | `/api/v1/auth/register`                      | No           | `{ "name": "leonardo", "email": "levifralex@mail.com", "password": "123456" }` |
| POST   | `/api/v1/auth/login`                         | No           | `{ "name": "john", "password": "123456" }`                             |
| POST   | `/api/v1/todos`                              | Sí           | `{ "title": "Nueva tarea", "description": "prueba", "userId": 1 }`     |
| GET    | `/api/v1/todos/{tarea_id}`                   | Sí           | -                                                                      |
| GET    | `/api/v1/todos`                              | Sí           | -                                                                      |
| POST   | `/api/v1/todos/paging`                       | Sí           | `{ "pageNumber": 1, "pageSize": 10, "fields": ["description", "title"], "order": "DESC" }`     |
| PUT    | `/api/v1/todos/{tarea_id}`                   | Sí           | `{ "title": "Nueva tarea", "description": "prueba", "userId": 1, "state": 1 }`     |
| PATCH  | `/api/v1/todos/{tarea_id}`                   | Sí           | `{ "title": "Nueva tarea", "description": "prueba", "userId": 1,"state": 1 }`     |
| DELETE | `/api/v1/todos/{tarea_id}`                   | Sí           | -                                                                    |


## Proyecto MVC - Funcionamiento

- CRUD de tareas
- Busqueda de tareas por descripción


## Proyecto Distribuido - Funcionamiento

- CRUD de tareas
- Busqueda de tareas por descripción