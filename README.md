# To-Do List Desafio Petize

Uma API Restful para gerenciamento de tarefas do desafio técnico da Petize.

---

## Tecnologias Utilizadas

- Java 22
- Spring Boot
- PostgreSQL
- Docker 

---

## Pré-requisitos

- Docker e Docker Compose instalados na máquina.
- (Opcional) Postman ou outro cliente HTTP para testar a API.

---

## Como rodar a aplicação

1. Faça o fork do repositório para a sua conta.
2. Clone o repositório na sua máquina.
3. No terminal, dentro da pasta do projeto, execute:
```
docker-compose up --build
```
5. Após subir os containers, a API estará disponível em `http://localhost:8080`.

6. Para acessar a documentação e testar os endpoints, abra no navegador:
```
http://localhost:8080/swagger-ui/index.html#/
```
## Funcionalidades Obrigatórias

- Criar tarefa com título, descrição, data de vencimento, status e prioridade. ✅ 
- Listar tarefas com filtros por status, prioridade e data de vencimento. ✅ 
- Atualizar o status da tarefa. ✅ 
- Deletar tarefa. ✅ 
- Impedir conclusão de tarefa quando houver subtarefas pendentes. ✅ 

## Funcionalidades Opcionais / Diferenciais

- Autenticação JWT com rotas protegidas. ✅ 
- Associação de tarefas ao usuário autenticado. ✅ 
- Validação de entrada usando `@Valid` com mensagens claras. ✅ 
- Documentação completa com Swagger. ✅ 
- Testes unitários e de integração. ⚠️
- Docker Compose configurado com banco de dados. ✅ 
- Paginação, ordenação e upload de anexos. ⚠️
```
