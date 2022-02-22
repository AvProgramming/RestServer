package com.example.practica6rest.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ClientDto {

    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer phone_number;
}
