package com.corporate.learning.userservice.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;



@Table("users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private LocalDate creationDate;

}


