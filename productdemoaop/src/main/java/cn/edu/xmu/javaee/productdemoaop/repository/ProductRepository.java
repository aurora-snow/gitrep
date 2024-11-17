package cn.edu.xmu.javaee.productdemoaop.repository;

import cn.edu.xmu.javaee.productdemoaop.mapper.jpa.entity.product;
import jakarta.persistence.QueryHint;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<product,Long>{

    // 根据 name 查找商品
    @Query("SELECT p FROM product p WHERE p.name = :name")
    List<product> findByName(@Param("name") String name);

    // 根据 goodsId 和 id 查找其他同类商品
    @Query("SELECT p from product p where p.goodsId=:goodsId AND p.id <> :id")
    @QueryHints(@QueryHint(name = "org.hibernate.readOnly",value = "true"))
    List<product> findOtherProducts(@Param("goodsId") Long goodsId, @Param("id") Long id);

}
