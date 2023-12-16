package com.bao.box.product.dao;

import com.bao.box.product.model.Category;
import com.bao.box.product.model.Product;
import com.bao.box.product.model.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select({"<script>",
            " select * from product p LEFT JOIN category c on p.category_id =c.category_id ",
            " Where c.category_name = #{categoryName} ",
            "LIMIT 0, 7",
            " </script> "})
    List<Product> getHomeProductsByCategoryName(@Param("categoryName") String categoryName);

    /**
     * TODO 需要补全分页查询
     */
    @Select({"<script>",
            " select * from product p LEFT JOIN category c on p.category_id =c.category_id ",
            " Where p.category_id = #{categoryId} ",
            " </script> "})
    List<Product> getProductsByCategoryId(@Param("categoryId") Integer categoryId);

    @Select({"<script>",
            " select * from product p LEFT JOIN category c on p.category_id =c.category_id ",
            " LIMIT #{pageStart},#{pageSize}",
            " </script> "})
    List<Product> getProducts(@Param("pageSize") Integer pageSize,@Param("pageStart") Integer pageStart);

    @Select({"<script>",
            " select COUNT(p.product_id) from product p LEFT JOIN category c on p.category_id =c.category_id ",
            " </script> "})
    int getProductsTotal();

    @Select("Select * From category ")
    List<Category> getAllCategory();

    /**
     * 产品加入购物车
     * TODO 应该直接返回主键
     */
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({"<script>",
            " INSERT INTO shopping_cart ",
            " ( ",
            "  user_id , product_id, num ",
            " ) ",
            " VALUES ",
            " ( ",
            "   #{shoppingCart.no}, #{shoppingCart.productId},#{shoppingCart.num} ",
            " )",
            "</script>" })
//    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int addShoppingCart(@Param("shoppingCart") ShoppingCart shoppingCart);

    @Select("Select id From shopping_cart  Where user_id = #{userNo}  and product_id = #{productId} ")
    Integer hasShoppingCart(@Param("userNo") Integer userNo,@Param("productId") Integer productId);

    @Select("Select * From shopping_cart  Where user_id = #{userNo}  and product_id = #{productId} ")
    ShoppingCart getShoppingCart(@Param("userNo") Integer userNo,@Param("productId") Integer productId);

    /**
     * 更新购物车数量
     */
    @Update(" Update shopping_cart Set num = num + 1 Where id = #{cartId}")
    int updateCartNum(@Param("cartId") Integer cartId);

//    @Select(" SELECT COUNT(ID) FROM shopping_cart Where User_Id = #{userId}")
//    int cartCount(@Param("userId") Integer userId);

    @Select({"<script>",
            " SELECT c.id,c.num,p.product_selling_price,p.product_picture,p.product_name,p.product_id ",
            " FROM shopping_cart c ",
            " LEFT JOIN product p on p.product_id = c.product_id ",
            " Where c.user_id =  #{userId} ",
            " </script> "})
    List<ShoppingCart> listCart(@Param("userId") Integer userId);

    @Select({"<script>",
            " SELECT c.id,c.num,p.product_selling_price,p.product_picture,p.product_name,p.product_id ",
            " FROM shopping_cart c ",
            " LEFT JOIN product p on p.product_id = c.product_id ",
            " Where c.user_id = #{userId} and c.id = #{cartId}",
            " </script> "})
    ShoppingCart listOneCart(@Param("userId") Integer userId,@Param("cartId") Integer cartId);

    @Delete({
            "Delete A",
            "FROM",
            "shopping_cart A",
            "WHERE",
            "A.id = #{cartId}  and A.product_id = #{productId}"
    })
    void delShoppingCart(@Param("cartId") Integer cartId,@Param("productId") Integer productId);

    @Delete({"<script>",
            "Delete A",
            "FROM",
            "shopping_cart A",
            "WHERE",
            "A.id IN ",
            "       <foreach collection='cartIds' item='item' open='(' close=')' separator=',' >",
            "           #{item}",
            "       </foreach>",
            " </script> " })
    int delShoppingCarts(@Param("cartIds") Integer[] cartIds);

}
