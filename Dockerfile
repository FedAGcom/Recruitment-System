FROM openjdk:11
COPY target/recruitmentSystem-0.0.1-SNAPSHOT.jar /recruitmentSystem.jar
CMD ["java","-jar","/recruitmentSystem.jar"]