<p align="center">
  <a href="https://endearing-blini-6a6b91.netlify.app/" target="_blank">
    <img src="https://drive.google.com/uc?export=view&id=1TuT30CiBkinh85WuTvjKGKN47hCyCS0Z" width="300" alt="Studios TKOH Logo">
  </a>
</p>

# Producto Manager API

API RESTful desarrollada con Spring Boot 3 y Java 21 para la gestión eficiente de inventarios y productos (Kardex). Este servicio permite administrar el ciclo de vida de los productos, controlar el stock (entradas y salidas), generar alertas de stock bajo y exportar datos para reportes.

## 🚀 Tecnologías Utilizadas

- **Lenguaje**: Java 21 (LTS)
- **Framework**: Spring Boot (Web, Data JPA)
- **Base de Datos**: MySQL 8.0
- **Herramientas de Build**: Maven
- **Contenerización**: Docker & Docker Compose
- **Utilidades**: Lombok
- **Entorno de Ejecución**: Eclipse Temurin (JRE 21)

## 📋 Prerrequisitos

Asegúrate de tener instalado lo siguiente si vas a ejecutar el proyecto localmente sin Docker:

- Java JDK 21
- Maven

Si prefieres usar contenedores (recomendado):

- Docker Desktop

## ⚙️ Configuración y Variables de Entorno

El proyecto utiliza variables de entorno para la conexión a la base de datos. Debes configurar un archivo `.env` en la raíz del proyecto (especialmente para Docker Compose) o configurar estas variables en tu IDE.

**Variables requeridas:**

| Variable         | Descripción                        | Valor por Defecto (Docker) |
|------------------|------------------------------------|---------------------------|
| MYSQL_HOST       | Host de la base de datos           | db (nombre del servicio en compose) o localhost |
| MYSQL_PORT       | Puerto de la base de datos         | 3306                      |
| MYSQL_DATABASE   | Nombre de la BD                    | producto_db (ejemplo)      |
| MYSQL_USER       | Usuario de la BD                   | root                      |
| MYSQL_PASSWORD   | Contraseña de la BD                | secret                    |

## 🛠️ Instalación y Ejecución

### Opción 1: Usando Docker Compose (Recomendado)

Esta opción levanta tanto la API como la base de datos MySQL automáticamente.

1. Crea un archivo `.env` en la raíz con tus credenciales (opcional si ya están en el compose).
2. Ejecuta el siguiente comando:

```bash
docker-compose up -d --build
````

La API estará disponible en: `http://localhost:8082`

### Opción 2: Ejecución Manual (Local)

Asegúrate de tener una base de datos MySQL corriendo.

1. Configura las variables de entorno en tu terminal o IDE, o edita `src/main/resources/application.properties` temporalmente.
2. Ejecuta el proyecto con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

## 🔌 Documentación de la API

La API expone los siguientes endpoints bajo el prefijo `/api/kardex`.

### 1. Gestión de Productos

#### ➤ Obtener todos los productos

Retorna la lista completa de productos con su estado actual.

* **Método**: GET
* **URL**: `/api/kardex/products`

**Respuesta Exitosa (200 OK):**

```json
[
  {
    "id": 1,
    "code": "P001",
    "name": "Laptop Gamer",
    "unit": "UNIDAD",
    "category": "Electrónica",
    "stock": 10,
    "minStock": 2,
    "status": "OK",
    "lastUpdated": "2023-10-27T10:00:00"
  }
]
```

#### ➤ Crear un producto

Registra un nuevo producto en el inventario.

* **Método**: POST
* **URL**: `/api/kardex/products`

**Cuerpo (JSON):**

```json
{
  "code": "P001",
  "name": "Laptop Gamer",
  "unit": "UNIDAD",
  "category": "Electrónica",
  "stock": 10,
  "minStock": 2
}
```

#### ➤ Carga Masiva de Productos

Permite crear múltiples productos en una sola petición.

* **Método**: POST
* **URL**: `/api/kardex/products/batch`

**Cuerpo (JSON Array):**

```json
[
  { "code": "A1", "name": "Prod A", "unit": "U", "category": "Cat1", "stock": 100, "minStock": 10 },
  { "code": "B2", "name": "Prod B", "unit": "U", "category": "Cat1", "stock": 50, "minStock": 5 }
]
```

### 2. Control de Inventario (Kardex)

#### ➤ Registrar Movimiento de Stock

Actualiza el stock de un producto (Entrada o Salida).

* **Método**: PUT
* **URL**: `/api/kardex/products/{id}/movement`
* **Parámetros** (Query Params):

  * `type`: IN (Entrada) o OUT (Salida)
  * `qty`: Cantidad a mover (Entero)
  * `reason`: Razón del movimiento (Texto)

Ejemplo:

```
/api/kardex/products/1/movement?type=OUT&qty=2&reason=Venta
```

### 3. Reportes y Alertas

#### ➤ Alertas de Stock Bajo

Obtiene productos cuyo stock es menor o igual al stock mínimo configurado.

* **Método**: GET
* **URL**: `/api/kardex/alerts`

#### ➤ Datos para Excel

Obtiene la data formateada específicamente para ser exportada a reportes de Excel.

* **Método**: GET
* **URL**: `/api/kardex/reports/excel-data`

**Respuesta Ejemplo:**

```json
[
  {
    "codigoBarras": "P001",
    "producto": "Laptop Gamer",
    "categoria": "Electrónica",
    "unidad": "UNIDAD",
    "cantidadActual": 10,
    "estadoInventario": "NORMAL",
    "fechaUltimoMovimiento": "27/10/2023 10:00"
  }
]
```

## 🏗️ Estructura del Proyecto

El proyecto sigue una arquitectura en capas clásica:

```
src/main/java/studios/tkoh/producto_manager
├── config/          # Configuraciones (CORS, etc.)
├── controller/      # Controladores REST (Endpoints)
├── dto/             # Data Transfer Objects (Request/Response)
├── model/           # Entidades JPA (Base de datos)
├── repository/      # Interfaces Repository (Acceso a datos)
├── service/         # Lógica de negocio
│   └── impl/        # Implementación de servicios
└── ProductoManagerApplication.java # Clase principal
```

## 🐳 Dockerfile & Despliegue

El proyecto incluye un **Dockerfile optimizado en dos etapas** (Multi-stage build):

* **Build Stage**: Usa una imagen de Maven para compilar el proyecto y descargar dependencias.
* **Production Stage**: Usa una imagen ligera JRE (`eclipse-temurin:21-jre-jammy`) para ejecutar la aplicación, creando un usuario sin privilegios por seguridad.

---

<p align="center">
  <sub>🛠️ Desarrollado con 💙 por <strong>Studios TKOH</strong></sub><br>
</p>
