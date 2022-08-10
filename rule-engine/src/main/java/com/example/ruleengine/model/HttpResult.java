package com.example.ruleengine.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class HttpResult {

    // 响应码
    private Integer code;

    // 响应体
    private String body;


    public HttpResult( ) {
        super();
    }

    public HttpResult(Integer code, String body) {
        super();
        this.code = code;
        this.body = body;
    }

    @Override
    public String toString( ) {
        return "HttpResult{" +
                "code=" + code +
                ", body='" + body + '\'' +
                '}';
    }
}
