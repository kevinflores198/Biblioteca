package com.biblioteca.biblioteca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.biblioteca.biblioteca.Entities.User;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PortalController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin.html";
    }

    @PostMapping("/signedin")
    public String logged(@RequestParam String name, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap model) {

        try {
            userService.signin(name, email, password, password2);
            model.put("Success", "User signed in propertly");
        } catch (MyException e) {
            model.put("Error", e.getMessage());
            model.put("name", name);
            model.put("email", email);
            return "index.html";
        }
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String Error, ModelMap model) {

        if (Error != null) {
            model.put("Error", "User or password wrong");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/main")
    public String main(HttpSession session) {
        User logged = (User) session.getAttribute("usuariosession");

        if (logged.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        return "main.html";
    }

}
