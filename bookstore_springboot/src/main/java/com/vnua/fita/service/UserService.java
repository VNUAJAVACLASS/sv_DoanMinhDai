package com.vnua.fita.service;

import com.vnua.fita.entity.User;
import java.util.List;
import java.util.Set;

public interface UserService {
    User registerUser(User user);

    User findByUsername(String username);
    User findById(Long id);
    List<User> findAllUsers();

    User save(User user);
    User updateUserRoles(Long id, Set<String> roleNames);
    void deleteById(Long id);
}