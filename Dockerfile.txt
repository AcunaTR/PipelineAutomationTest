FROM openjdk:8-jre
COPY ./jowi-0.1.0.jar /usr
WORKDIR /usr

CMD  ["java","-cp", "jowi-0.1.0.jar", "main.java.Examples.Basic.Program"]
