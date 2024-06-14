package com.consis.test.edgar.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseApi {

    private Object header;
    private Object content;

    public ResponseApi(Object header, Object content) {
        this.header = header;
        this.content = content;
    }
}
