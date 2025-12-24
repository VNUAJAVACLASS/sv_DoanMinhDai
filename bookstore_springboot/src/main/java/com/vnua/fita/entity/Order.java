package com.vnua.fita.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status; // PENDING, CONFIRMED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderBook> orderItems = new ArrayList<>();

    public void addLineItem(Book book, int quantity) {
        OrderBook item = new OrderBook();
        item.setOrder(this);
        item.setBook(book);
        item.setQuantity(quantity);
        item.setPrice(BigDecimal.valueOf(book.getPrice()));
        this.orderItems.add(item);
    }
}