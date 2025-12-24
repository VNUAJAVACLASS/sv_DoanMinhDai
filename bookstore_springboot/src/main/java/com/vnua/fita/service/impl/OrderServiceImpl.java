package com.vnua.fita.service.impl;

import com.vnua.fita.dto.request.OrderRequest;
import com.vnua.fita.entity.Book;
import com.vnua.fita.entity.Order;
import com.vnua.fita.entity.User;
import com.vnua.fita.repository.BookRepository;
import com.vnua.fita.repository.OrderRepository;
import com.vnua.fita.repository.UserRepository;
import com.vnua.fita.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Order placeOrder(OrderRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng: " + username));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderRequest.OrderItemRequest item : request.getItems()) {
            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new RuntimeException("Sách không tồn tại ID: " + item.getBookId()));

            if (book.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Sách '" + book.getTitle() + "' không đủ số lượng trong kho!");
            }

            book.setStockQuantity(book.getStockQuantity() - item.getQuantity());
            bookRepository.save(book);

            order.addLineItem(book, item.getQuantity());

            BigDecimal itemTotal = BigDecimal.valueOf(book.getPrice()).multiply(new BigDecimal(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }

        order.setTotalAmount(totalAmount);
        
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Đơn hàng không tồn tại ID: " + id));
    }

    @Override
    @Transactional
    public Order updateStatus(Long id, String status) {
        Order order = findById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }
    
    @Override
    public List<Order> findByUsername(String username) {
        return orderRepository.findByUser_Username(username);
    }
}