package com.example.backend.service.impl;

import com.example.backend.model.entity.Picture;
import com.example.backend.repository.PictureRepository;
import com.example.backend.service.CloudinaryService;
import com.example.backend.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Picture createPicture(MultipartFile file) throws IOException {
        final CloudinaryImage uploaded = this.cloudinaryService.upload(file);

        Picture picture = new Picture();

        picture.setPublicId(uploaded.getPublicId());
        picture.setUrl(uploaded.getUrl());

        return picture;
    }

    @Override
    public Picture savePicture(Picture picture) {
        return pictureRepository.save(picture);
    }
}
