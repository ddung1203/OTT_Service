package com.restapi.member.domain;

import com.restapi.member.common.model.BaseResponseBody;
import com.restapi.member.domain.db.entity.Member;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping(value = "insert_member")
    public ResponseEntity<? extends BaseResponseBody> insert(
            @RequestBody MemberDto.SingUpReq req
    ) {
        memberService.signUp(req.toEntity());
        return ResponseEntity.ok().body(BaseResponseBody.of(200, "성공"));
    }

    @PostMapping(value = "login")
    public ResponseEntity<? extends BaseResponseBody> login(
            @RequestBody MemberDto.LoginReq req
    ) {
        MemberDto.LoginRes resBody = memberService.login(req.toEntity());

        return ResponseEntity.ok().body(resBody);
    }
    @GetMapping(value = "login_test")
    public String memberTest(
            @AuthenticationPrincipal @ApiIgnore Member member
    ) {
        System.out.println(member.getMemberIdx());
        return "member test";
    }

}
