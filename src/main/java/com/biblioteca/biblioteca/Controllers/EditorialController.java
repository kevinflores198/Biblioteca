package com.biblioteca.biblioteca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Services.EditorialService;

@Controller
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String name, ModelMap model) {

        try {
            editorialService.makeEditorial(name);
            model.put("Success", "Editorial was charged correctly");
            
        } catch (MyException e) {
            model.put("Error", e.getMessage());

            return "editorial_form.html";
        }
        return "index.html";
    }
}
