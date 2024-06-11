package com.biblioteca.biblioteca.Controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.biblioteca.biblioteca.Entities.User;
import com.biblioteca.biblioteca.Services.UserService;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    UserService userService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<byte[]> imagenPublicacion(@PathVariable String id){
        
        User user = userService.getOne(id);
        
        byte[] imagen = user.getImage().getContent();
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }

}
