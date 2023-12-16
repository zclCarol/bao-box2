package com.bao.box.order.controller;

import com.bao.box.common.utils.R;
import com.bao.box.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public R addOrder(@RequestBody Integer[] cartIds) {
        R r = orderService.addOrder(cartIds);
        return r;
    }

    @PostMapping("/getOrders")
    public R getOrders(@RequestParam Integer userId) {
        R r = orderService.getOrders(userId);
        return r;
    }
}
