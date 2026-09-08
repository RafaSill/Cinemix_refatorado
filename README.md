# 🎥CINEMIX

O CINEMIX é uma aplicação desktop desenvolvida em Java para simular um sistema de catálogo, compra e aluguel de filmes.

O projeto permite o cadastro e autenticação de usuários, visualização do catálogo de filmes, cadastro de novos filmes por administradores e registro de compras e aluguéis utilizando diferentes formas de pagamento.

O sistema foi desenvolvido utilizando Java Swing para a interface gráfica, JPA/Hibernate para persistência de dados e MySQL como banco de dados.

## 📌 Funcionalidades

- Cadastro de usuários;
- Login com e-mail e senha;
- Diferenciação entre usuário comum e administrador;
- Cadastro de filmes por administradores;
- Listagem de filmes disponíveis no catálogo;
- Busca de filmes por título;
- Compra de filmes;
- Aluguel de filmes por período;
- Cálculo automático do valor do aluguel de acordo com a quantidade de dias;
- Seleção da forma de pagamento;
- Registro das compras e aluguéis no banco de dados.

## 👤 Tipos de usuário

O sistema possui dois tipos de usuário:

1. Usuário:
permissões:

- Consultar o catálogo;
- Comprar filmes;
- Alugar filmes.

2. Administrador:

Possui as funcionalidades do usuário comum e também pode:

- Cadastrar novos filmes no sistema.

Atualmente, durante o cadastro, um usuário é definido como administrador quando o e-mail informado começa com ADM.

Exemplo: ADM@email.com

## 🛠️ Tecnologias utilizadas
- Java 24
- Java Swing
- Maven
- Jakarta Persistence (JPA)
- Hibernate ORM 7.4.7.Final
- MySQL
- MySQL Connector/J 9.6.0

## 🧱 Organização das camadas

1. GUI
- Contém as telas da aplicação desenvolvidas com Java Swing.

2. Persistence
- Contém as entidades utilizadas pelo JPA para representar os dados armazenados no banco.

3. DAO
- Responsável pelas operações de acesso ao banco de dados, como cadastro, consulta e registro de compras e aluguéis.

4. EntityManager
- Contém a classe JPAUtil, responsável pela criação e gerenciamento do EntityManager utilizado pelo Hibernate.

## 🗄️ Banco de dados

O projeto utiliza um banco de dados MySQL chamado:

**cinemix_refatorado**

Crie o banco antes de executar a aplicação: 

**CREATE DATABASE cinemix_refatorado;**

A configuração atual da conexão está localizada em: 

**src/main/resources/META-INF/persistence.xml**

## Configuração do banco de dados

Configuração utilizada no projeto:

| Propriedade | Valor |
|---|---|
| `jakarta.persistence.jdbc.url` | `jdbc:mysql://localhost/cinemix_refatorado` |
| `jakarta.persistence.jdbc.user` | `root` |
| `jakarta.persistence.jdbc.password` | `root` |
          

Caso seu usuário ou senha do MySQL sejam diferentes, altere essas informações no arquivo persistence.xml.

O Hibernate está configurado com:

| `<property name="hibernate.hbm2ddl.auto" value="update"/>` |

Dessa forma, as tabelas mapeadas pelas entidades são criadas ou atualizadas automaticamente.

## 💳 Formas de pagamento

As formas de pagamento utilizadas na tela de compra e aluguel são carregadas da tabela TipoPagamento.

Após a criação das tabelas, você pode adicionar algumas opções manualmente no banco, por exemplo:

| Inserir valor | Valor |
|---|---|
| Tabela | `TipoPagamento` |
| Coluna | `tipoPagamento` |
| Valores | `PIX`, `Cartão`, `Dinheiro` |

## ▶️ Como executar o projeto

Pré-requisitos

Antes de começar, tenha instalado:

- Java JDK 24;

- MySQL;

- Apache Maven;

NetBeans ou outra IDE compatível com projetos Maven.

1. Clone o repositório

- git clone URL_DO_SEU_REPOSITORIO

- Entre na pasta do projeto:

- cd P.I03

2. Configure o banco de dados

Crie o banco:

- CREATE DATABASE cinemix_refatorado;

Depois, confira o usuário e a senha do MySQL no arquivo:

- src/main/resources/META-INF/persistence.xml

3. Baixe as dependências

Na pasta do projeto, execute:

- mvn clean install

O Maven irá baixar automaticamente o Hibernate e o MySQL Connector/J.

4. Inicie a aplicação

No NetBeans, abra o arquivo:

- src/main/java/GUI/login.java

Execute a classe login, que contém o método main e abre a tela inicial de autenticação do CINEMIX.

## 🎞️ Filmes

Cada filme possui informações como:

- Título;

- Gênero;

- Data de lançamento;

- Duração;

- Faixa etária;

- Preço de aluguel;

- Preço de compra.

O preço de compra possui atualmente o valor padrão de:

- R$ 25,00

O preço do aluguel é armazenado em cada filme e o valor final é calculado de acordo com a quantidade de dias entre a data de início e a data de devolução.

Exemplo:

Preço diário: R$ 5,00
Período: 3 dias
Total: R$ 15,00

## 🔐 Login

O login consulta os dados cadastrados no banco e valida o usuário através do e-mail e da senha.

E-mail + Senha → Validação no banco → Acesso à Home

Caso os dados estejam incorretos, o sistema informa que o usuário ou a senha são inválidos.

## 🧩 Principais entidades

1. Users

- Representa os usuários cadastrados no sistema.

2. Filmes

- Representa os filmes disponíveis no catálogo.

3. TipoPagamento

- Representa as formas de pagamento disponíveis.

4. ComprarFilmes

- Armazena os registros das compras realizadas pelos usuários.

5. AlugarFilmes

- Armazena os registros dos aluguéis, incluindo data de início, data de devolução e valor.

## 🚧 Melhorias futuras

- Algumas melhorias que podem ser implementadas futuramente:

- Criptografia das senhas dos usuários;

- Validação mais completa de e-mail e telefone;

- Tela com histórico de compras e aluguéis;

- Edição e exclusão de filmes;

- Cadastro de formas de pagamento pela aplicação;

- Melhorias na interface gráfica;

- Inclusão de capas e imagens dos filmes;

- Implementação de testes automatizados.

## 📚 Sobre o projeto

Projeto desenvolvido com o objetivo de aplicar conceitos de:

- Programação Orientada a Objetos;

- Interfaces gráficas com Java Swing;

- Persistência de dados;

- JPA e Hibernate;

- Banco de dados MySQL;

- Padrão DAO;

- Maven;

- Relacionamento entre entidades.
