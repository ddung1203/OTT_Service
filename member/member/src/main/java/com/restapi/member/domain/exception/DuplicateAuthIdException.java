package com.restapi.member.domain.exception;

public class DuplicateAuthIdException extends RuntimeException{
    public DuplicateAuthIdException() {
    }

    public DuplicateAuthIdException(String authId) {
        super("이미 존재하는 아이디입니다. :" + authId);
    }
}
