package com.consis.test.edgar.controller;

import com.consis.test.edgar.domain.dto.AccountDto;
import com.consis.test.edgar.exception.InternalServerErrorAdvice;
import com.consis.test.edgar.request.AccountRequest;
import com.consis.test.edgar.response.ResponseApi;
import com.consis.test.edgar.service.AccountService;
import com.consis.test.edgar.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    public static record ServerMessageOk(String code, String message, String description){}

    @Autowired
    private AccountService service;

    @GetMapping("/")
    public ResponseApi getAllAccounts(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{
            response.setContent(service.findAll(pageNumber, pageSize));
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
    public ResponseApi getAccountById(@PathVariable String number) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);

        try{
            response.setContent(service.findByNumber(number));
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
    public ResponseApi createAccount(@RequestBody AccountRequest request) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);

        try{
            if(service.saveAccount(request)){
                response.setContent(MessageUtil.MSG_OPERATION_SUCCESS_DESCRIPTON);
            }else{
                response.setContent(MessageUtil.MSG_OPERATION_FAILED_DESCRIPTON);
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

        return response;
    }

    private PersonController.ServerMessageOk getStatusOK(){
        return new PersonController.ServerMessageOk(MessageUtil.MSG_HTTP_SUCCESS,
                MessageUtil.MSG_OPERATION_SUCCESS,
                ""
        );
    }

}
