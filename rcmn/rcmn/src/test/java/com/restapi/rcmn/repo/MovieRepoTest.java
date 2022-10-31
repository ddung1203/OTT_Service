package com.restapi.rcmn.repo;

import com.restapi.rcmn.domain.db.entity.Movies;
import com.restapi.rcmn.domain.db.entity.MoviesMember;
import com.restapi.rcmn.domain.db.repository.MoviesMemberRepository;
import com.restapi.rcmn.domain.db.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
public class MovieRepoTest {

    private final MoviesMemberRepository moviesMemberRepository;

    @Test
    public void 영화_레포지토리_findByMovieName_테스트() {
    }

}
