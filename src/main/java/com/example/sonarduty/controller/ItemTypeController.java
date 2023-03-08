package com.example.sonarduty.controller;

import com.example.sonarduty.exception.BadRequestException;
import com.example.sonarduty.request.CreateUserRequest;
import com.example.sonarduty.request.ItemTypeCreateRequest;
import com.example.sonarduty.request.ItemTypeUpdateRequest;
import com.example.sonarduty.request.UpdateUserRequest;
import com.example.sonarduty.response.CreateUserResponse;
import com.example.sonarduty.response.ItemTypeCreateResponse;
import com.example.sonarduty.response.ItemTypeUpdateResponse;
import com.example.sonarduty.response.UpdateUserResponse;
import com.example.sonarduty.service.ItemTypeService;
import com.example.sonarduty.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/item_type")
public class ItemTypeController {

    @Autowired
    private ItemTypeService itemTypeService;

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItemType(@RequestBody ItemTypeCreateRequest request){
        ItemTypeCreateResponse response;
        HttpStatus httpStatus;
        try{
            response = itemTypeService.createItemType(request);
            httpStatus = HttpStatus.OK;
        }
        catch (BadRequestException badRequestException){
            response = ItemTypeCreateResponse.failureResponse(badRequestException.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (Exception exception){
            response = ItemTypeCreateResponse.failureResponse(exception.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItemType(@RequestBody ItemTypeUpdateRequest request){
        ItemTypeUpdateResponse response;
        HttpStatus httpStatus;
        try{
            response = itemTypeService.updateItemType(request);
            httpStatus = HttpStatus.OK;
        }
        catch (BadRequestException badRequestException){
            response = ItemTypeUpdateResponse.failureResponse(badRequestException.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (Exception exception){
            response = ItemTypeUpdateResponse.failureResponse(exception.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItemTypeById(@PathVariable("id") Long id){
        try{
            return new ResponseEntity(itemTypeService.getItemTypeById(id), HttpStatus.OK);
        }
        catch (BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/fetchAll/{field}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTypes(@PathVariable("field") String field){
        try{
            return new ResponseEntity(itemTypeService.getAllTypes(field), HttpStatus.OK);
        }
        catch (BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
