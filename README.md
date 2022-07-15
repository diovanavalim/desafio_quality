<h1 align="center">✅ Desafio Quality - Wave 6 ✅</h1>
<p align="center">Repositório criado para hospedar o código do segundo Touchpoint do Bootcamp IT Java.</p>

<p align="center">
  <img align="" alt="mascoteJava" src="https://github.com/amarinellif/boot-dh-testes/blob/main/developer-tester.gif" height="70%" width="70%" />
</p>

<p align="center">
 <a href="#objetivo">Sobre o Projeto</a> •
 <a href="#Requisitos">Requisitos</a> • 
 <a href="#Tecnologias">Tecnologias</a> • 
 <a href="#Pré-requisitos">Pré-requisitos</a> • 
 <a href="#Rodando o Servidor">Rodando o Servidor</a> • 
 <a href="#Dependências">Dependências</a> • 
 <a href="#Coleção-de-Requisições">Coleção de Requisições</a> •
 <a href="#Autores">Autores</a> •
</p>

## 🖊 Sobre o Projeto
<p> 
O objetivo do projeto é desenvolver uma API Rest, escrita na linguagem Java. Esta API está voltada ao contexto de vendas de imóveis. O programa viabiliza a partir de um banco de imóveis cadastrados com nome, bairro (nome e valor do metro quadrado) e uma lista de cômodos (nome, comprimento e largura), retornar o nome, bairro, área total, o preço total, a lista de cômodos com suas respectivas áreas e o maior cômodo da propriedade.
</p>

___

## 📄 Requisitos
<p> Obrigatórios (RO)</p>
<p>
  
- RO-01 Calcular a área total de uma propriedade (US-0001);
- RO-02 Indicar o preço dessa mesma propriedade com base na área total (US-0002);
- RO-03 Determinar qual é o maior cômodo da propriedade (US-0003);
- RO-04 Determinar a quantidade de metros quadrados que tem cada cômodo de uma propriedade (US-0004);
- RO-05 Fazer os testes unitários (US-0005);
- RO-06 Fazer as devidas validações, conforme especificado (US-0006);

</p>
- Melhorias implementadas (SM)
  - 

___

## 🛠 Tecnologias

A API foi construída utilizando a linguagem Java, com o framework Spring Boot.

___

## ✅ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java](https://www.java.com/pt-BR/) e um editor de código a sua escolha. Sugere-se o uso do Intelij Idea, disponível [aqui](https://www.jetbrains.com/pt-br/idea/).

___

## 🎲 Rodando o Servidor

- Clone este repositório na sua máquina:
    
      git clone https://github.com/diovanavalim/desafio_spring.git

- Acesse a pasta do projeto no terminal:

      cd desafio_spring

- Instale as dependências do projeto:

      mvn clean install
  
- Execute o servidor:

      mvn spring-boot:run

O servidor estará sendo escutado na porta indicada na IDE. Geralmente, trata-se da porta 8080.

___

## 🏁 Dependências  

Foram utilizadas no projeto as seguintes dependências:

- 🍃 Spring Boot Dev Tools;

- 🍃 Spring Boot Web;

- 🌶️ Lombok;

- 🍃 Spring Boot Stater Validations;

___

## 🌙 Coleção de Requisições 

/////A coleção de requisições utilizadas para testar os endpoints estão na pasta `src/main/resource`. O arquivo `Desafio-spring.postman_collection.json` pode ser importado em algum cliente HTTP, como o Postman ou o Insomnia./////

---

## <img alt="coffee_cup" src="https://user-images.githubusercontent.com/80721339/173413428-56d4f208-6f5f-437d-ad91-cb878a90a01a.png" width="30px" /> Java Docs

Criamos também uma documentação da aplicação, utilizando a ferramenta Java Docs, que pode ser consultada seguindo os passos a seguir: 

1 - À partir da IDE de sua preferência, basta rodar o comando: `mvn javadoc:javadoc`. Esse comando fará o build da documentação e criará uma pasta chamada **target**.

2 - Agora basta localizar o arquivo `index-all.html` -> clicar com o botão direito em "Open" -> escolher o browser de sua preferência. Ao clicar, será aberta uma página contendo a documentação.

___
## 📝 Autores ##

Desenvolvido com 💛 por Amanda, Diovana, Gabriela, Rafael, Thiago Frozzi e Thiago Almeida. 

