FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG ARG JAR_FILE=target/hackernews-app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} hackernews-app.jar
run mkdir -p /config
copy src/main/resources /config
ENTRYPOINT ["java","-jar","/hackernews-app.jar"]

#docker build -t hackernews-app.jar .
#docker image ls
#docker run -p 9090:8080 hackernews-app.jar 

#docker run --p 9090:8080 --name hackernews-app.jar -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 hackernews-app 