package com.restapi.rcmn.reqeust;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RequestTest {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void Mono_실습_1() {


        String result = blockingHelloWorld().block();
        assertEquals("Hello world!", result);
    }

    @Test
    public void Mono_실습_2() {
        String result = blockingHelloWorld().block(Duration.of(1000, ChronoUnit.MILLIS));
        assertEquals("Hello world!", result);
    }

    @Test
    public void Mono_실습_3() {
        Optional<String> result = Mono.<String>empty().blockOptional();
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void WebClient_Test() {
        String movies = "Avatar@Iron Man 3";
        String BASE_URL = "asdasasdasf";

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .build();

        String response = webClient.get()
                .uri("/masdasd")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        assertEquals("success", response);
    }

    @Test
    public void WebClient_Test2() {
        String movies = "{\n\t\"movie_name\":\"Star Wars@The Hobbit: The Battle of the Five Armies@Iron Man\"\n}";
        String testString = "Avengers: Age of Ultron@The Dark Knight Rises@Star Wars@The Hobbit: The Battle of the Five Armies@Iron Man";
        String sumString = String.format("{\n\t\"movie_name\":\"%s\"\n}",testString);
        String BASE_URL = "asdfasdf";

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        List<String> movieList = webClient.post()
                .uri("postrecommendapikey")
                .bodyValue(sumString)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        System.out.println(movieList.size());
        for (String movie : movieList) {
            System.out.println(movie);
        }
    }
    Mono<String> blockingHelloWorld() {
        return Mono.just("Hello world!");
    }
}
