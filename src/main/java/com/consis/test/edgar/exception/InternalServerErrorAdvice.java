package com.consis.test.edgar.exception;

import com.consis.test.edgar.util.MessageUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
public class InternalServerErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(
                MessageUtil.MSG_HTTP_ERROR,
                MessageUtil.MSG_OPERATION_ERROR,
               MessageUtil.MSG_OPERATION_ERROR_DESCRIPTION);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
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
