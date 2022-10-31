FROM azul/zulu-openjdk:8-latest
WORKDIR /app
COPY ./ ./

RUN chmod 777 gradlew
RUN ./gradlew bootjar

EXPOSE 9560
CMD java -jar build/libs/*.jar