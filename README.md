# Microservicio de Gestión de Usuarios

Este es un microservicio RESTful desarrollado con Spring Boot para la gestión de usuarios, permitiendo operaciones CRUD completas.

## Requisitos

- Java 17+
- Maven 3.8+
- MySQL 8+

## Características

- Gestión completa de usuarios (CRUD)
- Validación de datos
- Encriptación de contraseñas
- Manejo centralizado de excepciones
- Documentación de API

## Configuración

### Base de datos

El proyecto está configurado para conectarse a una base de datos MySQL. Puedes modificar la configuración en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

La aplicación también acepta la configuración a través de variables de entorno:
- `MYSQL_URL`: URL de conexión a la base de datos
- `MYSQL_USERNAME`: Usuario de la base de datos
- `MYSQL_PASSWORD`: Contraseña de la base de datos

### Puerto

El servicio se ejecuta por defecto en el puerto 9090. Puedes cambiar esto en `application.properties`:

```properties
server.port=9090
```

## Ejecución

### Desarrollo local

Para ejecutar el proyecto en un entorno de desarrollo local:

```bash
mvn spring-boot:run
```

### Construcción

Para compilar el proyecto:

```bash
mvn clean package
```

### Docker

El proyecto incluye dos archivos Dockerfile:

1. `dockerfile` - Para despliegue en ARM (ej. Raspberry Pi)
2. `dockerfile-local` - Para despliegue en arquitecturas x86/x64

#### Uso básico de Docker

```bash
# Para ARM
docker build -t microservicio-usuarios -f dockerfile .
docker run -p 9090:9090 microservicio-usuarios

# Para arquitecturas estándar
docker build -t microservicio-usuarios -f dockerfile-local .
docker run -p 9090:9090 microservicio-usuarios
```

#### Configuración de la base de datos en Docker

Para conectar a una base de datos específica, puedes pasar variables de entorno:

```bash
docker run -p 9090:9090 \
  -e MYSQL_URL=jdbc:mysql://tu-servidor-mysql:3306/tu_db \
  -e MYSQL_USERNAME=tu_usuario \
  -e MYSQL_PASSWORD=tu_contraseña \
  microservicio-usuarios
```

#### Conexión a MySQL local desde Docker

Para conectar a MySQL ejecutándose en tu máquina host desde Docker:

```bash
# En Windows/Mac (usando Docker Desktop)
docker run -p 9090:9090 \
  -e MYSQL_URL=jdbc:mysql://host.docker.internal:3306/tu_db \
  -e MYSQL_USERNAME=tu_usuario \
  -e MYSQL_PASSWORD=tu_contraseña \
  microservicio-usuarios

# En Linux (necesitas usar la IP de la red del host)
docker run -p 9090:9090 \
  -e MYSQL_URL=jdbc:mysql://172.17.0.1:3306/tu_db \
  -e MYSQL_USERNAME=tu_usuario \
  -e MYSQL_PASSWORD=tu_contraseña \
  microservicio-usuarios
```

#### Usando Docker Compose (recomendado)

Crea un archivo `docker-compose.yml` en la raíz del proyecto:

```yaml
version: '3.8'
services:
  app:
    build:
      context: .
      dockerfile: dockerfile-local  # o dockerfile para ARM
    ports:
      - "9090:9090"
    environment:
      - MYSQL_URL=jdbc:mysql://db:3306/usersdb
      - MYSQL_USERNAME=root
      - MYSQL_PASSWORD=rootpassword
    depends_on:
      - db
  
  db:
    image: mysql:8.0
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=rootpassword
      - MYSQL_DATABASE=usersdb
    volumes:
      - mysql-data:/var/lib/mysql

volumes:
  mysql-data:
```

Y ejecuta con:

```bash
docker-compose up -d
```

## Endpoints de la API

### Usuarios

| Método | URL                         | Descripción                    |
|--------|-----------------------------|---------------------------------|
| POST   | /api/v1/users               | Crear un nuevo usuario         |
| GET    | /api/v1/users               | Obtener todos los usuarios     |
| GET    | /api/v1/users/{id}          | Obtener usuario por ID         |
| GET    | /api/v1/users/email/{email} | Obtener usuario por email      |
| PUT    | /api/v1/users/{id}          | Actualizar un usuario existente|
| DELETE | /api/v1/users/{id}          | Eliminar un usuario            |

## Formato de Peticiones y Respuestas

### Crear/Actualizar Usuario

**Request:**

```json
{
  "username": "usuario_ejemplo",
  "email": "usuario@ejemplo.com",
  "password": "Contraseña1!"
}
```

**Validaciones:**
- El nombre de usuario debe tener entre 3 y 30 caracteres y solo puede contener letras, números, puntos, guiones y guiones bajos.
- El email debe tener un formato válido y no exceder los 100 caracteres.
- La contraseña debe tener entre 6 y 100 caracteres, e incluir al menos un número, una letra minúscula, una letra mayúscula y un carácter especial.

**Response:**

```json
{
  "id": "UUID-generado",
  "username": "usuario_ejemplo",
  "email": "usuario@ejemplo.com",
  "createdAt": "2023-06-15T10:30:45.123456",
  "updatedAt": "2023-06-15T10:30:45.123456"
}
```

## Manejo de Errores

El servicio implementa un manejo centralizado de errores que proporciona respuestas claras:

- **400 Bad Request**: Errores de validación
- **404 Not Found**: Recurso no encontrado
- **409 Conflict**: Recursos duplicados (email ya registrado)
- **500 Internal Server Error**: Error interno del servidor

## Solución de problemas comunes

### Problemas de conexión a la base de datos en Docker

Si experimentas errores como "Unable to determine Dialect without JDBC metadata", asegúrate de:

1. Verificar que la base de datos MySQL esté accesible desde el contenedor Docker
2. Comprobar que las credenciales de acceso son correctas
3. Usar la dirección correcta para tu entorno:
   - En Docker Desktop: `host.docker.internal` para referenciar al host
   - En Linux: usar la IP de la interfaz docker0 (generalmente `172.17.0.1`)
   - Si usas docker-compose, usar el nombre del servicio como hostname

## Seguridad

Actualmente, el servicio implementa encriptación de contraseñas pero no requiere autenticación para acceder a los endpoints. Esto facilita las pruebas durante el desarrollo.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, siga los estándares de codificación del proyecto y envíe sus pull requests para revisión.
