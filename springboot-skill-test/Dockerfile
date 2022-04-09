FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
EXPOSE 9898
ARG JAR_FILE=target/springboot-skill-test-PRE.1.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]