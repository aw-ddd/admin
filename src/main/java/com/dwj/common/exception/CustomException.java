package com.dwj.common.exception;

import com.dwj.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {
    private int code;

    public CustomException(int code){
        super(ResultCode.getMessageByCode(code));
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
