package com.consis.test.edgar.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountRecordReportRequest {

    private Date init;
    private Date end;
}
