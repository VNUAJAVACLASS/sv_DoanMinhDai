package com.vnua.fita;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vnua.fita.entity.Role;
import com.vnua.fita.entity.User;
import com.vnua.fita.repository.RoleRepository;
import com.vnua.fita.repository.UserRepository;


@SpringBootApplication
public class BookstoreSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreSpringbootApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
	    return args -> {
	        // 1. Khởi tạo Role USER nếu chưa có
	        Role userRole = roleRepo.findByName(Role.RoleType.ROLE_USER)
	                .orElseGet(() -> {
	                    Role r = new Role();
	                    r.setName(Role.RoleType.ROLE_USER);
	                    return roleRepo.save(r);
	                });

	        // 2. Khởi tạo Role ADMIN nếu chưa có
	        Role adminRole = roleRepo.findByName(Role.RoleType.ROLE_ADMIN)
	                .orElseGet(() -> {
	                    Role r = new Role();
	                    r.setName(Role.RoleType.ROLE_ADMIN);
	                    return roleRepo.save(r);
	                });

	        // 3. Khởi tạo tài khoản Admin nếu chưa có
	        if (userRepo.findByUsername("admin").isEmpty()) {
	            User admin = new User();
	            admin.setUsername("admin");
	            admin.setPassword(encoder.encode("admin"));
	            admin.setRoles(Set.of(adminRole)); 
	            userRepo.save(admin);
	            System.out.println(">>> Đã khởi tạo tài khoản Admin mặc định thành công.");
	        }
	    };
	}
}
