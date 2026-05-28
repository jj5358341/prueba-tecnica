📦 Prueba Técnica – Gestión de Productos Institucionales
Proyecto Full-Stack desarrollado con Spring Boot, Oracle y Angular para la administración de un catálogo de productos institucionales, cumpliendo con criterios de seguridad, trazabilidad y eficiencia en el manejo de datos.
 
🧠 Descripción General
Este sistema permite gestionar productos institucionales mediante:
Operaciones CRUD completas
Búsqueda avanzada con filtros
Generación de reportes en Excel en memoria (Base64)
Autenticación y autorización mediante JWT
El objetivo principal es proporcionar una solución segura, escalable y eficiente, alineada a buenas prácticas de desarrollo backend y frontend.
 
🚀 Tecnologías Utilizadas
🔧 Backend
Java 17
Spring Boot
Spring Security (JWT)
Spring Data JPA
Maven
Apache POI (generación de Excel)
🎨 Frontend
Angular
TypeScript
Bootstrap
🗄️ Base de Datos
Oracle Database
 
🧱 Arquitectura
El backend sigue una arquitectura por capas:
1     Controller → Service → Repository → Entity → DTO → Security → Exception 
Principales prácticas aplicadas:
Separación de entidades y DTOs
Manejo global de excepciones
Validaciones con @Valid
Servicios desacoplados
Código limpio y mantenible
 
🔐 Seguridad (JWT)
El sistema implementa autenticación basada en JSON Web Tokens (JWT).
Flujo de autenticación
El usuario envía credenciales:
1     POST /auth/login 
El sistema valida el usuario en base de datos
Se genera un token JWT:
1     { 
2       "token": "jwt-token", 
3       "usuario": { ... } 
4     } 
El token se envía en cada petición protegida:
1     Authorization: Bearer <token> 
 
📬 Endpoints del API
🔐 Autenticación
Método 	Endpoint 	Descripción 
POST 	/auth/login 	Genera token JWT 
 
📦 Productos
Método 	Endpoint 	Descripción 
POST 	/productos 	Crear producto (requiere token) 
GET 	/productos 	Listar productos 
GET 	/productos/{idProducto} 	Obtener por ID 
PUT 	/productos/{idProducto} 	Actualizar producto 
DELETE 	/productos/{idProducto} 	Eliminar producto 
 
🔎 Filtros de búsqueda
Método 	Endpoint 
GET 	/productos/filtrar 
Parámetros opcionales:
nombreProducto
claveProducto
precioMin
precioMax
Ejemplo:
1     /productos/filtrar?nombreProducto=test&precioMin=100 
 
📊 Generación de Reporte Excel
Método 	Endpoint 
GET 	/productos/reporte-excel 
Características:
Generación en memoria
Conversión a Base64
Entrega en JSON
Ejemplo de respuesta:
1     { 
2       "status": 200, 
3       "message": "Reporte generado correctamente", 
4       "fileName": "productos.xlsx", 
5       "fileBase64": "UEsDBBQAAAAI..." 
6     } 
 
✅ Validaciones
Campos obligatorios
Longitud máxima de atributos
Precio no negativo
Manejo de errores estándar
Ejemplo:
1     { 
2       "timestamp": "2026-05-26T18:30:00", 
3       "status": 400, 
4       "message": "La clave del producto es obligatoria" 
5     } 
 
⚙️ Configuración y Ejecución
📌 Backend
Clonar repositorio:
1     git clone https://github.com/jj5358341/prueba-tecnica.git 
Configurar conexión a Oracle en:
1     src/main/resources/application.properties 
spring.application.name=crudPrueba 
  
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCL 
spring.datasource.username=USERNAME 
spring.datasource.password=PASSWORD 
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver 
  
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect 
spring.jpa.hibernate.ddl-auto=none 
spring.jpa.show-sql=true 
server.port:8081 
Ejecutar la aplicación:
1     mvn spring-boot:run 
Servidor disponible en:
1     http://localhost:8080 
 
📌 Frontend (Angular)
1     cd frontend 
2     npm install 
3     ng serve 
Acceso:
1     http://localhost:4200 
 
🗄️ Base de Datos
El script SQL de creación de la base de datos se encuentra adjunto dentro del repositorio.
Incluye:
Creación de la tabla de productos
Configuración de estructura y tipos de datos
Datos necesarios para pruebas (si aplica)
 
📊 Funcionalidades Destacadas
✅ Autenticación segura con JWT
✅ CRUD completo
✅ Filtros dinámicos
✅ Exportación a Excel sin archivos temporales
✅ Respuestas en Base64
✅ Arquitectura limpia
GitHub - jj5358341/prueba-tecnica: Codigo java prueba tecnica imss
Codigo java prueba tecnica imss. Contribute to jj5358341/prueba-tecnica development by creating an account on GitHub.
 
