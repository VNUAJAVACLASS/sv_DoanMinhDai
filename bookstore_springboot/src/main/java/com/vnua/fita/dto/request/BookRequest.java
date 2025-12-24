package com.vnua.fita.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;
    
    private String author;
    
    @Min(value = 0, message = "Giá không được nhỏ hơn 0")
    private Double price;
    
    @Min(value = 0, message = "Số lượng kho không được âm")
    private Integer stockQuantity;
    
    @NotNull(message = "ID danh mục không được để trống")
    private Long categoryId;
}