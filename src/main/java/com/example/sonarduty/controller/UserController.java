package com.example.sonarduty.controller;

import com.example.sonarduty.exception.BadRequestException;
import com.example.sonarduty.request.CreateUserRequest;
import com.example.sonarduty.request.UpdateUserRequest;
import com.example.sonarduty.response.CreateUserResponse;
import com.example.sonarduty.response.UpdateUserResponse;
import com.example.sonarduty.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){
        CreateUserResponse response;
        HttpStatus httpStatus;
        try{
            response = userService.createUser(request);
            httpStatus = HttpStatus.OK;
        }
        catch (BadRequestException badRequestException){
            response = CreateUserResponse.failureResponse(badRequestException.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (Exception exception){
            response = CreateUserResponse.failureResponse(exception.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request){
        UpdateUserResponse response;
        HttpStatus httpStatus;
        try{
            response = userService.updateUser(request);
            httpStatus = HttpStatus.OK;
        }
        catch (BadRequestException badRequestException){
            response = UpdateUserResponse.failureResponse(badRequestException.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        catch (Exception exception){
            response = UpdateUserResponse.failureResponse(exception.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        try{
            return new ResponseEntity(userService.getUserById(id), HttpStatus.OK);
        }
        catch (BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/fetchAll/{field}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers(@PathVariable("field") String field){
        try{
            return new ResponseEntity(userService.getAllUsers(field), HttpStatus.OK);
        }
        catch (BadRequestException badRequestException){
            return new ResponseEntity(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
