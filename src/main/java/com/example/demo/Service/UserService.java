package com.example.demo.Service;

import com.example.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void deleteUser(int id);
    User updateUser(User user);
    List<User> selectAll();
    User selectById(int id);
    User selectByNA(String name, int age);
    Page<User> selectPageByAge(int age, int pageNum, int pageSize);
    List<User> findByNameLike(String name);//支持模糊查询
    List<User> findByNameContaining(String name);
    User findByTemplate(String name, int age);
    void updateById(int id, String name);
}
