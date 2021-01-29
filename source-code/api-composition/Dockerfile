# Build command
#
# 1. gradle clean bootJar
# 2. docker build --label api-composition --tag api-composition:1.0.0 . 
# 3. docker tag api-composition [dockerhub-username]/api-composition
# 4. docker push [dockerhub-username]/api-composition

# Start with a base image containing Java runtime
FROM openjdk:13-slim

# create folder for external config
RUN mkdir conf
# COPY conf/gateway.yml conf

# Make port 9992 available to the world outside this container
EXPOSE 9992

# The application's jar file
ARG JAR_FILE=build/libs/api-composition-1.0.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} api-composition.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api-composition.jar"]