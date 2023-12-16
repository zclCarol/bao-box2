package com.bao.box.order.feign;


import com.bao.box.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("bao-product")
public interface ProductFeignService {

    @PostMapping("/bao-product/product/delShoppingCarts")
    public R delShoppingCarts(@RequestBody Integer[] cartIds);

}
