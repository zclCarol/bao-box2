package com.bao.box.order;

import com.bao.box.common.exception.BizCodeEnume;
import com.bao.box.common.utils.R;
import com.bao.box.order.dao.OrderMapper;
import com.bao.box.order.feign.ProductFeignService;
import com.bao.box.order.model.ConfirmOrder;
import com.bao.box.order.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class BaoOrderApplicationTests {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductFeignService productFeignService;

    @Test
    void contextLoads() {
        Integer[] cartIds = {33,34,35};
        List<ConfirmOrder> confirmOrders = orderMapper.getConfirmOrders(cartIds);
        //根据cartIds生成订单
        Long currentTime = System.currentTimeMillis();
        List<Order> orderList = confirmOrders.stream()
                .map(confirmOrder -> {
                    Order order = new Order();
                    order.setUserId(confirmOrder.getUserId());
                    order.setProductId(confirmOrder.getProductId());
                    order.setProductNum(confirmOrder.getNum());
                    order.setWareNum(confirmOrder.getProductNum());
                    order.setProductName(confirmOrder.getProductName());
                    order.setProductPrice(confirmOrder.getNum() * confirmOrder.getProductSellingPrice());
                    order.setOrderTime(currentTime);
                    return order;
                })
                .collect(Collectors.toList());
        //判断是否有库存
        List<Order> hasOverWareList = orderList.stream()
                .filter(obj -> obj.getProductNum() > obj.getWareNum())
                .collect(Collectors.toList());
        if(!hasOverWareList.isEmpty()){
            String overName = hasOverWareList.stream()
                    .map(Order::getProductName) // 提取超出库存的商品Name
                    .collect(Collectors.joining(", "));
            //TODO增加那个商品不足
            System.out.println(overName);
        }
    }

    @Test
    void testFeign(){
        Integer[] cartIds = {33,34,35};
        R r = productFeignService.delShoppingCarts(cartIds);
        System.out.println(r.get("delRows"));
    }

}
