package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static sun.misc.MessageUtils.where;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> selectAll() {
        return  userRepository.findAll();
    }

    @Override
    public User selectById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameLike(name);
    }

    @Override
    public User selectByNA(String name, int age) {
        return userRepository.selectByNA(age, name).orElse(null);
    }

    @Override
    public Page<User> selectPageByAge(int age, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return userRepository.selectPageByAge(age, pageable);
    }

    @Override
    public List<User> findByNameContaining(String name){
        return userRepository.findAllByNameContaining(name);
    }

    @Override
    public User findByTemplate(String name, int age){
        User u = new User();
        u.setAge(age);
        u.setName(name);
        Example<User> example = Example.of(u);
        return userRepository.findOne(example).get();
    }

    @Override
    public void updateById(int id, String name){
        userRepository.updateById(id, name);
    }



}
