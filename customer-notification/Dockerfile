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
COPY --from=builder target/customer-notification-0.0.1-SNAPSHOT.jar /app/customer-notification.jar

WORKDIR /app

CMD ["java", "-jar", "customer-notification.jar"]