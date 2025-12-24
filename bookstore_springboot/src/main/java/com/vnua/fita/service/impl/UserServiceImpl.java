package com.vnua.fita.service.impl;

import com.vnua.fita.entity.Role;
import com.vnua.fita.entity.User;
import com.vnua.fita.repository.RoleRepository;
import com.vnua.fita.repository.UserRepository;
import com.vnua.fita.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập '" + user.getUsername() + "' đã tồn tại!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(Role.RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Lỗi hệ thống: Quyền ROLE_USER chưa được khởi tạo!"));
        
        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng: " + username));
    }

    @Override
    @Transactional
    public User updateUserRoles(Long id, Set<String> roleNames) {
        // Tận dụng findById để check tồn tại
        User user = findById(id);

        Set<Role> newRoles = new HashSet<>();
        for (String roleName : roleNames) {
            try {
                // Chuyển chuỗi String sang Enum
                Role.RoleType roleType = Role.RoleType.valueOf(roleName);
                Role role = roleRepository.findByName(roleType)
                        .orElseThrow(() -> new RuntimeException("Role không tồn tại trong hệ thống: " + roleName));
                newRoles.add(role);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Định dạng tên Role không hợp lệ (Phải là ROLE_ADMIN hoặc ROLE_USER): " + roleName);
            }
        }

        user.setRoles(newRoles);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Không thể xóa! Không tìm thấy người dùng với ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}