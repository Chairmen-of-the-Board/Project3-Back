FROM openjdk
#This is the enviornement we want the container to run in
COPY . /workspace
#This will copy everything in our project and put it into a folder called workspace
WORKDIR /workspace
#Tell it what folder to work out of
EXPOSE 8080
#Set Environment Variables
ARG DB_USERNAME
ARG PASSWORD
ENV DB_USERNAME=$DB_USERNAME
ENV PASSWORD=$PASSWORD
#Tell it what port to connect to
ENTRYPOINT [ "java", "-jar", "target/curated-banking-spring-1.0-SNAPSHOT-jar-with-dependencies.jar" ]

#FROM openjdk
#COPY /target/curated-banking-spring-1.0-SNAPSHOT-jar-with-dependencies.jar curated-banking-spring-1.0-SNAPSHOT-jar-with-dependencies.jar
#EXPOSE 8080
#ENTRYPOINT [ "java", "-jar", "/curated-banking-spring-1.0-SNAPSHOT-jar-with-dependencies.jar" ]
