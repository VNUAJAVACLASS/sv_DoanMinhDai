package com.vnua.fita.repository;

import com.vnua.fita.entity.OrderBook;
import com.vnua.fita.entity.OrderBookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookRepository extends JpaRepository<OrderBook, OrderBookId> {
}