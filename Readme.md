# Prueba Técnica - Sistema Bancario

Este proyecto implementa un sistema bancario básico con dos microservicios (`cliente-service` y `cuenta-service`) y una base de datos MySQL.  
Incluye pruebas unitarias, integración, colección de Postman y despliegue con Docker.

## 📂 Github

https://github.com/diegoa915/prueba-tecnica-banco
---

## 📂 Estructura del proyecto

Prueba tecnica/
├── cliente-service/        # Microservicio de clientes
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── cuenta-service/         # Microservicio de cuentas y movimientos
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── BaseDatos.sql           # Script inicial de la base de datos
├── PostmanCollection.json  # Colección de Postman para pruebas de endpoints
└── docker-compose.yml      # Orquestación de servicios

Código

---

## ⚙️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **MySQL 8**
- **JUnit 5 + Mockito + AssertJ**
- **Docker + Docker Compose**
- **Postman** (colección de pruebas)

---

## 🗄️ Base de datos

Archivo `BaseDatos.sql` contiene:
- Creación de la base de datos `banco`.
- Tablas: `persona`, `cliente`, `cuenta`, `movimiento`.
- Relaciones entre entidades.

Ejecutar en MySQL:

```bash
mysql -u root -p < BaseDatos.sql
🧪 Pruebas
Unitarias
ClienteControllerTest: valida creación de clientes con @WebMvcTest y @MockBean.

MovimientoServiceTest: valida lógica de movimientos y actualización de saldo con Mockito.

Integración
ClienteCuentaIntegrationTest: prueba flujo completo (crear cliente → crear cuenta → registrar movimiento → validar saldo).

Ejecutar pruebas:

bash
mvn test
📬 Postman
Se incluye PostmanCollection.json con los endpoints:

POST /clientes → Crear cliente

POST /cuentas → Crear cuenta

POST /movimientos → Registrar movimiento

GET /reportes → Consultar movimientos por rango de fechas

Importar en Postman y ejecutar contra los servicios levantados en Docker.

🐳 Docker
Dockerfile (ejemplo para cada microservicio)
dockerfile
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
docker-compose.yml
yaml
services:
  mysql:
    image: mysql:8
    container_name: banco-mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: banco
    ports:
      - "3307:3306"   # puerto externo ajustado para evitar conflictos
    volumes:
      - mysql_data:/var/lib/mysql

  cliente-service:
    build: ./cliente-service
    container_name: cliente-service
    ports:
      - "8081:8080"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/banco
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root

  cuenta-service:
    build: ./cuenta-service
    container_name: cuenta-service
    ports:
      - "8082:8080"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/banco
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root

volumes:
  mysql_data:
🚀 Pasos para ejecutar
Compilar los microservicios

bash
cd cliente-service
mvn clean package -DskipTests
cd ../cuenta-service
mvn clean package -DskipTests
Levantar con Docker Compose

bash
docker-compose up --build
Servicios disponibles

MySQL → localhost:3307

Cliente Service → http://localhost:8081

Cuenta Service → http://localhost:8082

🔍 Pruebas manuales con Postman / cURL
Crear cliente
bash
curl -X POST http://localhost:8081/clientes \
     -H "Content-Type: application/json" \
     -d '{"nombre":"Jose Lema","estado":true}'
Crear cuenta
bash
curl -X POST http://localhost:8082/cuentas \
     -H "Content-Type: application/json" \
     -d '{"numero":478758,"tipo":"Ahorros","saldoInicial":2000,"estado":true,"clienteId":1}'
Registrar movimiento
bash
curl -X POST http://localhost:8082/movimientos \
     -H "Content-Type: application/json" \
     -d '{"tipo":"Deposito","valor":500,"cuenta":{"numero":478758}}'
📦 Entregables
BaseDatos.sql → Script de base de datos.

PostmanCollection.json → Colección de pruebas de endpoints.

Pruebas unitarias e integración en cada microservicio.

docker-compose.yml y Dockerfile → despliegue en Docker.

Repositorio público en GitHub con todo el código y documentación.

Archivo .zip con todos los entregables.

✅ Conclusión
Este proyecto demuestra:

Diseño de microservicios con Spring Boot.

Persistencia con JPA y MySQL.

Pruebas unitarias e integración con JUnit 5 y Mockito.

Validación de endpoints con Postman.

Despliegue profesional con Docker y Docker Compose.