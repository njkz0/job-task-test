package com.firstspringapplication.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @OneToOne( targetEntity = User.class)
    private User user;

    @Column(name = "time")
    private String time;

    @Column(name = "status")
    private Status status;

    public Cart(User user) {
        this.user=user;
        time= new Date().toString();
    }
}