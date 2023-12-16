package com.bao.box.product;

import com.bao.box.common.utils.R;
import com.bao.box.product.model.ShoppingCart;
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
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setProductId(6);
//        shoppingCart.setNo(1);
//        R r = productService.addShoppingCart(shoppingCart);
//        System.out.printf(r.toString());
        Integer[] cartIds = {55,66,77,33,34};
        R r = productService.delShoppingCarts(cartIds);
        System.out.println(r.get("delRows"));
    }

}
