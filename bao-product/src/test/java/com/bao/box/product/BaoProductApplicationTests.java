package com.bao.box.product;

import com.bao.box.common.utils.R;
import com.bao.box.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaoProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
//        R r = productService.getProductsByCategoryId("手机");
//        System.out.printf(r.toString());
    }

}
