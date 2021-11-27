package com.example.backend.controllers;

import com.example.backend.models.binding.CreateProductBindingModel;
import com.example.backend.models.service.CreateProductServiceModel;;
import com.example.backend.service.ColourService;
import com.example.backend.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/portal/products")
public class ProductController {

    private final ProductService productService;
    private final ColourService colourService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ColourService colourService, ModelMapper modelMapper) {
        this.productService = productService;
        this.colourService = colourService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public CreateProductBindingModel createProductBindingModel() {
        return new CreateProductBindingModel();
    }

    @GetMapping()
    public String products(Model model) {

        model.addAttribute("products", productService.getAllProducts());

        return "products";
    }

    @PreAuthorize("hasRole('MERCH_ADMIN')")
    @GetMapping("/create")
    public String viewCreateProduct(Model model) {

        return "product-create";
    }

    @PreAuthorize("hasRole('MERCH_ADMIN')")
    @PostMapping("/create")
    public String createProduct(@Valid CreateProductBindingModel createProductBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createProductBindingModel", createProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createProductBindingModel", bindingResult);

            return "redirect:create";
        }

        productService.createNewProduct(modelMapper.map(createProductBindingModel, CreateProductServiceModel.class), principal);

        return "redirect:/portal/products";
    }

    @GetMapping("{id}/details")
    public String details(@PathVariable String id) {

        productService.getProductById(id);

        return "product-details";
    }
}
