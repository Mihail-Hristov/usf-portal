package com.example.backend.service.impl;

import com.example.backend.exceprtion.ObjectNotFoundException;
import com.example.backend.models.entity.Colour;
import com.example.backend.models.entity.Product;
import com.example.backend.models.entity.Stock;
import com.example.backend.models.entity.User;
import com.example.backend.models.service.CreateProductServiceModel;
import com.example.backend.models.view.ProductViewModel;
import com.example.backend.repository.ProductRepository;
import com.example.backend.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ColourService colourService;
    private final StockService stockService;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, ColourService colourService, StockService stockService, ModelMapper modelMapper, PictureService pictureService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.colourService = colourService;
        this.stockService = stockService;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }

    @Transactional
    @Override
    public ProductViewModel createNewProduct(CreateProductServiceModel product, Principal principal) throws IOException {

        Product newProduct = modelMapper.map(product, Product.class);

        var picture = pictureService.createPicture(product.getPicture());
        pictureService.savePicture(picture);

        Colour colour = colourService.findColourByName(product.getColour());
        newProduct.setColour(colour);

        User user = userService.getUserByUsername(principal.getName());
        newProduct.setOwner(user);

        newProduct.setPicture(picture);
        productRepository.save(newProduct);

        Stock stock = modelMapper.map(product, Stock.class);
        stock.setProduct(newProduct);
        stockService.saveStock(stock);

        return modelMapper.map(newProduct, ProductViewModel.class);
    }

    @Override
    public Set<ProductViewModel> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ProductViewModel getProductById(String id) {

        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ObjectNotFoundException(id);
        }

        return modelMapper.map(product, ProductViewModel.class);
    }
}
