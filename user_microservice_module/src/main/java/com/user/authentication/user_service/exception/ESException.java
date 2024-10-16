package com.user.authentication.user_service.exception;

import com.user.authentication.user_service.entity.BaseEntity;

public class ESException extends BaseException {
    public ESException(String message) {
        super(message);
    }

    public ESException(int code, String message) {
        super( message,code);
    }

    public ESException(int code, String message, Throwable cause) {
        super( code,message, cause);
    }
}
