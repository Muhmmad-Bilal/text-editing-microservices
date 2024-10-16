package com.user.authentication.user_service.service;

import com.user.authentication.user_service.dto.request.AuthenticationRequestDTO;
import com.user.authentication.user_service.dto.response.AuthenticationResponseDTO;
import com.user.authentication.user_service.exception.ESException;

public interface AuthenticationService {
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO model) throws ESException;
    void logout() throws ESException;
   void validateToken(String token);
}
