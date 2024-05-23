package com.assigment.miniproject.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
}
