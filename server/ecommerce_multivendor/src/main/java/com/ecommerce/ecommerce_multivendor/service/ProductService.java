package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.exceptions.ProductException;
import com.ecommerce.ecommerce_multivendor.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct (CreateProductRequest req, Seller seller);
    public void deleteProduct (Long productId) throws ProductException;
    public Product updateProducts(Long productId, Product product) throws ProductException;
    Product findProductById (Long productId) throws ProductException;
    List<Product> searchProducts(String query);
    public Page<Product> getAllProducts(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minimumPrice,
            Integer maximumPrice,
            Integer minimumDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );

    List<Product>getProductBySellerId(Long sellerId);

}
