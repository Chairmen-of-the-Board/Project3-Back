# Installation

Use the Git CLI [Git](https://git-scm.com/docs) to clone Project3-Back.

```bash
git clone https://github.com/Chairmen-of-the-Board/Project3-Back.git
```

change directory to Project3-Back.

```bash
cd Project3-Back
```

Create buildspec.yml
```bash
version: 0.2

phases:
  build:
    args:
      - DB_USERNAME=${DB_USERNAME}
      - PASSWORD=${PASSWORD}
    commands:
      - java -version
       - mvn clean package
artifacts:
  files:
    - 'Dockerfile'
    - target/curated-banking-spring-1.0-SNAPSHOT.jar
```

Create Dockerfile
```bash
FROM openjdk
#This is the enviornement we want the container to run in
COPY . /workspace
#This will copy everything in our project and put it into a folder called workspace
WORKDIR /workspace
#Tell it what folder to work out of
EXPOSE 8080
#Set Environment Variables
#ARG DB_USERNAME
#ARG PASSWORD
#ENV DB_USERNAME=$DB_USERNAME
#ENV PASSWORD=$PASSWORD
#Tell it what port to connect to
ENTRYPOINT [ "java", "-jar", "target/curated-banking-spring-1.0-SNAPSHOT.jar" ]

#FROM openjdk
#COPY /target/curated-banking-spring-1.0-SNAPSHOT-jar-with-dependencies.jar curated-banking-spring-1.0-SNAPSHOT-jar-with-dependencies.jar
#EXPOSE 8080
#ENTRYPOINT [ "java", "-jar", "/curated-banking-spring-1.0-SNAPSHOT-jar-with-dependencies.jar" ]
```

Create jar file by ```mvn clean package``` on the command-line. 





## Usage

Navigate to localhost:8080

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

Revature retains all rights
