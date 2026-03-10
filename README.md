# 📋 Challenge Fórum Hub - Alura

Este projeto é uma API REST para um fórum de discussões, desenvolvida como o desafio final da formação Java e Spring Boot da Alura. A API permite criar, listar, atualizar e excluir tópicos, contando com um sistema de segurança robusto usando JWT (JSON Web Token).

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (Persistência de dados)
- **Spring Security** (Segurança e Autenticação)
- **PostgreSQL** (Banco de dados relacional)
- **Lombok** (Produtividade)
- **Flyway** (Gerenciamento de banco de dados)

## 🔐 Funcionalidades de Segurança
- **Autenticação Stateless:** A API não guarda sessão, exigindo um token para cada requisição.
- **JWT:** Geração e validação de tokens para acesso protegido.
- **BCrypt:** Criptografia de senhas para maior segurança.

## 📁 Endpoints da API

### Segurança
- `POST /login/registrar`: Cadastra um novo usuário no sistema.
- `POST /login`: Realiza o login e retorna o Token JWT.

### Tópicos (Exige Token Bearer)
- `POST /topicos`: Cadastra um novo tópico (não permite duplicatas).
- `GET /topicos`: Lista todos os tópicos cadastrados.
- `GET /topicos/{id}`: Detalha um tópico específico.
- `PUT /topicos`: Atualiza os dados de um tópico.
- `DELETE /topicos/{id}`: Remove um tópico do sistema.

## 🛠️ Como Executar o Projeto
1. **Configurar Banco de Dados:** Crie um banco chamado `forum_hub` no PostgreSQL.
2. **Configurar Senha:** No arquivo `src/main/resources/application.properties`, ajuste o campo `spring.datasource.password` com a sua senha do banco.
3. **Executar:** Rode a classe `ForumHubApplication.java` no seu IntelliJ.
4. **Testar:** Use o Insomnia ou Postman para enviar as requisições.

---
Desenvolvido por você durante o Challenge da Alura! 👊