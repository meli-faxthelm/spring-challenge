package com.meli.springchallenge.model;

import com.meli.springchallenge.dto.ProductDTO;

public class Product {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public Product(){

    }

    public Integer getProduct_id() {
        return product_id;
    }

    public ProductDTO toProductDTO(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_id(this.product_id);
        productDTO.setProductName(this.productName);
        productDTO.setType(this.type);
        productDTO.setBrand(this.brand);
        productDTO.setColor(this.color);
        productDTO.setNotes(this.notes);
        return productDTO;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
