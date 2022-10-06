package com.example.order.service.app.application.projection.order;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "orders")
public class OrderDataModel {
    @Id
    private String itemId;
    private String name;
    private int nr;
}
