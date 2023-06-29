# First stage: complete build environment
FROM maven:3.9.3-eclipse-temurin-17-alpine AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package

# Second stage: minimal runtime environment
From eclipse-temurin:17-jre-alpine

RUN mkdir /app

# copy jar from the first stage
COPY --from=builder target/customer-registration-0.0.1-SNAPSHOT.jar /app/customer-registration.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "customer-registration.jar"]