package com.bao.box.order.model;

import lombok.Data;

@Data
public class ConfirmOrder {

    private Integer id; //原购物车ID
    private Integer userId; //这是用户NO
    private Integer num;
    private Integer productId;
    private Integer productNum;
    private String productName;
    private Double productSellingPrice;

}
