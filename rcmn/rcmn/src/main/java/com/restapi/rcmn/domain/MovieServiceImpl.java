package com.restapi.rcmn.domain;

import com.restapi.rcmn.domain.db.entity.Movies;
import com.restapi.rcmn.domain.db.entity.MoviesMember;
import com.restapi.rcmn.domain.db.repository.MoviesMemberRepository;
import com.restapi.rcmn.domain.db.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl {

    private final MoviesRepository moviesRepository;
    private final MoviesMemberRepository moviesMemberRepository;

    private final WebClient webClient;

    public List<Movies> rcmnMovies(long memberId) {
        StringBuilder sb = new StringBuilder("");

        List<String> movieTitleList = moviesMemberRepository.findByMemberId(memberId).stream()
                .sorted(Comparator.comparing(MoviesMember::getUpdateDt))
                .limit(5)
                .map(MoviesMember::getMovies)
                .map(Movies::getMoviesTitle)
                .collect(Collectors.toList());

        for (String movieTitle : movieTitleList) {
            sb.append("@").append(movieTitle);
        }

        sb.deleteCharAt(0);

        String jsonString = String.format("{\n\t\"movie_name\":\"%s\"\n}", sb.toString());

        List<String> rcmnList = webClient.post()
                .uri("postrecommendapikey")
                .bodyValue(jsonString)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        List<Movies> moviesList = new ArrayList<>();

        for (String movieTitle: rcmnList) {
            Movies movie = moviesRepository.findByMoviesTitle(movieTitle).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 영화이름입니다. : " + movieTitle)
            );

            moviesList.add(movie);
        }

        return moviesList;
    }
}
