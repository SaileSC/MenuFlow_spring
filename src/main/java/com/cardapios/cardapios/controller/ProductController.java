package com.cardapios.cardapios.controller;

import com.cardapios.cardapios.domain.message.ResponseMessage;
import com.cardapios.cardapios.domain.product.Product;
import com.cardapios.cardapios.domain.product.ProductRequestDTO;
import com.cardapios.cardapios.domain.product.ProductResponseDTO;
import com.cardapios.cardapios.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO body){
        ProductResponseDTO product = productService.create(body);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();
        System.out.println(uri.getHost());
        return ResponseEntity.created(uri).body(product);
    }


    @GetMapping("/product")
    public ResponseEntity<List<ProductResponseDTO>> listProducts(){
        return ResponseEntity.ok().body(productService.list());
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> readProduct(@PathVariable int id){
        return ResponseEntity.ok(productService.read(id));
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/product")
    public ResponseEntity<ProductResponseDTO> update(@RequestBody ProductResponseDTO body){
        ProductResponseDTO prodUpdated = productService.update(body);
        return ResponseEntity.ok().body(prodUpdated);
    }

}
