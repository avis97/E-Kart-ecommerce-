package com.ecommerce.EKart.Service.Impl;

import com.ecommerce.EKart.Dtos.Request.ProductCreateReq;
import com.ecommerce.EKart.Entitys.Category;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Repository.CategoryRepository;
import com.ecommerce.EKart.Repository.ProductRepository;
import com.ecommerce.EKart.Service.ProductService;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserService userService;


    @Override
    public Product createNewProduct(ProductCreateReq req) {
        Category topLevel=categoryRepository.findByName(req.getFirstCategory());
        if(topLevel==null){
            Category topLavelCategory=new Category();
            topLavelCategory.setName(req.getFirstCategory());
            topLavelCategory.setLevel(1);

            topLevel=categoryRepository.save(topLavelCategory);
        }
        Category secondLevel=categoryRepository.findByNameAndParent(req.getSecondCategory(),topLevel.getName());
        if(secondLevel==null){
            Category secondLavelCategory=new Category();
            secondLavelCategory.setName(req.getSecondCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);
            secondLevel=categoryRepository.save(secondLavelCategory);
        }
        Category thirdLevel=categoryRepository.findByNameAndParent(req.getThirdCategory(), secondLevel.getName());
        if(thirdLevel==null){
            Category thirdLavelCategory=new Category();
            thirdLavelCategory.setName(req.getThirdCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);
            thirdLevel=categoryRepository.save(thirdLavelCategory);
        }
        Product product=new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDetails(req.getDescriptions());
        product.setDiscountPrice(req.getDiscountPrice());
        product.setDiscountPercent(req.getDiscountPercent());
        product.setImgUrl(req.getImgUrl());
        product.setBrand(req.getBrandName());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSizeSet());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreationDate(req.getCreateTime());

        Product saveProduct=productRepository.save(product);
        return saveProduct;
    }
    @Override
    public String deleteProduct(int productId) throws ProductNotFoundException {
        Product product=findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product Deleted Successfully With This Id"+ productId;
    }
    @Override
    public Product updateProduct(int productId, Product req) throws ProductNotFoundException {
        Product product=findProductById(productId);

        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
            product.setPrice(req.getPrice());
            product.setDiscountPrice(req.getDiscountPrice());
        }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(int productId) throws ProductNotFoundException {
        Product product;
        try{
            product=productRepository.findById(productId).get();
        }catch (Exception e){
            throw new ProductNotFoundException("Product is not found with this id");
        }
        return product;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> color,
                                       List<String> sizes, int minPrice, int maxPrice,
                                       int minDiscount, String sort, String stock, int pageNumber,
                                       int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository
                .filterProduct(category, minPrice, maxPrice, minDiscount, sort);
        if (!color.isEmpty()) {
            products = products.
                    stream().
                    filter(p -> color.stream().
                            anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }
        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.
                        stream().
                        filter(p -> p.getQuantity() > 0).
                        collect(Collectors.toList());
            } else if (stock.equals("0ut_of_stock")) {
                products = products.stream().filter(
                        p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }
        int startIndex=(int) pageable.getOffset();
        int endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product> pageContent=products.subList(startIndex,endIndex);
        Page<Product> filterProduct=new PageImpl<>(pageContent,pageable,products.size());
        return filterProduct;
    }

}
