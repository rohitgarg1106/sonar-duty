package com.example.sonarduty.service;

import com.example.sonarduty.exception.BadRequestException;
import com.example.sonarduty.model.User;
import com.example.sonarduty.repository.UserRepository;
import com.example.sonarduty.request.CreateUserRequest;
import com.example.sonarduty.request.UpdateUserRequest;
import com.example.sonarduty.response.CreateUserResponse;
import com.example.sonarduty.response.UpdateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.sonarduty.utils.CommonUtils.isStringEmpty;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public CreateUserResponse createUser(CreateUserRequest request) {
        validateCreateUserRequestParams(request);
        User user = getUserEntity(request);
        User createdUser = userRepository.saveAndFlush(user);
        return getCreateResponseFromEntity(createdUser);
    }

    public CreateUserResponse getCreateResponseFromEntity(User createdUser) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(createdUser.getId());
        response.setEmail(createdUser.getEmail());
        response.setName(createdUser.getName());
        response.setPhoneNumber(createdUser.getPhoneNumber());
        return response;
    }

    public void validateCreateUserRequestParams(CreateUserRequest request) {
//        request
        if(isStringEmpty(request.getName())){
            throw new BadRequestException("User name can not be empty");
        }

        if(request.getPhoneNumber() == null || request.getPhoneNumber().toString().length() != 10){
            throw new BadRequestException("User phone number is invalid");
        }

        if(isStringEmpty(request.getEmail())){
            throw new BadRequestException("User email id can not be empty");
        }

        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if(user.isPresent() && user.get().getEmail().equalsIgnoreCase(request.getEmail())){
            throw new BadRequestException("User with same email already exists");
        }


    }

    public User getUserEntity(CreateUserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        return user;
    }

    public UpdateUserResponse updateUser(UpdateUserRequest request) {

        User user = getUserById(request.getId());

        if(!isStringEmpty(request.getEmail())){
            user.setEmail(request.getEmail());
        }

        if(!isStringEmpty(request.getName())){
            user.setName(request.getName());
        }

        if(request.getPhoneNumber() != null){
            user.setPhoneNumber(request.getPhoneNumber());
        }

        return new UpdateUserResponse(userRepository.save(user));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException("user id is invalid"));
    }

    public List<User> getAllUsers(String field){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }
}

