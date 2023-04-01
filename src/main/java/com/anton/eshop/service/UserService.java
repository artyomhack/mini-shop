package com.anton.eshop.service;

import com.anton.eshop.data.UserEntity;
import com.anton.eshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface UserService extends UserDetailsService{
    void deleteUserById(Long user_id);

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    UserDTO fetchById(Long user_id);

    UserEntity fetchByUsername(String username);
}
