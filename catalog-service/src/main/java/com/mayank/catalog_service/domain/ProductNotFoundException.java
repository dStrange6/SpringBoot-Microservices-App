package com.mayank.catalog_service.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
    // utility function
    public static ProductNotFoundException forCode(String code)
    {
        return new ProductNotFoundException("Product not found with code: "+code);
    }
}
