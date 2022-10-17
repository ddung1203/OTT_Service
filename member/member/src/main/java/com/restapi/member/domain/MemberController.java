package com.restapi.member.domain;

import com.restapi.member.common.model.BaseResponseBody;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
