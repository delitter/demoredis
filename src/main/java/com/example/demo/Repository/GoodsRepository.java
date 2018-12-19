package com.example.demo.Repository;

import com.example.demo.Entity.Goods;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    @Query(value = "insert into goods(name,price,amount) values(?1,?2,?3)", nativeQuery = true)
    Optional<Goods> addGoods(
            @Param("name")String name, @Param("price")double price, @Param("amount")int amount);

    @Query("select g from Goods g where id = ?1")
    @Cacheable(key = "#p0")
    Optional<Goods> findById(@Param("id")int id);

    @Query("update Goods g set g.name=?2 where g.id=?1")
    @CachePut(key = "#p0")
    Optional<Goods> updateById(@Param("id")int id, @Param("name")String name);

    @Query("delete g from Goods g where g.id=?1")
    @CacheEvict(key = "#p0")//如果指定为true，则方法调用后将立即清空所有缓存
    void deleteById(@Param("id")int id);
}
