package com.anton.eshop.service;

import com.anton.eshop.data.UserEntity;
import com.anton.eshop.dto.UserDTO;
import com.anton.eshop.dto.mapDTO.UserMapper;
import com.anton.eshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper = new UserMapper();


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUserById(Long user_id) {
        userRepository.deleteById(user_id);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .number(userDTO.getNumber())
                .role(userDTO.getRole())
                .build();

        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        UserEntity updateUser = fetchUserByUsername(userDTO.getUsername());
        boolean isCheck = false;

        if (Objects.isNull(updateUser)) {
            throw new RuntimeException("User not found: " + userDTO.getUsername());
        }

        if (!Objects.equals(updateUser.getEmail(), userDTO.getEmail())) {
            updateUser.setEmail(userDTO.getEmail());
            isCheck = true;
        }

        if (Objects.nonNull(userDTO.getPassword()) && !userDTO.getPassword().isEmpty()) {
            updateUser.setPassword(userDTO.getPassword());
            isCheck = true;
        }

        if (isCheck) userRepository.save(updateUser);
    }

    private UserEntity fetchUserByUsername(String username) {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        UserEntity user = null;

        for (UserEntity usr : users) {
            if (Objects.equals(usr.getUsername(), username)) {
                user = usr;
                break;
            }
        }

        return Objects.nonNull(user) ? user : null;
    }

    @Override
    public UserDTO fetchById(Long user_id) {
        return mapper.userMapUserDTO(userRepository.findById(user_id).orElse(null));
    }

    @Override
    public UserEntity fetchByUsername(String username) {
        UserEntity userEntity = null;

        for (UserEntity user : userRepository.findAll()) {
            if (user.getUsername().equals(username))
                userEntity = user;
        }

        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = fetchUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Unknown username: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }
}
