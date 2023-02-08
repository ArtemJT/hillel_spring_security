package com.example.hw_31_spring_security.services;

import com.example.hw_31_spring_security.dto.ProductDto;
import com.example.hw_31_spring_security.model.Product;
import com.example.hw_31_spring_security.repository.ProductRepository;
import com.example.hw_31_spring_security.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Artem Kovalov on 04.02.2023
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> findAllById(List<Integer> ids) throws EntityNotFoundException {
        Iterable<Product> allById = productRepository.findAllById(ids);
        return Mapper.iterableToDto(allById, ProductDto.class);
    }

    public List<ProductDto> deleteAllById(List<Integer> ids) throws EntityNotFoundException {
        List<ProductDto> productDtoList = findAllById(ids);
        if (productDtoList.isEmpty()) {
            throw new EntityNotFoundException();
        }
        productRepository.deleteAllById(ids);
        return productDtoList;
    }

    public List<ProductDto> findAllProducts() {
        Stream<Product> productStream = StreamSupport.stream(productRepository.findAll().spliterator(), false);
        List<Product> productList = productStream.collect(Collectors.toList());
        return Mapper.allToDto(productList, ProductDto.class);
    }

    public void addProduct(ProductDto productDto) {
        Product product = Mapper.toEntity(productDto, Product.class);
        productRepository.save(product);
        productDto.setId(product.getId());
    }

    public boolean isProductExists(String productName) {
        return productRepository.existsProductByName(productName);
    }
}
