package com.theralink.common.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponse implements Serializable {

    private int code;
    private String message;

    public ErrorResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
