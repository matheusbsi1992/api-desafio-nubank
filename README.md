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
- **Redis** (cacheamento opcional do responsável ao projeto)

---

## ⚙️ Configurações de Ambiente

### Produção (`application.yaml`)
```yaml
server:
  port: 9293
spring:
  cache:
    type: redis
  data:
    redis:
      host: redis
      port: 6379
  # profiles:
  #  active: dev
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/api-nubank-desafio #${SPRING_DATASOURCE_URL}
    username: postgres #${SPRING_DATASOURCE_USERNAME}
    password: postgres #${SPRING_DATASOURCE_PASSWORD}
jpa:
  open-in-view: true
  hibernate:
    ddl-auto: update
    show-sql: true
    format-sql: true
flyway:
  create-schemas: true
  enabled: true
  locations:
    classpath: db/migration
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
#version: "3.9"

services:
  # Cache
  redis:
    image: redis:8.2
    container_name: redis
    command: [
      "redis-server",
      "--save", "",
      "--appendonly", "no",
      "--databases", "1",
      "--loglevel", "warning",
      "--logfile", "/dev/null",
      "--tcp-keepalive", "60"
    ]
    healthcheck:
      test: [ "CMD", "redis-cli", "ping" ]
      interval: 5s
      timeout: 3s
      retries: 5
    ports:
      - "6379:6379"
  # DB PostgreSQL
  postgres:
    image:
      postgres:15
    container_name: api-nubank-base-de-dados
    restart: always
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: api-nubank-desafio
    ports:
      - "5432:5432"
    expose:
      - "5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: [ "CMD-SHELL", "pg_isready -U postgres -d api-nubank-desafio" ]
      interval: 5s
      timeout: 5s
      retries: 10
  # Image da aplicação
  app:
    build: .
    container_name: api-nubank-desafio
    depends_on:
      - postgres
      #- redis
      #Dependência do DB criada anteriormente
      #O desafio nao pedi o redis como alto dependencia - redis
      #condition: service_healthy
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/api-nubank-desafio
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
      SERVER_PORT: 9393
      SPRING_REDIS_HOST: redis
      SPRING_REDIS_PORT: 6379
      #SPRING_FLYWAY_SCHEMAS: magalu
    ports:
      - "9393:9393"
volumes: # Adicione esta seção!
  postgres_data:  # Declaração do volume
```
### Execução do Docker Compose

```
   docker-compose down (Remove cada IMAGE criada anteriormente)
   docker-compose build --no-cache (Nova construção sem o cache anterior)
   docker compose up -d (Subir cada docker em background)
   docker-compose logs app (Visualizar os logs do app)

```

---

### 📑 Documentação da API

``` Acesse a documentação interativa via Swagger: http://localhost:9293/swagger-ui/index.html ```

---

### 📂 Estrutura do Projeto

```
src
 ├── main
 │   ├── java/com/br/nubank
 │   │   ├── config       # Integração com o SWAGGER
 │   │   ├── controller   # Endpoints REST
 │   │   ├── dto          # Objetos de transferência
 │   │   ├── exceptions   # Exceções de status code e bindinresult
 │   │   ├── execucao     # Execução do projeto
 │   │   ├── mapper       # Mapeamento (MapStruct)
 │   │   ├── model        # Entidades
 │   │   ├── repository   # Integração com JPA
 │   │   ├── service      # Regras de negócio
 │   │   └── validacao    # Validacoes de error
 │   └── resources
 │       ├── db/migration # Scripts Flyway
 │       ├── application.yaml # Configurações de Servidor Web e Banco de Dados Local
 └── test
     └── java/com/br/nubank/teste # Testes unitários e de integração
         └── repository       # SpringBootTest
     └── resources
         └── application-test.properties # Configurações de Banco de Dados em memória
```
