package com.restapi.rcmn.domain;

import com.restapi.rcmn.domain.db.entity.Movies;
import com.restapi.rcmn.domain.db.entity.MoviesMember;
import com.restapi.rcmn.domain.db.repository.MoviesMemberRepository;
import com.restapi.rcmn.domain.db.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl {

    private final MoviesRepository moviesRepository;
    private final MoviesMemberRepository moviesMemberRepository;

    private final WebClient webClient;

    public List<Movies> rcmnMovies(long memberId) {
        List<Movies> moviesDataList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");

        String testString = "Avengers: Age of Ultron";
        String sumString = String.format("{\n\t\"movie_name\":\"%s\"\n}",testString);

        List<String> movieList = webClient.post()
                .uri("postrecommendapikey")
                .bodyValue(sumString)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        StringTokenizer st = new StringTokenizer(testString,"@");

        while(st.hasMoreTokens()) {
            String target = st.nextToken();
            movieList.remove(target);
        }

        movieList = movieList.subList(0, 10);

        for (String movieTitle: movieList) {
            Movies movie = moviesRepository.findByMoviesTitle(movieTitle).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 영화이름입니다. : " + movieTitle)
            );

            moviesDataList.add(movie);
        }

        return moviesDataList;
    }

    public void testJpqlTest() {
        moviesMemberRepository.findByMemberId(0L);
    }
}
