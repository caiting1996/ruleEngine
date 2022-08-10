package com.example.ruleengine.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BaseResponse {
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 操作是否成功
     */
    private Boolean success;

    /**
     * 响应消息
     */
    private String msg;


    public BaseResponse( ) {
        this.code = 200;
        this.success = true;
        this.msg = "操作成功";
    }

    public BaseResponse(int code, Boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    @Override
    public String toString( ) {
        return "BaseResponse{" +
                "code=" + code +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
