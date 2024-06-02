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
import com.biblioteca.biblioteca.Entities.Editorial;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Services.EditorialService;

@Controller
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/signin")
    public String signin() {
        return "editorial_form.html";
    }

    @PostMapping("/signedin")
    public String signedin(@RequestParam String name, ModelMap model) {

        try {
            editorialService.makeEditorial(name);
            model.put("Success", "Editorial was charged correctly");

        } catch (MyException e) {
            model.put("Error", e.getMessage());

            return "editorial_form.html";
        }
        return "index.html";
    }

    @GetMapping("/list")
    public String listing(ModelMap model) {
        List<Editorial> editorials = editorialService.editorialList();

        model.addAttribute("editorials", editorials);
        return "editorial_list.html";
    }

    @GetMapping("/modify/{ID}")
    public String modify(@PathVariable String ID, ModelMap model) {
        model.put("editorial", editorialService.getOne(ID));
        return "editorial_modify.html";
    }

    @PostMapping("/modify/{ID}")
    public String modify(@PathVariable String ID, String name, ModelMap model) {
        try {
            editorialService.modifyEditorial(name, ID);
            return "redirect:../list";
        } catch (Exception e) {
            model.put("Error", e.getMessage());
            return "editorial_modify.html";
        }
    }
}
