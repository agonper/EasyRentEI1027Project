package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.utils.FileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Alberto on 12/05/2016.
 */

@Controller
@RequestMapping("/uploads")
public class PhotosController {

    @Autowired
    private FileLoader fileLoader;

    @ResponseBody
    @RequestMapping(value = "/profile-pics/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable String imageName) throws IOException {
        return Files.readAllBytes(fileLoader.load("profile-pics", imageName).toPath());
    }
}
