package com.user.authentication.user_service.service;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.UserResponseDTO;
import com.user.authentication.user_service.exception.ESException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {


    UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws ESException;
    void delete(long id) throws ESException;

    List<UserResponseDTO> findAllUsers() throws ESException;





}
