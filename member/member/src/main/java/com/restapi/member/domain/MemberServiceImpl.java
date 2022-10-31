package com.restapi.member.domain;

import com.restapi.member.common.config.jwtConfig.JwtTokenProvider;
import com.restapi.member.domain.db.entity.Member;
import com.restapi.member.domain.db.repostiory.MemberRepository;
import com.restapi.member.domain.exception.DuplicateAuthIdException;
import com.restapi.member.domain.exception.NotFoundAuthIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Member signUp(Member member){
        if(memberRepository.findByAuthId(member.getAuthId()).isPresent())
            throw new DuplicateAuthIdException(member.getAuthId());

        return memberRepository.save(Member.builder()
                .authId(member.getAuthId())
                .password(passwordEncoder.encode(member.getPassword()))
                .build());
    }

    public MemberDto.LoginRes login(Member member) {
        Member loginMember = memberRepository.findByAuthId(member.getAuthId()).orElseThrow(
                () -> new NotFoundAuthIdException(member.getAuthId())
        );

        String token = jwtTokenProvider.createToken(member.getAuthId());

        if (loginMember.getPassword().equals(passwordEncoder.encode(member.getPassword()))) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        return MemberDto.LoginRes.of(member, token);
    }
}
