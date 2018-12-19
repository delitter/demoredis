package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user where name like CONCAT('%',?1,'%')", nativeQuery = true)
    List<User> findByNameLike(@Param("name") String name);//支持模糊查询

    @Query("select u from User u where u.age = :age and u.name = :name")
    Optional<User> selectByNA(@Param("age") int age, @Param("name") String name);

    @Query(value = "select * from user where name < ?1",
            countQuery = "select count(*) from user where name < ?1",
            nativeQuery = true)
    Page<User> selectPageByAge(@Param("age") int age, Pageable pageable);

    List<User> findAllByNameContaining(String name);

    Optional<User> updateById(@Param("id") int id, @Param("name") String name);
}
