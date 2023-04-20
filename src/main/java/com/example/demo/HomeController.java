package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;
import java.util.Optional;

@Controller
public class HomeController {
    private PhotoRepository photoRepository;

    @Autowired
    public HomeController(@Qualifier("photoTable") PhotoRepository userRepository) {
        this.photoRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "error";
    }

    @PostMapping("/upload")
    public String takePhoto(@RequestBody byte[] image) {
        Photo photo = new Photo();
        photo.setImage(image);
        photoRepository.save(photo);
        System.out.println(photo.getImage());
        return "redirect:/";
    }

    @GetMapping("/photo")
    public String showPhoto(Model model) {
        System.out.println(photoRepository.count());
            Optional<Photo> photo = photoRepository.findById(photoRepository.count());
            if (photo.isPresent()) {
                String base64 = Base64.getEncoder().encodeToString(photo.get().getImage());
                System.out.println("OK");
                model.addAttribute("image", base64);
            }
        return "home";
    }
}