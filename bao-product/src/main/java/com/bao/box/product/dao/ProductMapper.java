package com.bao.box.product.dao;

import com.bao.box.product.model.Category;
import com.bao.box.product.model.Product;
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


}
