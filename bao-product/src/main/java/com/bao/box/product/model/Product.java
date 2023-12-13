package com.bao.box.product.model;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private Integer productId;
    private String productName;
    private Integer categoryId;
    private String categoryName;
    private String productTitle;
    private String productIntro; //text
    private String productPicture; //char
    private Double productPrice;
    private Double productSellingPrice;
    private Integer productNum;
    private Integer productSales;
}
