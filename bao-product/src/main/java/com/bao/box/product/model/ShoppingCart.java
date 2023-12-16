package com.bao.box.product.model;

import lombok.Data;

@Data
public class ShoppingCart {

    private Integer id;
    private Integer no; //这是用户NO
    private String userId;
    private Integer num;
    private Integer productId;
    private String productPicture;
    private String productName;
    private Double productSellingPrice;
}
