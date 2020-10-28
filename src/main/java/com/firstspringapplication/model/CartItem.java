package com.firstspringapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity{

    @ManyToOne(targetEntity = Item.class)
    private Item item;

    @ManyToOne (targetEntity = Cart.class)
    private Cart cart;

    @Column(name = "amount")
    private Integer amount;




}