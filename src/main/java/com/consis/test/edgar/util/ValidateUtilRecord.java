package com.consis.test.edgar.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateUtilRecord {

    private boolean value;
    private String reason;

    public ValidateUtilRecord(boolean value, String reason) {
        this.value = value;
        this.reason = reason;
    }
}
