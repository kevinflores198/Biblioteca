package com.biblioteca.biblioteca.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.biblioteca.Entities.Autor;
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

    @GetMapping("/list")
    public String listing(ModelMap model) {
        List<Autor> autors = autorService.autorList();

        model.addAttribute("autors", autors);
        return "autor_list.html";
    }

    @GetMapping("/modify/{id}")
    public String modity(@PathVariable String id, ModelMap model) {

        model.put("autor", autorService.getOne(id));
        return "autor_modify.html";
    }

    @PostMapping("/modify/{id}")
    public String modity(@PathVariable String id, String nombre, ModelMap model) {
        try {
            autorService.modifyAutor(id, nombre);
            return "redirect:../list";
        } catch (MyException e) {
            model.put("Error", e.getMessage());
            return "autor_modify.html";
        }
    }

}
