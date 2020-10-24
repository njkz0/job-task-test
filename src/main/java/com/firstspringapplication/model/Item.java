package com.firstspringapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    @Column
    private String name;
    @Column
    private Integer price;
}
