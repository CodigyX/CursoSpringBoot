# Curso Spring Boot

Proyectos de práctica con **Spring Boot 3.2** y **Java 17**: dos aplicaciones que se comunican de forma asíncrona mediante **RabbitMQ**.

| Proyecto | Qué hace |
|---|---|
| [`reuniones`](reuniones) | Aplicación web y API REST para gestionar personas y reuniones |
| [`busca`](busca) | Cliente de consola que envía consultas a `reuniones` por medio de RabbitMQ |

## reuniones

- **Web con Thymeleaf:** inicio de sesión, listado de personas y de reuniones.
- **API REST:** controladores propios y **Spring Data REST** expuesto en `/api/rest`.
- **Persistencia:** Spring Data JPA con **PostgreSQL**; `schema.sql` y `data.sql` crean y cargan los datos iniciales.
- **Seguridad:** Spring Security con roles `USER` y `API_USER` y contraseñas cifradas con BCrypt.
- **Mensajería:** escucha en RabbitMQ las consultas que envía `busca` y registra la reunión de la persona.
- **Observabilidad:** Spring Boot Actuator con un endpoint personalizado.
- **Perfiles:** `desarrollo` (puerto 8081), `pruebas` (8082) y `produccion` (9000); sin perfil usa el 8080.

## busca

Aplicación de línea de comandos: pide el ID de una reunión y el de una persona, y publica la consulta en el exchange `servicios` de RabbitMQ con la clave `reuniones.busca.personas`.

## Cómo ejecutarlo

**Requisitos:** Java 17, PostgreSQL y RabbitMQ en local.

1. Crea la base de datos `Springboot` en PostgreSQL (por defecto en `localhost:5433`).
2. Define las credenciales como variables de entorno:

   ```bash
   export DB_PASSWORD=tu_contraseña
   # Opcionales, con estos valores por defecto:
   export DB_URL=jdbc:postgresql://localhost:5433/Springboot
   export DB_USERNAME=postgres
   ```

3. Inicia `reuniones`:

   ```bash
   cd reuniones
   ./mvnw spring-boot:run
   ```

4. En otra terminal, inicia `busca`:

   ```bash
   cd busca
   ./mvnw spring-boot:run
   ```

## Tecnologías

Java 17 · Spring Boot 3.2 · Spring Web · Spring Data JPA · Spring Data REST · Spring Security · Spring AMQP (RabbitMQ) · Thymeleaf · Actuator · PostgreSQL · Maven
