package com.anton.eshop.service;

import com.anton.eshop.data.Role;
import com.anton.eshop.data.User;
import com.anton.eshop.dto.UserDTO;
import com.anton.eshop.dto.mapDTO.UserMapper;
import com.anton.eshop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean save(UserDTO userDTO) {
        if (Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .email(userDTO.getEmail())
                    .role(Role.CLIENT)
                    .build();

            userRepository.save(user);
            return true;
        } else {
            throw new RuntimeException("Password is not equals.");
        }
    }

    @Override
    public List<UserDTO> fetchAll() {
        return ((List<User>) userRepository.findAll())
                .stream()
                .map(it -> UserMapper.MAPPER.userMapUserDTO(it))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
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



    private User getUserByUsername(String username) {
        List<User> users = (List<User>) userRepository.findAll();
        User user = null;

        for (User usr : users) {
            if (Objects.equals(usr.getUsername(), username))
                user = usr;
        }

        return Objects.nonNull(user) ? user : null;
    }

}
