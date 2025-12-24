package com.vnua.fita.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "order_book")
@Data
public class OrderBook {
    @EmbeddedId
    private OrderBookId id = new OrderBookId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Book book;

    private Integer quantity;
    private BigDecimal price;
}