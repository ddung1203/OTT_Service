FROM gradle:jdk8-focal
WORKDIR /app
COPY ./ ./

RUN gradle build
EXPOSE 8080
CMD java -jar build/libs/apigateway-1.0.jar