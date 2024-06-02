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

    @GetMapping("/signin")
    public String signin() {
        return "autor_form.html";
    }

    @PostMapping("/signedin")
    public String signedin(@RequestParam String name, ModelMap model) {

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

    @GetMapping("/modify/{ID}")
    public String modify(@PathVariable String ID, ModelMap model) {
System.out.println("HELLO");
        model.put("autor", autorService.getOne(ID));
        return "autor_modify.html";
    }

    @PostMapping("/modify/{ID}")
    public String modify(@PathVariable String ID,
            String name,
            ModelMap model) {
        try {
            autorService.modifyAutor(ID, name);
            return "redirect:../list";
        } catch (MyException e) {
            model.put("Error", e.getMessage());
            return "autor_modify.html";
        }
    }

}
