# 👤 ms-usuarios — Microservicio de Usuarios

Microservicio encargado de la gestión y autenticación de usuarios para la plataforma **Donaton**, desarrollada con arquitectura de microservicios.

---

## 🚀 Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17 | Lenguaje principal |
| Spring Boot | 3.x | Framework base |
| Spring Security | 3.x | Autenticación y autorización |
| JWT | 0.11.x | Generación y validación de tokens |
| Spring Data JPA | 3.x | Persistencia de datos |
| Swagger/OpenAPI | 2.x | Documentación de API |
| JUnit 5 + Mockito | 5.x | Pruebas unitarias |
| MySQL | 8.x | Base de datos |

---

## 📁 Estructura del proyecto

```
ms-usuarios/
├── src/
│   ├── main/
│   │   ├── java/com/donaton/usuarios/
│   │   │   ├── controller/        # Endpoints REST
│   │   │   ├── dto/               # Objetos de transferencia de datos
│   │   │   ├── enums/             # Enumeraciones
│   │   │   ├── exception/         # Manejo de excepciones
│   │   │   ├── model/             # Entidades JPA
│   │   │   ├── repository/        # Repositorios Spring Data
│   │   │   ├── security/          # JWT y configuración de seguridad
│   │   │   └── service/           # Lógica de negocio
│   │   └── resources/
│   │       └── application.properties
│   └── test/                      # Pruebas unitarias
└── pom.xml
```

---

## ⚙️ Configuración

### Variables en `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/donaton_usuarios
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
jwt.secret=tu_clave_secreta
jwt.expiration=86400000
```

---

## 📖 Endpoints disponibles

### Autenticación
| Método | Endpoint | Descripción | Auth |
|---|---|---|---|
| `POST` | `/api/usuarios/registro` | Registrar nuevo usuario | ❌ |
| `POST` | `/api/usuarios/login` | Iniciar sesión y obtener token | ❌ |

### Usuarios
| Método | Endpoint | Descripción | Auth |
|---|---|---|---|
| `GET` | `/api/usuarios` | Listar todos los usuarios | ✅ |
| `GET` | `/api/usuarios/{id}` | Obtener usuario por ID | ✅ |
| `PUT` | `/api/usuarios/{id}` | Actualizar usuario | ✅ |
| `DELETE` | `/api/usuarios/{id}` | Eliminar usuario | ✅ |

---

## 🔐 Seguridad

El microservicio utiliza **JWT (JSON Web Token)** para autenticación:

1. El cliente hace `POST /api/usuarios/login` con sus credenciales
2. El servidor retorna un token JWT
3. El cliente incluye el token en el header de cada request:
```
Authorization: Bearer <token>
```

---

## 🧪 Pruebas unitarias

Ejecutar las pruebas con Maven:

```bash
mvn test
```

Cobertura actual: **+80%**

Clases testeadas:
- `UsuarioService`
- `UsuarioController`
- `GlobalExceptionHandler`

---

## 📄 Documentación Swagger

Una vez levantado el servicio, accede a la documentación interactiva en:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ▶️ Cómo ejecutar el proyecto

```bash
# Clonar el repositorio
git clone https://github.com/danibhern/ms-usuarios.git

# Entrar al directorio
cd ms-usuarios

# Ejecutar con Maven
mvn spring-boot:run
```

---

## 👩‍💻 Autora

**Daniela Barrera** — [@danibhern](https://github.com/danibhern)

Proyecto académico — Arquitectura de Microservicios
