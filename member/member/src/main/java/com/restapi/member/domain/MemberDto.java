package com.restapi.member.domain;

import com.restapi.member.common.model.BaseResponseBody;
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class LoginReq {
        private String authId;
        private String password;

        public Member toEntity() {
            return Member.builder()
                    .authId(authId)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class LoginRes extends BaseResponseBody {
        private String token;

        private String authId;
        public static LoginRes of(Member member, String token) {
            return LoginRes.builder()
                    .token(token)
                    .authId(member.getAuthId())
                    .build();
        }
    }

}
