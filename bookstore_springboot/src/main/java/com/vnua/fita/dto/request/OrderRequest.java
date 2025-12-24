package com.vnua.fita.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    @NotEmpty(message = "Giỏ hàng không được trống")
    private List<OrderItemRequest> items;

    @Data
    public static class OrderItemRequest {
        @NotNull(message = "ID sách không được trống")
        private Long bookId;

        @Min(value = 1, message = "Số lượng mua tối thiểu là 1")
        private Integer quantity;
    }
}