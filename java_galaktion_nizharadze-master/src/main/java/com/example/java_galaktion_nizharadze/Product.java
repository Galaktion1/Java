package com.example.java_galaktion_nizharadze;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long ID;
    private String name;
    private String brand;
    private Integer price;
    private Integer expiration;


    public int getPriceRange(){
        return this.price/100;
    }
    public Product(String name, String brand, Integer price, Integer expiration) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.expiration = expiration;
    }
}

