package com.example.hw_31_spring_security.controller;

import com.example.hw_31_spring_security.dto.ProductDto;
import com.example.hw_31_spring_security.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        log.info("products page");
        List<ProductDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "product/products";
    }

    @GetMapping("addProduct")
    public String addProduct() {
        log.info("product add page");
        return "product/addProduct";
    }

    @PostMapping("addProduct")
    public String addProduct(@RequestParam String productName,
                             @RequestParam(defaultValue = "0") String cost, Model model) {
        log.info("product add post page");
        String msg;
        if (productName.equals("")) {
            msg = "Product name must be filled";
        } else if (productService.isProductExists(productName)) {
            msg = "Product exists!";
        } else {
            ProductDto productDto = new ProductDto(null, productName, Double.parseDouble(cost));
            productService.addProduct(productDto);
            msg = "Product added successfully!";
            model.addAttribute("productDto", productDto);
        }
        model.addAttribute("message", msg);
        return "product/addProduct";
    }

    @PostMapping
    public String findOrDeleteProduct(@RequestParam String action, Model model,
                                      @RequestParam(required = false) List<Integer> productId) {
        log.info("product post page");
        if (productId == null) {
            model.addAttribute("message", "Please check product first");
            return getAllProducts(model);
        }

        if (action.equals("delete")) {
            log.info("\"DELETE\" CALLED");
            return deleteProduct(model, productId);
        }

        log.info("\"FIND\" CALLED");
        boolean isDeleted = false;
        List<ProductDto> productDtoList = productService.findAllById(productId);
        model.addAttribute("productList", productDtoList);
        model.addAttribute("deleted", isDeleted);
        return "product/product";
    }

    @DeleteMapping
    public String deleteProduct(Model model, @RequestParam List<Integer> productId) {
        log.info("product delete page");
        List<ProductDto> productDtoList = productService.deleteAllById(productId);
        boolean isDeleted = true;
        model.addAttribute("productList", productDtoList);
        model.addAttribute("deleted", isDeleted);
        return "product/product";
    }
}
