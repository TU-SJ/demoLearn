package com.cebbank.airm.tech.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("统一响应实体类")
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1763079252395550862L;
    @ApiModelProperty(value = "状态码")
    private State stateCode;

    @ApiModelProperty(value = "数据")
    private T data;

    public ResponseData(String code, String standardMessage, T data) {
        this.setStateCode(new State(code,standardMessage));
        this.data = data;
    }

    public static ResponseData ok() {
        return ok("sucess");
    }

    public static <T> ResponseData<T> ok(T data) {
        return new ResponseData<T>(StateCode.SUCCESS.getCode(), StateCode.SUCCESS.getDesc(), data);
    }
    public static ResponseData ok(String message) {
        return new ResponseData(StateCode.SUCCESS.getCode(),message,"sucess");
    }
    public static ResponseData fail(StateCode status) {
        return new ResponseData<>(status.getCode(), status.getDesc(), null);
    }

    public static ResponseData fail(String code , String msg){
        return  new ResponseData<>(code, msg, null);
    }

    public static ResponseData fail(String code, String desc, String data) {
        return new ResponseData<>(code,desc,data);
    }
    public static <T> ResponseData fail(String code, String desc, T data) {
        return new ResponseData<>(code,desc,data);
    }

    public ResponseData byEnum(StateCode statusCode, T data) {
        this.setStateCode(new State(statusCode.getCode(),statusCode.getDesc()));
        this.setData(data);
        return this;
    }

    public static void main(String[] args) {
        String s = new String("asd");
        String b = "asd";
        String a = "asd";
        System.out.println(s == a);
        System.out.println(b == a);
        System.out.println(StateCode.ER_LOG_EXCEPTION.getCode());
    }
}
