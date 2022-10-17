package com.restapi.member.domain;

import com.restapi.member.domain.db.entity.Member;
import com.restapi.member.domain.db.repostiory.MemberRepository;
import com.restapi.member.domain.exception.DuplicateAuthIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    public Member signUp(Member member){

        if(memberRepository.findByAuthId(member.getAuthId()).isPresent())
            throw new DuplicateAuthIdException(member.getAuthId());

        Member newMember = memberRepository.save(member);

        return newMember;
    }
}
