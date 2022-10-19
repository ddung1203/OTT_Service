package com.restapi.member.domain.db.repostiory;

import com.restapi.member.domain.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAuthId(String authId);
}
