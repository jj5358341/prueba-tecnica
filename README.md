# 📦 Prueba Técnica – Gestión de Productos Institucionales

Proyecto Full-Stack desarrollado con **Spring Boot**, **Oracle** y **Angular** para la administración de un catálogo de productos institucionales, cumpliendo con criterios de seguridad, trazabilidad y eficiencia en el manejo de datos.

---

# 🧠 Descripción General

Este sistema permite gestionar productos institucionales mediante:

* Operaciones CRUD completas
* Búsqueda avanzada con filtros
* Generación de reportes en Excel en memoria (Base64)
* Autenticación y autorización mediante JWT

El objetivo principal es proporcionar una solución segura, escalable y eficiente, alineada a buenas prácticas de desarrollo backend y frontend.

---

# 🚀 Tecnologías Utilizadas

## 🔧 Backend

* Java 17
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* Maven
* Apache POI (generación de Excel)

## 🎨 Frontend

* Angular
* TypeScript
* Bootstrap

## 🗄️ Base de Datos

* Oracle Database

---

# 🧱 Arquitectura

El backend sigue una arquitectura por capas:

```text id="8m6sj3"
Controller → Service → Repository → Entity → DTO → Security → Exception
```

## Principales prácticas aplicadas

* Separación de entidades y DTOs
* Manejo global de excepciones
* Validaciones con `@Valid`
* Servicios desacoplados
* Código limpio y mantenible

---

# 🔐 Seguridad (JWT)

El sistema implementa autenticación basada en **JSON Web Tokens (JWT)**.

## Flujo de autenticación

### 1. El usuario envía credenciales

```http id="2e2mji"
POST /auth/login
```

### 2. El sistema valida el usuario en base de datos

### 3. Se genera un token JWT

```json id="1i8s5v"
{
  "token": "jwt-token",
  "usuario": {
    ...
  }
}
```

### 4. El token se envía en cada petición protegida

```http id="kr6d8j"
Authorization: Bearer <token>
```

---

# 📬 Endpoints del API

# 🔐 Autenticación

| Método | Endpoint      | Descripción      |
| ------ | ------------- | ---------------- |
| POST   | `/auth/login` | Genera token JWT |

---

# 📦 Productos

| Método | Endpoint                  | Descripción                     |
| ------ | ------------------------- | ------------------------------- |
| POST   | `/productos`              | Crear producto (requiere token) |
| GET    | `/productos`              | Listar productos                |
| GET    | `/productos/{idProducto}` | Obtener por ID                  |
| PUT    | `/productos/{idProducto}` | Actualizar producto             |
| DELETE | `/productos/{idProducto}` | Eliminar producto               |

---

# 🔎 Filtros de búsqueda

| Método | Endpoint             |
| ------ | -------------------- |
| GET    | `/productos/filtrar` |

## Parámetros opcionales

* `nombreProducto`
* `claveProducto`
* `precioMin`
* `precioMax`

## Ejemplo

```http id="k8c89r"
/productos/filtrar?nombreProducto=test&precioMin=100
```

---

# 📊 Generación de Reporte Excel

| Método | Endpoint                   |
| ------ | -------------------------- |
| GET    | `/productos/reporte-excel` |

## Características

* Generación en memoria
* Conversión a Base64
* Entrega en JSON

## Ejemplo de respuesta

```json id="3i5ev8"
{
  "status": 200,
  "message": "Reporte generado correctamente",
  "fileName": "productos.xlsx",
  "fileBase64": "UEsDBBQAAAAI..."
}
```

---

# ✅ Validaciones

* Campos obligatorios
* Longitud máxima de atributos
* Precio no negativo
* Manejo de errores estándar

## Ejemplo de error

```json id="n8mwoz"
{
  "timestamp": "2026-05-26T18:30:00",
  "status": 400,
  "message": "La clave del producto es obligatoria"
}
```

---

# ⚙️ Configuración y Ejecución

# 📌 Backend

## Clonar repositorio

```bash id="t7wx4m"
git clone https://github.com/jj5358341/prueba-tecnica.git
```

## Configurar conexión a Oracle

Archivo:

```text id="lwr0az"
src/main/resources/application.properties
```

```properties id="k4e85e"
spring.application.name=crudPrueba

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCL
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

server.port=8081
```

## Ejecutar la aplicación

```bash id="h5wmyj"
mvn spring-boot:run
```

## Servidor disponible en

```text id="gmv4ob"
http://localhost:8081
```

---

# 📌 Frontend (Angular)

```bash id="5xvrh4"
cd frontend
npm install
ng serve
```

## Acceso

```text id="9v3pkf"
http://localhost:4200
```

---

# 🗄️ Base de Datos

El script SQL de creación de la base de datos se encuentra adjunto dentro del repositorio.

## Incluye

* Creación de la tabla de productos
* Configuración de estructura y tipos de datos
* Datos necesarios para pruebas (si aplica)

---

# 📊 Funcionalidades Destacadas

* ✅ Autenticación segura con JWT
* ✅ CRUD completo
* ✅ Filtros dinámicos
* ✅ Exportación a Excel sin archivos temporales
* ✅ Respuestas en Base64
* ✅ Arquitectura limpia

---

# 📚 Repositorio

## GitHub

**Repositorio:** `jj5358341/prueba-tecnica`

> Código Java prueba técnica IMSS.
> Contribute to `jj5358341/prueba-tecnica` development by creating an account on GitHub.
