# Autorizador
Projeto desenvolvido para mostrar um exemplo de Autorizador de Transações de Cartoes

##Tecnologias
Para o desenvolvimento da aplicação, foram utilizadas às tecnologias e libs abaixo:

| Nome        | Versão         |
|:------------|:---------------|
| Java        | JDK 17         |
| Maven       |                |
| Spring Boot | 3.3.2          |
| Powermock   | 2.0.9          | 
|
##Pré requisitos de tecnologia
- Instalar o _**Java 17**_ </br>
- Instalar o _**Maven**_ </br>

##Build
- Fazer o build da aplicação através do comando `mvn clean install`(_**Necessário ter o maven instalado**_)).

##Execução
- Executar a aplicação através do comando `java -jar target/Caju-Autorizador-0.0.1-SNAPSHOT.jar `;
- Pontos a serem observados no processo de execução são:
    - Execução na porta _**8080**_;
    - Acesso através do swagger-ui = (http://localhost:8080/swagger-ui/index.html)

##Atenção
1) Foram inseridos na inicialização do sistema os seguintes dados:

- Client (CLIENT_ID,NAME, EMAIL,MCC) 
  VALUES (1,'Caju', 'caju@caju.com','5811');
- Client (CLIENT_ID, NAME, EMAIL,MCC) 
  VALUES (2, 'Marcelo', 'marcelo@caju.com', '5411');

- Account (ACCOUNT_ID, CLIENT_ID, BALANCE_FOOD, BALANCE_MEAL, BALANCE_CASH) 
  VALUES (1,1,1000,2000,3000);
- Account (ACCOUNT_ID, CLIENT_ID, BALANCE_FOOD, BALANCE_MEAL, BALANCE_CASH) 
  VALUES (2,2,1000,2000,3000);

2) Foram criados os end points de listagem para os clientes e contas!<br>

3) Resposta do desafio L4. Questão aberta:<brS>
   Uma alternativa para esse problema é usar o Spring Data JPA, que possui um recurso chamado locking. 
   Esse recurso garante que apenas uma transação seja processada por vez.
