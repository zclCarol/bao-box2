package com.bao.box.product.controller;

import com.bao.box.common.utils.R;
import com.bao.box.product.model.Category;
import com.bao.box.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/getHomeProductsByCategoryName")
    public Map<String, Object> getHomeProductsByCategoryName(@RequestBody Category category) {
        R r = productService.getHomeProductsByCategoryName(category.getCategoryName());
        return r;
    }

    @PostMapping("/getProductsByCategoryId")
    public Map<String, Object> getProductsByCategoryId(@RequestBody Category category) {
        R r = productService.getProductsByCategoryId(category.getCategoryId());
        return r;
    }

    @PostMapping("/getProducts")
    public Map<String, Object> getProducts( @RequestParam Integer pageSize,@RequestParam Integer page) {
        R r = productService.getProducts(pageSize,page);
        return r;
    }

    @PostMapping("/getAllCategory")
    public Map<String, Object> getAllCategory() {
        R r = productService.getAllCategory();
        return r;
    }

}
