package com.consis.test.edgar.controller;

import com.consis.test.edgar.domain.dto.PersonDto;
import com.consis.test.edgar.exception.InternalServerErrorAdvice;
import com.consis.test.edgar.request.PersonRequest;
import com.consis.test.edgar.response.ResponseApi;
import com.consis.test.edgar.service.PersonService;
import com.consis.test.edgar.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@ResponseBody
public class PersonController {

    public static record ServerMessageOk(String code, String message, String description){}
    @Autowired
    private PersonService service;

    @GetMapping("/")
    public ResponseApi getAllPeople(
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

        return  response;
    }

    @GetMapping("/{id}")
    public ResponseApi getPersonById(@PathVariable Long id) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{
            response.setContent(service.findById(id));
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

    @PostMapping(value = "/")
    public ResponseApi createPerson(@RequestBody PersonRequest request) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{

            if(service.savePerson(request)){
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

    @PutMapping("/{id}")
    public ResponseApi updatePerson(@PathVariable Long id, @RequestBody PersonRequest request) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{

            if(service.updatePerson(request,id)){
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

    @DeleteMapping("/{id}")
    public ResponseApi deletePerson(@PathVariable Long id) {

        ResponseApi response = new ResponseApi(this.getStatusOK(), null);
        try{

            if(service.deletePerson(id)){
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

    private ServerMessageOk getStatusOK(){
        return new ServerMessageOk(MessageUtil.MSG_HTTP_SUCCESS,
                MessageUtil.MSG_OPERATION_SUCCESS,
                MessageUtil.MSG_OPERATION_SUCCESS_DESCRIPTON
        );
    }

}
