package com.ecommerce.EKart.Controller;


import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController{
    @Autowired
    ProductService productService;
    @GetMapping("/product")
    public ResponseEntity<Page<Product>> findProductHandler(
            @RequestParam String category,@RequestParam List<String> color,@RequestParam List<String> size,
            @RequestParam Integer minPrice,@RequestParam Integer maxPrice,@RequestParam Integer minDiscount,
            @RequestParam String sort, @RequestParam String stock,@RequestParam Integer pageNumber,@RequestParam Integer pageSize){
        Page<Product> res=productService.getAllProduct(
                category,color,size,minPrice,maxPrice,
                minDiscount,sort,stock,pageNumber,pageSize);

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable int productId) throws ProductNotFoundException {
        Product product=productService.findProductById(productId);
        return new ResponseEntity<>(product,HttpStatus.ACCEPTED);
    }
}
