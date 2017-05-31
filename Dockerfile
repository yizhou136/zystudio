# First stage to build the application
#FROM maven:3.5.0-jdk-8-alpine AS build-env # build-env构建阶段的开始
FROM maven:3.5.0-jdk-8-alpine AS build-env
ADD ./pom.xml pom.xml
ADD ./src src/
RUN mvn clean package

# Final stage to define our minimal runtime
FROM FROM openjdk:8-jre
#FROM fabric8/java-jboss-openjdk8-jdk
VOLUME /tmp
#ADD build/libs/*.jar app.jar
COPY --from=build-env target/app.jar app.jar

RUN echo $(date) > /image_built_at
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
