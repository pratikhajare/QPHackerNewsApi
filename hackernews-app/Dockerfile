#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/hackernews-app-0.0.1-SNAPSHOT.jar
#copy ${JAR_FILE} hackernews-app.jar
#run mkdir -p /config
#copy src/main/resources /config
#ENTRYPOINT ["sh", "-c", "java -Xms512m -Xmx1g -XX:+UseStringDeduplication -jar /hackernews-app.jar"]

FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG ARG JAR_FILE=target/hackernews-app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} hackernews-app.jar
run mkdir -p /config
copy src/main/resources /config
ENTRYPOINT ["java","-jar","/hackernews-app.jar"]

#docker build -t hackernews-app.jar
#docker image ls
#docker run -p 9090:8080 hackernews-app.jar