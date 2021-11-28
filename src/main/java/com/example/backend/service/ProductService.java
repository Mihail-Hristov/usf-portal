package com.example.backend.service;

import com.example.backend.model.service.CreateProductServiceModel;
import com.example.backend.model.view.ProductViewModel;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;

public interface ProductService {

    public ProductViewModel createNewProduct(CreateProductServiceModel product, Principal principal) throws IOException;

    Set<ProductViewModel> getAllProducts();

    public ProductViewModel getProductById(String id);
}
