package com.anton.eshop.service;

import com.anton.eshop.dao.UserRepository;
import com.anton.eshop.data.Role;
import com.anton.eshop.data.User;
import com.anton.eshop.dto.UserDTO;
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
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(this::UserMapUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById()
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Unknown email: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }

    private UserDTO UserMapUserDTO(User user) {
        if (Objects.nonNull(user))
            return UserDTO.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .number(user.getNumber())
                    .build();
        else
            return null;
    }

}
