FROM azul/zulu-openjdk:8-latest
WORKDIR /app
COPY ./ ./

RUN chmod 777 gradlew
RUN ./gradlew bootjar

ENV db_username=${db_username}
ENV db_password=${db_password}
ENV datasource=${datasource}

EXPOSE 9560
CMD java -jar build/libs/*.jar