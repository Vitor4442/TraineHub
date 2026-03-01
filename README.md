# 🏋️ TrainerHub - Centro de Treinamento

Sistema para gerenciamento de treinos, fichas e alunos, desenvolvido com **Java + Spring Boot**.

## 📌 Sobre o Projeto

O TrainerHub é uma aplicação backend criada para organizar e gerenciar:

- 👤 Alunos
- 📋 Fichas de treino
- 📊 Relacionamentos entre alunos e treinos
- 🗂 Estrutura organizada com DTOs, Mappers e Entities

O objetivo é fornecer uma API REST estruturada para controle de treinos de forma escalável e organizada.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- Banco de Dados (MySQL ou PostgreSQL)
- Maven

---

## 📂 Estrutura do Projeto


src/main/java/com/vtr/exercises
│
├── controller → Controladores REST
├── service → Regras de negócio
├── repository → Comunicação com banco
├── model → Entidades JPA
├── dto → Objetos de transferência
├── mapper → Conversão Entity ↔ DTO


---

## 🧱 Modelo de Dados (Exemplo)

### Ficha

- id
- student_id (ManyToOne)
- nome
- descricao
- created_at
- updated_at

Relacionamento:
> Um aluno pode ter várias fichas de treino.

---

## 🚀 Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/Vitor4442/TraineHub.git
2️⃣ Entrar na pasta
cd TraineHub
3️⃣ Configurar o application.properties

Exemplo para MySQL:

spring.datasource.url=jdbc:mysql://localhost:3306/trainerhub
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
4️⃣ Rodar a aplicação
./mvnw spring-boot:run

Ou pela IDE (Run Application).
