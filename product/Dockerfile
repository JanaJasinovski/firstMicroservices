FROM openjdk:17
ADD /target/product-0.0.1-SNAPSHOT.jar backendProduct.jar
ARG PORT
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "backendProduct.jar"]