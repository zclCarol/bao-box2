package com.bao.box.order.dao;

import com.bao.box.order.model.ConfirmOrder;
import com.bao.box.order.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select({"<script>",
            " SELECT o.order_id,o.user_id,o.product_num,o.product_price,o.order_time, ",
            "        p.product_name,p.product_picture, p.product_selling_price  ",
            " from orders o ",
            " LEFT JOIN product p ON p.product_id = o.product_id",
            " WHERE o.user_id = #{userId} ",
            " </script> "})
    List<Order> getOrders(@Param("userId") Integer userId);

    @Select({"<script>",
            " SELECT s.id,s.user_id,s.product_id,s.num,",
            " p.product_selling_price, p.product_num, p.product_name  ",
            " FROM shopping_cart s ",
            " LEFT JOIN product p ON s.product_id = p.product_id ",
            " WHERE 1 = 1 ",
            "      AND s.id IN ",
            "       <foreach collection='cartIds' item='item' open='(' close=')' separator=',' >",
            "           #{item}",
            "       </foreach>",
            " </script> "})
    List<ConfirmOrder> getConfirmOrders(@Param("cartIds") Integer[] cartIds);

    @Insert({"<script>",
            " INSERT INTO orders ",
            " ( ",
            "  user_id , product_id, order_id, product_num,product_price, order_time ",
            " ) ",
            " VALUES ",
            "<foreach collection='orders' item='order' separator=','>",
                "(",
            "       #{order.userId}, #{order.productId} , #{order.orderId},    ",
            "       #{order.productNum}, #{order.productPrice} , #{order.orderTime}    ",
                ")",
            "</foreach>",
            "</script>" })
    int addOrders(@Param("orders") List<Order> orders);

}
