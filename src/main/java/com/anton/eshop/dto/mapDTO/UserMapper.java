package com.anton.eshop.dto.mapDTO;

import com.anton.eshop.data.UserEntity;
import com.anton.eshop.dto.UserDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserMapper MAPPER = new UserMapper();

    public UserDTO userMapUserDTO(UserEntity userEntity) {
        if (Objects.nonNull(userEntity))
            return UserDTO.builder()
                    .username(userEntity.getUsername())
                    .email(userEntity.getEmail())
                    .number(userEntity.getNumber())
                    .build();
        else
            return null;
    }

    public UserEntity userDTOmapToUser(UserDTO userDTO) {
        if (Objects.nonNull(userDTO)) {
            return UserEntity.builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .email(userDTO.getEmail())
                    .number(userDTO.getNumber())
                    .build();

        } else
            return null;
    }

    public List<UserDTO> usersToUsersDTO(List<UserEntity> userEntities) {
        return userEntities.stream()
                .filter(Objects::nonNull)
                .map(this::userMapUserDTO)
                .collect(Collectors.toList());
    }

    public List<UserEntity> productsDTOtoProducts(List<UserDTO> usersDTO) {
        return usersDTO.stream()
                .filter(Objects::nonNull)
                .map(this::userDTOmapToUser)
                .collect(Collectors.toList());
    }
}
