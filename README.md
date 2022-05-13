# Finances-API: Uma API RESTful para controle financeiro

<p align="center">
<img src="https://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge" alt="Em desenvolvimento"/>
</p>

## Tecnologias utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

## Descrição
Essa é uma API RESTful desenvolvida como desafio para praticar e aprender sobre backend, através do Challenge Backend
da Alura. Essa API é responsável por realizar controle financeiro de forma simples.

## Confira o Deploy da aplicação
[HEROKU](https://finances-api-nevitoniuri.herokuapp.com/swagger-ui/index.html)

## Desafios sugeridos
* API com rotas implementadas seguindo as boas práticas do modelo REST 
* Validações feitas conforme as regras de negócio
* Implementação de base de dados para persistência das informações
* Serviço de autenticação/autorização para restringir acesso às informações

## Funcionalidades já implementadas

✔️ Capaz de adicionar, consultar, atualizar e deletar **Despesas**.\
✔️ Capaz de adicionar, consultar, atualizar e deletar **Receitas**.\
✔️ Gera um **resumo mensal** com o total das receitas, despesas, **saldo final** e o **total gasto por categoria**.\
✔️ Deploy da API no Heroku.

## O que ainda será desenvolvido?

📝 Adicionar o controle de acesso (autenticação e autorização)\
📝 Melhorar a documentação da API

## Rodando a aplicação ▶️
#### Requisitos
* [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
* [Docker](https://www.docker.com/)
* [Maven](https://maven.apache.org/)
* [Git](https://git-scm.com/)

#### Passo 1. Clonar o repositório
Rodar o comando abaixo no Git Bash para clonar o repositório:
```
git clone git@github.com:nevitoniuri/finances-api.git
```
#### Passo 2. Subir o container do Docker para criar o banco de dados MySQL
No diretório raiz do projeto, execute o comando:
```
docker-compose up -d
```
#### Passo 3. Acessar a documentação da API
```
http://localhost:8080/swagger-ui.html
```
#### Além disso, também disponibilizei a Collection do Postman para testes
* [Collection Postman](https://github.com/nevitoniuri/finances-api/blob/ccc8c735a52b48febc066443508e3f2bef4c6bbe/Postman/finances-api.postman_collection.json)