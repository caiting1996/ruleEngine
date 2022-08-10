package com.example.ruleengine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> extends BaseResponse implements Serializable {

    private T data;

    public ResponseModel( ) {
        super();
    }

    public ResponseModel(T data) {
        super();
        this.data = data;
    }

    public ResponseModel(int code, boolean success, String msg) {
        super(code, success, msg);
    }

    public ResponseModel(int code, boolean success, String msg, T data) {
        super(code, success, msg);
        this.data = data;
    }

    @Override
    public String toString( ) {
        return "ResponseModel{" +
                "code =" + getCode() +
                ", success=" + getSuccess() +
                ", msg=" + getMsg() +
                ", data=" + data +
                '}';
    }
}
