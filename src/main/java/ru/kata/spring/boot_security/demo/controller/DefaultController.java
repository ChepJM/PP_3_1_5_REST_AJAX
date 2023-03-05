package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {


    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/addAdmin")
    public String addAdmin() {
        String username = "admin";
        String secondName = "adminName";
        String password = "12345";
        int age = 14;
        String email = "admin@mail.ru";
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("ROLE_ADMIN"));
        roleList.add(new Role("ROLE_USER"));
        User user = new User(
                username,
                secondName,
                password,
                age,
                email,
                roleList
        );
        userService.saveUser(user);

        return "redirect:/";
    }
}
