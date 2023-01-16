# FROM eclipse-temurin:17-jdk-jammy as builder
# WORKDIR /opt/app
# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./
# COPY ./src ./src
# RUN ./mvnw clean install 
 
 
# FROM eclipse-temurin:17-jre-jammy
# WORKDIR /opt/app
# EXPOSE 8080
# COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
# ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]

FROM maven:3.8.6-eclipse-temurin-17
WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
 
COPY src ./src
 
CMD ["mvn", "clean","spring-boot:run"]