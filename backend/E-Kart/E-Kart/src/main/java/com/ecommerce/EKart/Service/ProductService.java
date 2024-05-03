package com.ecommerce.EKart.Service;

import com.ecommerce.EKart.Dtos.Request.ProductCreateReq;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService{
    Product createNewProduct(ProductCreateReq req);
    String deleteProduct(int productId) throws ProductNotFoundException;
    Product updateProduct(int productId,Product product) throws ProductNotFoundException;
    Product findProductById(int productId) throws ProductNotFoundException;
    List<Product> findProductByCategory(String category);
    Page<Product> getAllProduct(String category,List<String> color
    ,List<String> sizes,int minPrice,int maxPrice,int minDiscount,
    String sort,String stock,int pageNumber,int pageSize);
    List<Product> findAllProduct();
}
