FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar consumer.jar
ENTRYPOINT ["java","-jar","/consumer.jar"]