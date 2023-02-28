package com.teamway.utils;

public class ResponseSerializer {
    private Boolean success;
    private String code;
    private String message;
    private Object data;

    public ResponseSerializer(Boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
