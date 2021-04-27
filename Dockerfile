# 도커 이미지 베이스가되는 환경
FROM adoptopenjdk/openjdk11:latest

# 작성자 정보
LABEL maintainer="alsgus2ek@gmail.com"

# 볼륨 추가 to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# jar파일 경로
ARG JAR_FILE=build/libs/todo-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} todo-springboot.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/todo-springboot.jar"]