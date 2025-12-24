package com.vnua.fita.controller;

import com.vnua.fita.dto.request.UpdateRoleRequest;
import com.vnua.fita.dto.response.ApiResponse;
import com.vnua.fita.entity.User;
import com.vnua.fita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.success(userService.findAllUsers(), "Lấy danh sách người dùng thành công");
    }

    @PatchMapping("/{id}/roles")
    public ApiResponse<User> updateUserRoles(
            @PathVariable Long id, 
            @RequestBody UpdateRoleRequest request) {
        
        User updatedUser = userService.updateUserRoles(id, request.getRoles());
        return ApiResponse.success(updatedUser, "Cập nhật quyền thành công");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ApiResponse.success(null, "Xóa người dùng thành công");
    }
}