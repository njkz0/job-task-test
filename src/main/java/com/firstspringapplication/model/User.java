package com.firstspringapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table (name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class User extends  BaseEntity {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Profile profile;

}
