FROM openjdk:17

LABEL maintainer="infomuscle10@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/portfolio-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} portfolio-yongback.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/portfolio-yongback.jar"]
