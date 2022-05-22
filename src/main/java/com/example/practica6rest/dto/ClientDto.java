package com.example.practica6rest.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer phone_number;

    private String address;
}
