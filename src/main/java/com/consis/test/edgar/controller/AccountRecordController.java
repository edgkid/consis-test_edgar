package com.consis.test.edgar.controller;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.AccountRecord;
import com.consis.test.edgar.domain.dto.AccountDto;
import com.consis.test.edgar.domain.dto.AccountRecordDto;
import com.consis.test.edgar.exception.InternalServerErrorAdvice;
import com.consis.test.edgar.repository.AccountRecordRepository;
import com.consis.test.edgar.request.AccountRecordReportRequest;
import com.consis.test.edgar.request.AccountRecordRequest;
import com.consis.test.edgar.response.ResponseApi;
import com.consis.test.edgar.service.AccountRecordService;
import com.consis.test.edgar.util.MessageUtil;
import com.consis.test.edgar.util.ValidateUtilRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movements")
public class AccountRecordController {

    public static record ServerMessageOk(String code, String message, String description){}
    @Autowired
    private AccountRecordService service;

    @GetMapping("/")
    public ResponseApi getAllAccountRecords(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{
            response.setContent(service.getAllAccountRecords(pageNumber, pageSize));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseApi(
                    new InternalServerErrorAdvice.ErrorResponse(
                            MessageUtil.MSG_HTTP_ERROR,
                            MessageUtil.MSG_OPERATION_ERROR_DESCRIPTION,
                            ""
                    ),
                    new Object()
            );
        }

        return response;
    }

    @GetMapping("/{number}")
    public ResponseApi getAccountRecordByAccountNumber(@PathVariable String number) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{
            response.setContent(service.getAllByAccountNumber(number));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseApi(
                    new InternalServerErrorAdvice.ErrorResponse(
                            MessageUtil.MSG_HTTP_ERROR,
                            MessageUtil.MSG_OPERATION_ERROR_DESCRIPTION,
                            ""
                    ),
                    new Object()
            );
        }

        return response;
    }

    @PostMapping("/")
    public ResponseApi createAccountRecord(@RequestBody AccountRecordRequest request) {

        ValidateUtilRecord validateUtilRecord = null;
        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{

            validateUtilRecord = service.createAccountRecord(request);
            if(validateUtilRecord.isValue()){
                response.setContent(validateUtilRecord.getReason());
            }else{
                response.setContent(validateUtilRecord.getReason());
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseApi(
                    new InternalServerErrorAdvice.ErrorResponse(
                            MessageUtil.MSG_HTTP_ERROR,
                            MessageUtil.MSG_OPERATION_ERROR_DESCRIPTION,
                            ""
                    ),
                    new Object()
            );
        }

        System.out.println(validateUtilRecord.getReason());
        return response;
    }

    @PostMapping("/report")
    public ResponseApi getAccountsRecordByDate(@RequestBody AccountRecordReportRequest request) {

        ResponseApi response = new ResponseApi( this.getStatusOK(), null);
        List<AccountRecord> listResult = new ArrayList<>();

        try {
            response.setContent(service.getReportByDate(request));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApi(
                    new InternalServerErrorAdvice.ErrorResponse(
                            MessageUtil.MSG_HTTP_ERROR,
                            MessageUtil.MSG_OPERATION_ERROR_DESCRIPTION,
                            ""
                    ),
                    new Object()
            );
        }
        return response;
    }


    private PersonController.ServerMessageOk getStatusOK(){
        return new PersonController.ServerMessageOk(MessageUtil.MSG_HTTP_SUCCESS,
                MessageUtil.MSG_OPERATION_SUCCESS,
                MessageUtil.MSG_OPERATION_SUCCESS_DESCRIPTON
        );
    }

}
