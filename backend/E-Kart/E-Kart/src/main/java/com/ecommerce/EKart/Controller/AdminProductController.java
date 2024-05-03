package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Dtos.Request.ProductCreateReq;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController{

    @Autowired
    ProductService productService;
    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateReq req){
        Product product=productService.createNewProduct(req);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @DeleteMapping("/{productId}/delete")
    public ResponseEntity deleteProduct(@PathVariable int productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
        return new ResponseEntity("This Product deleted Successfully",HttpStatus.ACCEPTED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct(){
        List<Product> products=productService.findAllProduct();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product pro,@PathVariable int productId) throws ProductNotFoundException {
        Product product=productService.updateProduct(productId,pro);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
