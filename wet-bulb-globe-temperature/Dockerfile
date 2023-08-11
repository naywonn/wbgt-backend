# Use the official Java 17 image as the base image
FROM openjdk:17-jdk


# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/wet-bulb-globe-temperature-0.0.1-SNAPSHOT.jar app.jar

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
