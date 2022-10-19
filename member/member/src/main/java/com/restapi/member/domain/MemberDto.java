package com.restapi.member.domain;

import com.restapi.member.domain.db.entity.Member;
import lombok.*;


public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class SingUpReq {
        private String authId;
        private String password;

        public Member toEntity() {
            return Member.builder()
                    .authId(authId)
                    .password(getPassword())
                    .build();
        }
    }

}
