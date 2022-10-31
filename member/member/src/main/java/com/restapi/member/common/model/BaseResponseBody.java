package com.restapi.member.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BaseResponseEntity")
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseBody {
    @ApiModelProperty(value = "응답 메시지", example = "성공")
    private String message;

    @ApiModelProperty(value = "응답 코드", example = "200")
    private int statusCode;

    public static BaseResponseBody of(int statusCode, String message) {
        BaseResponseBody body = new BaseResponseBody();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
}
