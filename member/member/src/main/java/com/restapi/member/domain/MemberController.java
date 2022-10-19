package com.restapi.member.domain;

import com.restapi.member.common.model.BaseResponseBody;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping(value = "insert_member")
    public ResponseEntity<? extends BaseResponseBody> insertCompany(
            @RequestBody MemberDto.SingUpReq dto
    ) {
        memberService.signUp(dto.toEntity());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
    }

    @GetMapping(value = "test")
    public String memberTest() {
        return "member test";
    }
}
