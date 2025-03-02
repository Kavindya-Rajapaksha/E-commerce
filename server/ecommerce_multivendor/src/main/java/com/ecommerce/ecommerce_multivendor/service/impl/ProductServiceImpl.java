package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.entity.Category;
import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.exceptions.ProductException;
import com.ecommerce.ecommerce_multivendor.repository.CategoryRepository;
import com.ecommerce.ecommerce_multivendor.repository.ProductRepository;
import com.ecommerce.ecommerce_multivendor.request.CreateProductRequest;
import com.ecommerce.ecommerce_multivendor.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest req, Seller seller) {
        Category category1=categoryRepository.findByCategoryId(req.getCategory());
        if(category1 == null){
            Category category = new Category();
            category.setCategoryId(req.getCategory());
            category.setLevel(1);
            category1=categoryRepository.save(category);
        }

        Category category2 = categoryRepository.findByCategoryId(req.getCategory2());
        if(category2 == null){
            Category category = new Category();
            category.setCategoryId(req.getCategory2());
            category.setLevel(2);
            category.setParentCategory(category1);
            category2=categoryRepository.save(category);
        }

        Category category3 = categoryRepository.findByCategoryId(req.getCategory3());
        if(category3 == null){
            Category category = new Category();
            category.setCategoryId(req.getCategory3());
            category.setLevel(3);
            category3=categoryRepository.save(category);
        }

        int discountPercentage = calculateDiscountPercentage(req.getMrpPrice(), req.getSellingPrice());

        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category3);
        product.setDescription(req.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setSellingPrice(req.getSellingPrice());
        product.setImages(req.getImages());
        product.setMrpPrice(req.getMrpPrice());
        product.setSizes(product.getSizes());
        product.setDiscountPercent(discountPercentage);

        return productRepository.save(product);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if(mrpPrice<=0){
            System.out.println("mrp: "+mrpPrice);
            throw new IllegalArgumentException("Actual price must be greater than 0");
        }
        double discount = mrpPrice-sellingPrice;
        double discountPercentage = (discount/mrpPrice)*100;
        return (int)discountPercentage;
    }

    @Override
    public void deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public Product updateProducts(Long productId, Product product) throws ProductException {
        findProductById(productId);
        product.setId(productId);

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productRepository.findById(productId).orElseThrow(()->
                new ProductException("Product not found with Id - "+productId)
        );
    }

    @Override
    public List<Product> searchProducts(String query) {

        return productRepository.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProducts(String category, String brand, String colors, String sizes, Integer minimumPrice, Integer maximumPrice, Integer minimumDiscount, String sort, String stock, Integer pageNumber) {
        Specification<Product>specification=(root, query, criteriaBuilder) ->{
            List<Predicate>predicate=new ArrayList<>();

            if(category!=null){
                Join<Product, Category> categoryJoin= root.join("category");
                predicate.add(criteriaBuilder.equal(categoryJoin.get("categoryId"),category));
            }
            if (colors != null && !colors.isEmpty()){
                System.out.println("colour: "+colors);
                predicate.add(criteriaBuilder.equal(root.get("color"),colors));
            }

            //Filter by size (single value)
            if(sizes != null && !sizes.isEmpty()){
                predicate.add(criteriaBuilder.equal(root.get("size"),sizes));
            }

            if(minimumPrice!= null){
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"),maximumPrice));
            }

            if(maximumPrice!=null){
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"),maximumPrice));
            }
            if(minimumDiscount!=null){
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"),minimumDiscount));
            }
            if(stock!=null){
                predicate.add(criteriaBuilder.equal(root.get("stock"),stock));
            }
            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        };
        Pageable pageable;
        if(sort!=null && !sort.isEmpty()){
            switch (sort) {
                case "price_low":
                    pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("sellingPrice").ascending());
                    break;
                case "price_high":
                    pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.by("sellingPrice").descending());
                    break;
                default:
                    pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 10, Sort.unsorted());
                    break;
            }
        }else{
            pageable=PageRequest.of(pageNumber!=null?pageNumber:0,10,Sort.unsorted());
        }
        return productRepository.findAll(specification,pageable);
    }

    @Override
    public List<Product> getProductBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
}
