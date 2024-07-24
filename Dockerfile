# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-slim-buster

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy only the built JAR file into the container
COPY target/MattrIssuer.jar /usr/src/app/app.jar

EXPOSE 8980

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]