FROM openjdk:21

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/portfolio-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} portfolio-yongback.jar

ENTRYPOINT ["java", "-jar", "/portfolio-yongback.jar"]