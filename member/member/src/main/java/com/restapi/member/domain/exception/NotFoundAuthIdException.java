package com.restapi.member.domain.exception;

public class NotFoundAuthIdException extends RuntimeException {
    
    NotFoundAuthIdException() {}
    
    public NotFoundAuthIdException(String authId) {
        super("존재하지 않은 아이디입니다. :" + authId);
    }
}
