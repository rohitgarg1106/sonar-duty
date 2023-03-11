package com.example.sonarduty.controller;

import com.example.sonarduty.exception.BadRequestException;
import com.example.sonarduty.request.ItemCreateRequest;
import com.example.sonarduty.request.ItemTypeCreateRequest;
import com.example.sonarduty.request.ItemTypeUpdateRequest;
import com.example.sonarduty.request.ItemUpdateRequest;
import com.example.sonarduty.response.*;
import com.example.sonarduty.service.ItemService;
import com.example.sonarduty.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody ItemCreateRequest request){
        ItemCreateResponse response;
        HttpStatus httpStatus;
        try{
            response = itemService.createItem(request);
            httpStatus = HttpStatus.OK;
        }
        catch (BadRequestException badRequestException){
            response = ItemCreateResponse.failureResponse(badRequestException.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (Exception exception){
            response = ItemCreateResponse.failureResponse(exception.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }
//
    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@RequestBody ItemUpdateRequest request){
        ItemUpdateResponse response;
        HttpStatus httpStatus;
        try{
            response = itemService.updateItem(request);
            httpStatus = HttpStatus.OK;
        }
        catch (BadRequestException badRequestException){
            response = ItemUpdateResponse.failureResponse(badRequestException.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (Exception exception){
            response = ItemUpdateResponse.failureResponse(exception.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }
//
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItem(@PathVariable("id") Long id){
        try{
            return new ResponseEntity(itemService.getItem(id), HttpStatus.OK);
        }
        catch (BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/fetchAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllItems(){
        try{
//            @PathVariable("field") String field
            return new ResponseEntity(itemService.getAllItems(), HttpStatus.OK);
        }
        catch (BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/configs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConfigs(){
        ItemConfigsReponse itemConfigsReponse;
        HttpStatus httpStatus;
        try {
            itemConfigsReponse = itemService.getConfigs();
            httpStatus = HttpStatus.OK;
        }
        catch (Exception exception){
            itemConfigsReponse = ItemConfigsReponse.failureResponse(exception.getStackTrace().toString());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(itemConfigsReponse, httpStatus);
    }

}
