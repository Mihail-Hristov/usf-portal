package com.example.backend.service;


import com.example.backend.model.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    public Picture createPicture(MultipartFile file) throws IOException;

    public Picture savePicture(Picture picture);
}
