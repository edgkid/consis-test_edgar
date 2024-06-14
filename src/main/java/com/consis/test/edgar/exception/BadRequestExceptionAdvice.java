package com.consis.test.edgar.exception;

import com.consis.test.edgar.util.MessageUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
public class BadRequestExceptionAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                MessageUtil.MSG_HTTP_BADREQUEST,
                MessageUtil.MSG_OPERATION_BADREQUEST_DESCRIPTON,
                MessageUtil.MSG_OPERATION_BADREQUEST_DESCRIPTON);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    public static class ErrorResponse {
        private final String code;
        private final String message;
        private final String detail;

        public ErrorResponse(String code, String message, String detail) {
            this.code = code;
            this.message = message;
            this.detail = detail;
        }

    }

}
