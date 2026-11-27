# Finavizo API

**Português (Brasil)** · [English (US)](README.en_US.md)

A Finavizo é um projeto de API para finanças pessoais. Esta etapa inicial prepara o cadastro de usuários que poderão acessar o projeto. A API usa Spring Boot, verifica se o e-mail já está cadastrado e grava o usuário no PostgreSQL com a senha protegida por hash BCrypt.

## Executar localmente

Você precisa do JDK 25 e do Docker com o Docker Compose. Execute os comandos na raiz do projeto usando o Maven Wrapper incluído. No Windows, use `.\mvnw.cmd` no lugar de `./mvnw`.

**1. Configure o banco.** Crie um arquivo `.env` na raiz com os valores abaixo. Escolha uma senha para o ambiente local. O nome do banco em `DB_URL` deve corresponder a `POSTGRES_DB`.

```dotenv
POSTGRES_DB=finavizo_dev
POSTGRES_USER=finavizo_dev
POSTGRES_PASSWORD=replace-with-a-local-password
DB_URL=jdbc:postgresql://localhost:5432/finavizo_dev
```

**2. Inicie o PostgreSQL.** O Compose publica o banco na porta `5432`.

```bash
docker compose up -d db
```

**3. Inicie a API.** Quando o PostgreSQL estiver pronto para aceitar conexões, execute o comando abaixo. O Spring Boot lê o `.env` e inicia o servidor em `http://localhost:8080`. A geração do esquema pelo JPA está habilitada.

```bash
./mvnw spring-boot:run
```

## Cadastrar um usuário

Em outro terminal, envie uma requisição para `POST /api/v1/auth/signup`. Use o endereço de e-mail no campo `username`.

```bash
curl -i -X POST http://localhost:8080/api/v1/auth/signup \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alex Silva","username":"alex@example.com","password":"Example42!"}'
```

O cadastro retorna **HTTP 201 Created**. A resposta contém `id`, `name`, `username`, `password`, `enabled` e `createdAt`. O campo `password` contém o hash BCrypt. Novas contas começam com `enabled` igual a `false`.

## Testes e build

Com o PostgreSQL em execução, rode o teste de integração. Ele cadastra um usuário no banco configurado em `DB_URL`, verifica o status HTTP 201 e compara a senha da resposta com a enviada.

```bash
./mvnw test
```

Para executar os testes e gerar o JAR em `target/`, use o comando de build.

```bash
./mvnw package
```
