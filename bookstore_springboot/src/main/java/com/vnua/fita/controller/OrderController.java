package com.vnua.fita.controller;

import com.vnua.fita.dto.request.OrderRequest;
import com.vnua.fita.dto.response.ApiResponse;
import com.vnua.fita.entity.Order;
import com.vnua.fita.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public ApiResponse<Order> placeOrder(@Valid @RequestBody OrderRequest request, Authentication authentication) {
        String username = authentication.getName();
        
        Order order = orderService.placeOrder(request, username);
        return ApiResponse.success(order, "Đặt hàng thành công!");
    }

    @GetMapping
    public ApiResponse<List<Order>> getAllOrders(Authentication authentication) {
        String username = authentication.getName();
        
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<Order> orders;
        if (isAdmin) {
            orders = orderService.findAll(); 
        } else {
            orders = orderService.findByUsername(username); 
        }
        
        return ApiResponse.success(orders, "Lấy danh sách đơn hàng thành công");
    }
    
    @GetMapping("/{id}")
    @PostAuthorize("hasRole('ADMIN') or returnObject.data.user.username == authentication.name")
    public ApiResponse<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ApiResponse.success(order, "Lấy thông tin đơn hàng thành công");
    }

    @PatchMapping("/admin/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Order> updateStatus(@PathVariable Long id, @RequestParam String status) {
        Order updatedOrder = orderService.updateStatus(id, status);
        return ApiResponse.success(updatedOrder, "Cập nhật trạng thái đơn hàng thành công");
    }
}