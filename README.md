# spring-boot-liquibase-oracle-docker
Projeto de estudo/testes com liquibase, oracle, docker.

## Características

- Java 8
- Frameworks: Spring Boot, JPA, Maven
- Padrão MVC (com Entity e DTO), Service e DAO
- Restful
- Oracle (Imagem Docker. Ver link de referencia, em Web Sources, de como configurar)
- Liquibase

## Como rodar:

- Subir aplicação Spring Boot via comando: 
> mvn spring-boot:run

- Caso queira gerar o script sql completo esteja no arquivo liquibase-spring-boot-liquibase-oracle-docker.sql, 
eh preciso antes deletar o arquivo databasechangelog.csv e depois rodar o comando: 
> mvn clean install -DskipTests

- Fazer o build da imagem da aplicacao e instalar no Docker (Docker local e rodando), estando no diretorio raiz do projeto:
> mvn clean install -DskipTests && docker build -f automation/Dockerfile . -t spring-boot-liquibase-oracle-docker

- Subir container Docker do Oracle: 
> docker run -d -p 49161:1521 --name oracle11g oracleinanutshell/oracle-xe-11g

- Subir container da aplicacao de maneira iterativa com o container oracle (usando Windows, funciona no prompt cmd).
Obs.: Para que a aplicacao consiga acessar o oracle levantado como container, eh preciso setar o ip utilizado pelo container 
na propriedade 'spring.datasource.url' (application-docker.properties). No caso do Windows, eh possivel saber usando o comando
'ipconfig /all' no prompt cmd, e entao ver o ip usado pelo adaptador DockerNat.
> docker run -it --link oracle11g -p 8081:8081 --name app-liquibase-oracle spring-boot-liquibase-oracle-docker

- Parar container especifico: docker stop <nome_container>
> docker stop app-liquibase-oracle
> docker stop oracle11g

- Parar todos os containers Docker: 
> docker stop $(docker ps -a -q)

- Deletar container especifico (que esteja parado): docker rm <nome_container>
> docker rm app-liquibase-oracle
> docker rm oracle11g 

- Deletar todos os containers Docker que estejam parados: 
> docker rm $(docker ps -a -q)

- Deletar imagem Docker: docker rmi <nome da imagem, que eh a concatenacao com : do repositorio e tag (repository:tag)>
> docker rmi spring-boot-liquibase-oracle-docker:latest

## Web Sources: 
https://hub.docker.com/r/oracleinanutshell/oracle-xe-11g
  