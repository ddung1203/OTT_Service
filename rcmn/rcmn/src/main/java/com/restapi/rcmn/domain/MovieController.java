package com.restapi.rcmn.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
@RequiredArgsConstructor
public class MovieController {

    @PostMapping(value = "test")
    public String memberTest() {
        return "member test";
    }
}
