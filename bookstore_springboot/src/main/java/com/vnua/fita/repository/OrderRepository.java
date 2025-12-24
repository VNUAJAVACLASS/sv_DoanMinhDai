package com.vnua.fita.repository;

import com.vnua.fita.entity.Order;
import com.vnua.fita.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUser_Username(String username);
}