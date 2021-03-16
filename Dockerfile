# pull official base image
FROM openjdk:8-jdk-alpine

# copy files
COPY target/coinscombination.api-1.0.jar coinscombination.api-1.0.jar

# expose port
EXPOSE 8080

#start app
ENTRYPOINT ["java","-jar","/coinscombination.api-1.0.jar"]