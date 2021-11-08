# Carteira de Investimentos
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

## Descrição do Projeto
Foi pensado e desenvolvido pensando na personagem fictícia Rafaela, que trabalha com *COMPRA* e *VENDA* de ações.
Os usuários podem visualizar, inserir, alterar, e remover transações que eles mesmos inseriram. E aqueles que possuem permissão de administrador, podem realizar as mesmas funções do CRUD com outros usuários também.

## Funcionalidades
`Transações`
1. Listar: Lista todas as **transações** registradas pelo usuário logado.
2. Cadastrar: Cadastra uma **transação** relacionada ao usuário.
3. Atualizar: Atualiza uma **transação** préviamente registrada pelo ID.
4. Remover: Remove uma **transação** pelo ID, feita pelo usuário.
5. Detalhar: Detalha uma **transação** escolhida pelo ID.

`Usuários`
As seguintes funções só podem ser utilizadas por usuários com a permissão de ADMIN.
1. Listar: Lista todos os **usuários** registrados.
2. Cadastrar: Cadastra um **usuário** e define seu nível de acesso.
3. Atualizar: Atualiza um **usuário** préviamente registrada pelo ID.
4. Remover: Remove um **usuário** pelo ID caso ele não tenha nenhuma **transação** registrada.
5. Detalhar: Detalha um **usuário** escolhida pelo ID.

`Relatório`
1. Apresenta um relatóio com a relação de todas as *ações compradas* no sistema, mostrando a procentagem delas relacionadas ao total.

## Acesso ao Projeto
Para acessar o projeto é só clicar [aqui](https://github.com/mateuscbarbosa/carteira-api)

## Abrir e Rodar
Para abrir o projeto é só fazer o pull daqui do github. E executá-lo na sua IDE de ```Java```
Algumas observações sobre isso:
* O Lombok precisa estar habilitado, pois foi usado em várias classes;
* As dependências do Maven precisam ser baixadas para que ele seja utilizado;
* O Banco de Dados MySQL foi utilizado, então precisa estar instalado;
* Depois de instalado o banco ***carteira*** precisa ser criado;
* E então as versões do FlyWay funcinarão;

## Tecnologias Utilizadas
* `Java`
* `SpringBoot`
* `SpringMVC`
* `Maven`
* `Lombok`
* `FlyWay`
* `JJWT`
* `ModelMapper`
* `BCryptPasswordEncoder`

## Autores
* Professores: Rodrigo Caneppele & Rita Cury
| <br>[<sub>Mateus C Barbosa</sub>](https://github.com/mateuscbarbosa)

