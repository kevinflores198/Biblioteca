package com.biblioteca.biblioteca.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.biblioteca.biblioteca.Entities.Image;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Repositories.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(MultipartFile archive) throws MyException {

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

    public Image updatImage(MultipartFile archive, String idImage) throws MyException {

        if (archive != null) {
            try {
                Image image = new Image();

                if (idImage != null) {
                    Optional<Image> answer = imageRepository.findById(idImage);
                    if (answer.isPresent()) {
                        image = answer.get();
                    }
                }

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
