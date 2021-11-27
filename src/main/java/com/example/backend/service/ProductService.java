package com.example.backend.service;

import com.example.backend.models.binding.CreateProductBindingModel;
import com.example.backend.models.service.CreateProductServiceModel;
import com.example.backend.models.service.CreateTripServiceModel;
import com.example.backend.models.view.ProductViewModel;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;

public interface ProductService {

    public ProductViewModel createNewProduct(CreateProductServiceModel product, Principal principal) throws IOException;

    Set<ProductViewModel> getAllProducts();

    public ProductViewModel getProductById(String id);
}
