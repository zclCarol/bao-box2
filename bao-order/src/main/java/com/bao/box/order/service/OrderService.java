package com.bao.box.order.service;

import com.bao.box.common.exception.BizCodeEnume;
import com.bao.box.common.utils.R;
import com.bao.box.order.dao.OrderMapper;
import com.bao.box.order.feign.ProductFeignService;
import com.bao.box.order.model.ConfirmOrder;
import com.bao.box.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("order")
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductFeignService productFeignService;

    /**
     *  查询订单
     */
    public R getOrders(Integer userId){
        List<Order> orders = orderMapper.getOrders(userId);
        //根据订单ID分组
        Map<Long, List<Order>> groupedById = orders.stream()
                .collect(Collectors.groupingBy(Order::getOrderId));

        // 将分组后的结果放入新的集合
        List<List<Order>> result = new ArrayList<>(groupedById.values());

        R r = R.ok().put("data",result);
        return r;
    }

    /**
     * //TODO 添加事务
     * 确认订单
     */
    public R addOrder(Integer[] cartIds){
        //根据cartIds和数据库购物车确认
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

            return R.error(BizCodeEnume.OVER_WARE.getCode(),BizCodeEnume.OVER_WARE.getMsg() + ":" + overName);
        }
        //插入订单
        for(Order order : orderList){
            order.setOrderId(currentTime);
        }
        orderMapper.addOrders(orderList);
        //删除购物车，并返回删除的cartIds
        R feignDelR = productFeignService.delShoppingCarts(cartIds);
        int feignDelRows = (Integer) feignDelR.get("delRows");
        if(feignDelRows != cartIds.length){
            //TODO 删除行数与IDS长度进行对比，如果不一样就回滚
        }
        //TODO 减库存操作

        R r = R.ok();
        return r;
    }
}
