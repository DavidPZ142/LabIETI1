package com.edu.eci.ieti.laboratorio.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String email;
    private String lastname;
    private String createdAt;

    public User(){
        this.createdAt = LocalDate.now().toString();
    }
}
