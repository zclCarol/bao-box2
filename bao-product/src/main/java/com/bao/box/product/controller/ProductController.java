package com.bao.box.product.controller;

import com.bao.box.common.utils.R;
import com.bao.box.product.model.Category;
import com.bao.box.product.model.ShoppingCart;
import com.bao.box.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/getHomeProductsByCategoryName")
    public R getHomeProductsByCategoryName(@RequestBody Category category) {
        R r = productService.getHomeProductsByCategoryName(category.getCategoryName());
        return r;
    }

    @PostMapping("/getProductsByCategoryId")
    public R getProductsByCategoryId(@RequestBody Category category) {
        R r = productService.getProductsByCategoryId(category.getCategoryId());
        return r;
    }

    @PostMapping("/getProducts")
    public R getProducts( @RequestParam Integer pageSize,@RequestParam Integer page) {
        R r = productService.getProducts(pageSize,page);
        return r;
    }

    @PostMapping("/getAllCategory")
    public R getAllCategory() {
        R r = productService.getAllCategory();
        return r;
    }

    @PostMapping("/addShoppingCart")
    public R addShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        R r = productService.addShoppingCart(shoppingCart);
        return r;
    }

    @PostMapping("/listCart")
    public R listCart( @RequestParam Integer userNo) {
        R r = productService.listCart(userNo);
        return r;
    }

    @PostMapping("/delShoppingCart")
    public R delShoppingCart( @RequestParam Integer cartId,@RequestParam Integer productId) {
        R r = productService.delShoppingCart(cartId,productId);
        return r;
    }

    @RequestMapping("/delShoppingCarts")
    public R delShoppingCarts(@RequestBody Integer[] cartIds) {
        R r = productService.delShoppingCarts(cartIds);
        return r;
    }

}
