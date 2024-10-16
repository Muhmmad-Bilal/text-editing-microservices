package com.user.authentication.user_service.controller;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.BaseResponse;
import com.user.authentication.user_service.dto.response.BaseResponseDTO;
import com.user.authentication.user_service.exception.ESException;
import com.user.authentication.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController extends BaseResponse {
 private  final UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponseDTO> addUser(@RequestBody UserRequestDTO model) throws ESException {
        return ResponseEntity.ok(response(HttpStatus.CREATED,HttpStatus.CREATED.getReasonPhrase(),userService.saveUser(model)));
    }

}
