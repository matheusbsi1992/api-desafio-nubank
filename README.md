# 🚀 API Nubank Desafio

Este projeto é uma **API REST** desenvolvida em **Spring Boot 3.5.4** com **Java 21**, criada como parte de um desafio técnico.  
A aplicação segue boas práticas de arquitetura, utilizando **JPA/Hibernate**, **Flyway** para versionamento de banco, **MapStruct** para mapeamento de DTOs, **Swagger/OpenAPI** para documentação interativa e **Docker Compose** para orquestração de containers.

---

## 📦 Tecnologias Utilizadas
- **Java 21**  
- **Spring Boot 3.5.4**  
  - Spring Web  
  - Spring Data JPA  
  - Spring Validation  
- **PostgreSQL** (produção)  
- **H2 Database** (testes, em modo PostgreSQL)  
- **Flyway** (migração e versionamento de banco de dados)  
- **MapStruct** (conversão de DTO ↔ Entidades)  
- **Lombok** (redução de boilerplate)  
- **Swagger / OpenAPI** (documentação e testes de endpoints)  
- **JUnit / Spring Boot Test** (testes automatizados)  
- **Docker & Docker Compose**  

---

## ⚙️ Configurações de Ambiente

### Produção (`application.yaml`)
```yaml
server:
  port: 9293

spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/api-nubank-desafio
    username: postgres
    password: postgres
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      show-sql: true
      format-sql: true
flyway:
  create-schemas: true
  enabled: true
  locations: classpath:db/migration
  baseline-on-migrate: true
spring-doc:
  pathsToMatch:
    - /**
swagger-ui:
  user-root-path: true
```

### Testes (application-test.properties)

```
spring.datasource.url=jdbc:h2:mem:testdb;MODE=PostgreSQL;INIT=CREATE SCHEMA IF NOT EXISTS APINUBANKDESAFIO
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=false

```

### Docker Compose (docker-compose.yaml)

```yaml
services:
  postgres:
    image: postgres:15
    container_name: api-nubank-base-de-dados
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: api-nubank-desafio
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: [ "CMD-SHELL", "pg_isready -U postgres -d api-nubank-desafio" ]
      interval: 5s
      timeout: 5s
      retries: 10

  app:
    build: .
    container_name: api-nubank-desafio
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/api-nubank-desafio
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
    ports:
      - "9393:9393"

volumes:
  postgres_data:
```
### Execução do Docker Compose
``` docker compose up -d ```

---

### 📑 Documentação da API

``` Acesse a documentação interativa via Swagger: http://localhost:9293/swagger-ui.html ```

---

### 📂 Estrutura do Projeto

src
 ├── main
 │   ├── java/com/br/nubank
 │   │   ├── config       # Integração com o SWAGGER
 │   │   ├── controller   # Endpoints REST
 │   │   ├── dto          # Objetos de transferência
 │   │   ├── exceptions   # Exceções de status code e bindinresult
 │   │   ├── execucao     # Execução do projetos
 │   │   ├── service      # Regras de negócio
 │   │   ├── repository   # Integração com JPA
 │   │   ├── model        # Entidades JPA
 │   │   └── mapper       # MapStruct
 │   └── resources
 │       ├── db/migration # Scripts Flyway
 │       ├── application.yaml # Configurações de Servidor Web e Banco de Dados Local
 │       └── ...
 └── test
     └── java/com/br/nubank # Testes unitários e de integração

