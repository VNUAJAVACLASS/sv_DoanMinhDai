package com.vnua.fita.service;

import com.vnua.fita.dto.request.OrderRequest;
import com.vnua.fita.entity.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(OrderRequest request, String username);
    List<Order> findAll();
    Order findById(Long id);
    Order updateStatus(Long id, String status);
    List<Order> findByUsername(String username);
}