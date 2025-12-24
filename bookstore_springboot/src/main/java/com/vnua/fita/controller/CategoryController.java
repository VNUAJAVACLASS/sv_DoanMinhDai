package com.vnua.fita.controller;

import com.vnua.fita.dto.request.CategoryRequest;
import com.vnua.fita.dto.response.ApiResponse;
import com.vnua.fita.entity.Category;
import com.vnua.fita.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ApiResponse<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ApiResponse.success(categories, "Lấy danh sách danh mục thành công");
    }

    @GetMapping("/categories/{id}")
    public ApiResponse<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ApiResponse.success(category, "Lấy thông tin danh mục thành công");
    }


    @PostMapping("/admin/categories")
    public ApiResponse<Category> addCategory(@Valid @RequestBody CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        Category savedCategory = categoryService.save(category);
        return ApiResponse.success(savedCategory, "Thêm danh mục mới thành công");
    }

    @PutMapping("/admin/categories/{id}")
    public ApiResponse<Category> updateCategory(
            @PathVariable Long id, 
            @Valid @RequestBody CategoryRequest request) {
        
        Category category = categoryService.findById(id);
        category.setName(request.getName());
        Category updatedCategory = categoryService.save(category);
        
        return ApiResponse.success(updatedCategory, "Cập nhật danh mục thành công");
    }

    @DeleteMapping("/admin/categories/{id}")
    public ApiResponse<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ApiResponse.success(null, "Xóa danh mục thành công");
    }
}