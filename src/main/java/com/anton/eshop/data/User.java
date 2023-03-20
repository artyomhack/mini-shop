package com.anton.eshop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    private static final String SEQ_NAME = "user_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SEQ_NAME)
    @SequenceGenerator(name=SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String number;

    private boolean archive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Cart cart;
}
