package com.bao.box.product.service;

import com.bao.box.product.dao.ProductMapper;
import com.bao.box.common.utils.R;
import com.bao.box.product.model.Category;
import com.bao.box.product.model.Product;
import com.bao.box.product.model.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 根据categoryName 获取首页产品 (只取7条)
     */
    public R getHomeProductsByCategoryName(String categoryName){
        List<Product> list = productMapper.getHomeProductsByCategoryName(categoryName);
        R r = R.ok().put("Products", list);

        return r;
    }

    /**
     * 根据categoryId 获取产品
     * TODO 并未做分页处理
     */
    public R getProductsByCategoryId(Integer categoryId){
        List<Product> list = productMapper.getProductsByCategoryId(categoryId);
        R r = R.ok().put("Products", list);

        return r;
    }

    /**
     * 获取所有产品
     */
    public R getProducts(Integer pageSize,Integer page){
        int pageStart = pageSize*(page-1);
        List<Product> list = productMapper.getProducts(pageSize,pageStart);
        int total = productMapper.getProductsTotal();
        R r = R.ok().put("Products", list).put("total",total);

        return r;
    }

    /**
     * 获取所有产品分类
     */
    public R getAllCategory(){
        List<Category> list = productMapper.getAllCategory();
        R r = R.ok().put("AllCategory", list);

        return r;
    }

    /**
     * 加入购物车
     */
    public R addShoppingCart(ShoppingCart shoppingCart){
        //判断购物车是否已有
        Integer hasShoppingCart = productMapper.hasShoppingCart(shoppingCart.getNo(),shoppingCart.getProductId());
        if(hasShoppingCart != null){
            productMapper.updateCartNum(hasShoppingCart);
        }else {
            productMapper.addShoppingCart(shoppingCart);
        }
        ShoppingCart newCart = productMapper.getShoppingCart(shoppingCart.getNo(),shoppingCart.getProductId());
        ShoppingCart temp =productMapper.listOneCart(shoppingCart.getNo(),newCart.getId());
        R r = R.ok().put("data", temp);
        return r;
    }

    /**
     * 查询用户购物车当前总数
     */
    public R listCart(Integer userNo){
        List<ShoppingCart> list = productMapper.listCart(userNo);
        R r = R.ok().put("list", list);
        return r;
    }

    /**
     * 删除购物车
     */
    public R delShoppingCart(Integer cartId,Integer productId){
        productMapper.delShoppingCart(cartId,productId);
        R r = R.ok();
        return r;
    }

    /**
     * 批量删除购物车
     */
    public R delShoppingCarts(Integer[] cartIds){
        int delRows = productMapper.delShoppingCarts(cartIds);
        R r = R.ok().put("delRows",delRows);
        return r;
    }



}
