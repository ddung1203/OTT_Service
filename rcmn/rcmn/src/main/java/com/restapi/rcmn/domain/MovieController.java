package com.restapi.rcmn.domain;

import com.restapi.rcmn.common.model.BaseResponseBody;
import com.restapi.rcmn.domain.db.entity.Movies;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;

    @PostMapping(value = "rcmn")
    public ResponseEntity<?> rcmnController(
            HttpServletRequest request
    ) {
        long memberId = Long.parseLong(request.getHeader("memberId"));
        List<Movies> movieList = movieService.rcmnMovies(memberId);

        return ResponseEntity.ok().body(MovieDto.MovieRcmnRes.createMovieList(movieList));
    }

    @GetMapping(value = "test")
    public ResponseEntity<?> test() {
        movieService.testJpqlTest();
        return ResponseEntity.ok().body(BaseResponseBody.of(200, "ok"));
    }
}
