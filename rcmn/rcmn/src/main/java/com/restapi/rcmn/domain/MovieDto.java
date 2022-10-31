package com.restapi.rcmn.domain;

import com.restapi.rcmn.common.model.BaseResponseBody;
import com.restapi.rcmn.domain.db.entity.Movies;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class MovieDto {

    @Data
    @Builder
    public static class MovieRcmnRes extends BaseResponseBody {
        private String thumbnail;
        private String fileAddress;
        private String moviesTitle;
        private String overView;

        public static List<MovieRcmnRes> createMovieList(List<Movies> movieList) {
            List<MovieRcmnRes> resList = new ArrayList<>();

            for (Movies movie : movieList) {
                    resList.add(MovieRcmnRes.builder()
                        .moviesTitle(movie.getMoviesTitle())
                        .overView(movie.getOverview())
                        .build()
                );
            }

            return resList;
        }
    }
}
