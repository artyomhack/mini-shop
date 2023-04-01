package com.anton.eshop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private String number;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;

    @OneToOne(fetch = FetchType.LAZY    )
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    public UserEntity() {}
}
