package com.example.demo.Repository;

import com.example.demo.Entity.Goods;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    @Query(value = "insert into goods(name,price,amount) values(?1,?2,?3)", nativeQuery = true)
    Optional<Goods> addGoods(
            @Param("name")String name, @Param("price")double price, @Param("amount")int amount);

    @Query("select g from Goods g where id = ?1")
    Optional<Goods> findById(@Param("id")int id);


    @Modifying(clearAutomatically = true)
    @Query(value = "update goods set name=:name where id=:id", nativeQuery = true)
    void updateById(@Param("id")int id, @Param("name")String name);

    @Query(value = "delete from goods where id=:id", nativeQuery = true)
    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteById(@Param("id")int id);
}
