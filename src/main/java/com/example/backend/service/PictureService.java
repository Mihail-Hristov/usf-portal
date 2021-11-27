package com.example.backend.service;


import com.example.backend.models.entity.Picture;
import com.example.backend.models.view.PictureViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    public Picture createPicture(MultipartFile file) throws IOException;

    public Picture savePicture(Picture picture);
}
