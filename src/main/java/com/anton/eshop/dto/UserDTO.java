package com.anton.eshop.dto;

import com.anton.eshop.data.Role;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
    private String matchingPassword;
    private String email;
    private String number;
    private Role role;

}
