# University Backend

Este proyecto forma parte de un sistema universitario diseñado para la gestión integral de usuarios. Su arquitectura se basa en los principios de microservicios, con un enfoque en la escalabilidad y la modularidad. Se complementa con otro proyecto en Node.js para la autenticación e inscripción de materias, formando un sistema completo para una institución educativa.

## Arquitectura y Patrones de Diseño

El proyecto sigue una arquitectura de microservicios, donde cada directorio representa un componente independiente del sistema. Se ha implementado el patrón de diseño MVC (Modelo-Vista-Controlador) para separar la lógica de negocio de la presentación y la manipulación de datos. Además, se han utilizado los siguientes patrones de diseño:

- **DTO (Data Transfer Object):** Utilizado para transferir datos entre capas y reducir el acoplamiento.
- **Repository:** Para abstraer la capa de acceso a datos y facilitar el uso de diferentes tecnologías de almacenamiento.
- **Service:** Para implementar la lógica de negocio de forma separada y reutilizable.

## Estructura del Proyecto

El proyecto está estructurado en los siguientes directorios:

- **DTOs:** Contiene los Data Transfer Objects utilizados para transferir datos entre capas.
- **controllers:** Contiene los controladores que manejan las peticiones HTTP y gestionan la lógica de la aplicación.
- **entities:** Contiene las entidades JPA (Java Persistence API) que representan las tablas de la base de datos.
- **repositories:** Contiene los repositorios JPA para acceder a la base de datos de manera abstracta.
- **services:** Contiene los servicios que implementan la lógica de negocio de forma independiente de la capa de presentación.

## Configuración

La aplicación está configurada para utilizar una base de datos MySQL con las siguientes propiedades:

- **URL:** jdbc:mysql://localhost:3306/university
- **Usuario:** admin
- **Contraseña:** 123456
- **Dialecto de Hibernate:** org.hibernate.dialect.MySQLDialect
- **DDL Auto:** update

## Endpoints

- **GET /user/all-users:** Obtiene todos los usuarios registrados en el sistema.
- **GET /user/{userId}:** Obtiene un usuario específico según su ID.
- **POST /user:** Crea un nuevo usuario en el sistema.
- **DELETE /user/{userId}:** Elimina un usuario existente según su ID.
- **PUT /user/{userId}:** Actualiza los datos de un usuario existente según su ID.

## Repositorio de Node.js

Además de este proyecto, se encuentra disponible un proyecto complementario en Node.js para la autenticación e inscripción de materias. Puedes encontrar más detalles en el siguiente enlace: [subject_backend](https://github.com/DANIELSSF/subject_backend).
