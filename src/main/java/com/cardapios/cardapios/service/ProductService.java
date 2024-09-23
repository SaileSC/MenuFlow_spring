package com.cardapios.cardapios.service;


import com.cardapios.cardapios.domain.product.Product;
import com.cardapios.cardapios.domain.product.ProductRequestDTO;
import com.cardapios.cardapios.domain.product.ProductResponseDTO;
import com.cardapios.cardapios.repository.ProductRepository;
import com.cardapios.cardapios.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public ProductResponseDTO create(ProductRequestDTO data){
        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setPrice(data.price());
        newProduct.setQuantity(data.quantity());

        Product createdProd = productRepository.save(newProduct);

        return new ProductResponseDTO(createdProd.getId(), createdProd.getName(), createdProd.getQuantity(), createdProd.getPrice());
    }


    public List<ProductResponseDTO> list() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }


    public ProductResponseDTO read(int id){
        Optional<Product> product = productRepository.findById(id);
        Product obj = product.orElseThrow(() -> new ObjectNotFoundException("Product not found! Id: " + id + ", Type: "+ Product.class.getName()));
        return new ProductResponseDTO(obj.getId(), obj.getName(), obj.getQuantity(), obj.getPrice());
    }

    public void delete(int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
        }else{
            throw new ObjectNotFoundException("Product not found! Id: "+ id + ", Type: " + Product.class.getName());
        }
    }


    public ProductResponseDTO update(ProductResponseDTO data){
        productRepository.findById(data.id()).orElseThrow(() -> new ObjectNotFoundException("Product not found"));

        Product productObj = new Product();
        productObj.setId(data.id());
        productObj.setName(data.name());
        productObj.setPrice(data.price());
        productObj.setQuantity(data.quantity());

        Product updatedProd = productRepository.save(productObj);

        return new ProductResponseDTO(updatedProd.getId(), updatedProd.getName(),updatedProd.getQuantity(), updatedProd.getPrice());
    }
}
