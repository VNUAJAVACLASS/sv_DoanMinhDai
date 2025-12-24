package com.vnua.fita.controller;

import com.vnua.fita.dto.request.BookRequest;
import com.vnua.fita.dto.response.ApiResponse;
import com.vnua.fita.entity.Book;
import com.vnua.fita.entity.Category;
import com.vnua.fita.service.BookService;
import com.vnua.fita.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping("/books")
    public ApiResponse<List<Book>> getAllBooks() {
        return ApiResponse.success(bookService.findAll(), "Lấy danh sách sách thành công");
    }

    @GetMapping("/books/{id}")
    public ApiResponse<Book> getBookById(@PathVariable Long id) {
        return ApiResponse.success(bookService.findById(id), "Lấy thông tin sách thành công");
    }


    @PostMapping("/admin/books")
    public ApiResponse<Book> addBook(@Valid @RequestBody BookRequest request) {
        Category category = categoryService.findById(request.getCategoryId());
        
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setStockQuantity(request.getStockQuantity());
        book.setCategory(category);
        
        return ApiResponse.success(bookService.save(book), "Thêm sách mới thành công");
    }

    @PutMapping("/admin/books/{id}")
    public ApiResponse<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Book book = bookService.findById(id);
        Category category = categoryService.findById(request.getCategoryId());

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setStockQuantity(request.getStockQuantity());
        book.setCategory(category);

        return ApiResponse.success(bookService.save(book), "Cập nhật sách thành công");
    }

    @DeleteMapping("/admin/books/{id}")
    public ApiResponse<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ApiResponse.success(null, "Xóa sách thành công");
    }
}