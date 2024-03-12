FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar supplier.jar
ENTRYPOINT ["java","-jar","/supplier.jar"]