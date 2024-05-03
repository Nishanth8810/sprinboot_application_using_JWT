FROM eclipse-temurin:17-jdk-alpine
RUN apk add curl
VOLUME /tmp
ENV JAVA_OPTS=""
EXPOSE 9090
ADD target/springboot-aws-deploy-jwt.jar springboot-aws-deploy-jwt.jar
ENTRYPOINT ["java","-jar","/springboot-aws-deploy-jwt.jar"]