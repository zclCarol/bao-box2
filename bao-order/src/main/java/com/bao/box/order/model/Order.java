package com.bao.box.order.model;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Long orderId;
    private Integer userId; //这是用户NO
    private Integer productId;
    private String productName;
    private Integer productNum;
    private Integer wareNum;
    private Double productPrice;
    private Long orderTime;
    private String productPicture;
    private Double productSellingPrice;

}
