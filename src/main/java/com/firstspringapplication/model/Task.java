package com.firstspringapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Task extends BaseEntity{
    private Date createDate;
    private String description;
    private Status status;
    @ManyToOne(targetEntity = User.class)
    private User creator;

}
