# spring-boot-liquibase-oracle-docker
Projeto de estudo/testes com liquibase, oracle, docker.

## Características

Backend:
- Java 8
- Frameworks: Spring Boot, JPA, Maven
- Padrão MVC (com Entity e DTO), Service e DAO
- Restful
- Oracle (Imagem Docker. Ver link de referencia de como configurar em Web Sources)
- Liquibase

Frontend:

## Como rodar:

- Subir container Docker do Oracle: docker run -d -p 49161:1521 oracleinanutshell/oracle-xe-11g
- Parar todos os containers Docker: docker stop $(docker ps -a -q)
- Subir aplicação Spring Boot via comando: mvn spring-boot:run 
- Caso queira gerar o script sql completo esteja no arquivo liquibase-spring-boot-liquibase-oracle-docker.sql, 
eh preciso antes deletar o arquivo databasechangelog.csv e depois rodar o comando: mvn clean install -DskipTests

## Web Sources: 
https://hub.docker.com/r/oracleinanutshell/oracle-xe-11g
  