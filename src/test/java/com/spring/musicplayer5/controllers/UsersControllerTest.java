package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.entity.Role;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.repositories.RoleRepository;
import com.spring.musicplayer5.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersControllerTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
//    @Test
//    public void testUser() {
//        Role role = new Role();
//        role.setId(1);
//        User user = User.builder()
//                .username("lamlbx123")
//                .name("Levi")
//                .password("123")
//                .country("HUE")
//                .age(21)
//                .role(role)
//                .build();
//        Role role2 = new Role();
//        role2.setId(2);
//        User user2 = User.builder()
//                .username("nva")
//                .name("Levia")
//                .password("123")
//                .country("HUE")
//                .age(21)
//                .role(role2)
//                .build();
//        userRepository.save(user2);
//    }
//
//    @Test
//    public void testRole() {
//        Role role = Role.builder()
//                .name("admin")
//                .code("ADMIN")
//                .build();
//        Role role2 = Role.builder()
//                .name("user")
//                .code("USER")
//                .build();
//        roleRepository.save(role);
//        roleRepository.save(role2);
//    }

}