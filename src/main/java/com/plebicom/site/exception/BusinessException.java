package com.plebicom.site.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
        if(log.isDebugEnabled()){
            log.debug(message);
        }
    }
}
