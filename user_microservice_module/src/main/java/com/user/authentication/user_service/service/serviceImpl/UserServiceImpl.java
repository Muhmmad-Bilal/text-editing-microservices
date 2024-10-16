package com.user.authentication.user_service.service.serviceImpl;

import com.user.authentication.user_service.dto.request.UserRequestDTO;
import com.user.authentication.user_service.dto.response.UserResponseDTO;
import com.user.authentication.user_service.entity.UserEntity;
import com.user.authentication.user_service.exception.ESException;
import com.user.authentication.user_service.mapper.UserMapper;
import com.user.authentication.user_service.repository.UserRepository;
import com.user.authentication.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private   UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserMapper userMapper;
//    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    this.userMapper = userMapper;
//}

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws ESException {

    userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

       UserEntity userEntity= userRepository.save(userMapper.addUserMapper(userRequestDTO));
        return userMapper.find(userEntity);
    }

    @Override
    public void delete(long id) throws ESException {

    }

    @Override
    public List<UserResponseDTO> findAllUsers() throws ESException {
        return null;
    }
}
