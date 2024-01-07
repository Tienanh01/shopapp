package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "price")
    private Float price;
    @Column(name = "number_of_product")
    private int numberOfProduct;
    @Column(name = "total_money")
    private Float totalMoney;
    @Column(name = "color")
    private String color;
}
