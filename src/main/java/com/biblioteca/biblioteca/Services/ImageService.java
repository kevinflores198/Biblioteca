package com.biblioteca.biblioteca.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.biblioteca.biblioteca.Entities.Image;
import com.biblioteca.biblioteca.Repositories.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(MultipartFile archive) {

        if (archive != null) {
            try {

                Image image = new Image();

                image.setMime(archive.getContentType());

                image.setName(archive.getName());

                image.setContent(archive.getBytes());

                return imageRepository.save(image);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
