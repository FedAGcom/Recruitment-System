FROM openjdk:11
ADD . /src
WORKDIR /src
EXPOSE 8080
CMD ["java","-jar","target/recruitmentSystem-0.0.1-SNAPSHOT.jar"]