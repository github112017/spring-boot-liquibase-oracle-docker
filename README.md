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
> mvn clean install -DskipTests && docker build -f automation/Dockerfile . -t microservice-liquibase-oracle

- Subir container Docker do Oracle: 
> docker run -d -p 49161:1521 --name oracle11g -v C:\Softwares\docker-volumes\Oracle-11g:/data oracleinanutshell/oracle-xe-11g

- Subir container Docker do H2:
> docker run -d -p 1521:1521 -p 81:81 -e H2_OPTIONS='-ifNotExists' --name h2 oscarfonts/h2

- Subir container Docker do Postgres:
> docker run -d -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=postgres postgres

- Subir container da aplicacao.
Obs.: Para que a aplicacao consiga acessar o oracle levantado como container, eh preciso setar o ip utilizado pelo container 
na propriedade 'spring.datasource.url' (application-docker.properties). No caso do Windows, eh possivel saber usando o comando
'ipconfig /all' no prompt cmd, e entao ver o ip usado pelo Hiper-V.
> docker run -d -p 8080:8080 --name microservice-livraria microservice-liquibase-oracle

- Parar container especifico: docker stop <nome_container>
> docker stop microservice-livraria && docker stop oracle11g

- Parar todos os containers Docker: 
> docker stop $(docker ps -a -q)

- Deletar container especifico (que esteja parado): docker rm <nome_container>
> docker rm microservice-livraria && docker rm oracle11g 

- Deletar todos os containers Docker que estejam parados: 
> docker rm $(docker ps -a -q)

- Deletar imagem Docker: docker rmi <nome da imagem, que eh a concatenacao com : do repositorio e tag (repository:tag)>
> docker rmi microservice-liquibase-oracle:latest

- Subir Portainer Docker (Extra para administracao de containers. Usar tambem versao mais recente do Docker Desktop)
> docker run -d -p 9000:9000 --name portainer --restart always -v /var/run/docker.sock:/var/run/docker.sock -v C:\Softwares\docker-volumes\Portainer:/data portainer/portainer

## Web Sources: 
https://hub.docker.com/r/oracleinanutshell/oracle-xe-11g
  