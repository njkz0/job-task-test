package com.firstspringapplication.controller.request;

import lombok.Data;

import java.util.Date;

@Data
public class ListCartRequestDTO {
    Integer id;
    Date dateFrom;
    Date dateTo;
}
