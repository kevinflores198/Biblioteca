package com.biblioteca.biblioteca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Services.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/registrar")
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String name, ModelMap model) {

        try {
            autorService.makeAutor(name);
            model.put("Success", "Autor was charged correctly");

        } catch (MyException e) {
            model.put("Error", e.getMessage());

            return "autor_form.html";
        }
        return "index.html";
    }
}
