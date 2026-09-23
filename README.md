# DevShowcase API

API REST desenvolvida em Java e Spring Boot para gerenciamento de perfis, projetos, tecnologias e feedbacks.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Jakarta Validation
- Postman

## Funcionalidades

A API permite:

- Cadastrar perfis
- Consultar perfis
- Cadastrar tecnologias
- Listar tecnologias
- Cadastrar projetos
- Listar projetos
- Relacionar projetos e tecnologias
- Relacionar projetos e perfis
- Armazenar feedbacks relacionados aos projetos

## Relacionamentos

- Profile 1:N Project
- Project N:N Technology
- Project 1:N Feedback

## Endpoints

### Profiles

POST /api/profiles

GET /api/profiles/{id}

### Technologies

POST /api/technologies

GET /api/technologies

### Projects

POST /api/projects

GET /api/projects

## Executando o projeto

1. Instale o Java 21.
2. Instale o MySQL.
3. Crie o banco de dados `devshowcase`.
4. Configure a variável de ambiente `DB_PASSWORD`.
5. Execute:

```powershell
.\mvnw.cmd spring-boot:run