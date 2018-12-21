package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user){
        user.setCreateDate(new Date());
        redisTemplate.opsForValue().set("add", user.getName());
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @GetMapping("/queryAll")
    public ResponseEntity<List<User>> queryAll(){
        return new ResponseEntity<>(userService.selectAll(), HttpStatus.OK);
    }

    @GetMapping("/queryById")
    public ResponseEntity<User> queryById(@RequestParam int id){
        return new ResponseEntity<>(userService.selectById(id), HttpStatus.OK);
    }

    @GetMapping("/queryByNA")
    public ResponseEntity<User> queryByNA(@RequestParam String name, @RequestParam int age){
        return new ResponseEntity<>(userService.selectByNA(name, age), HttpStatus.OK);
    }

    @GetMapping("/queryPageAge")
    public ResponseEntity<Page<User>> queryPageAge(
            @RequestParam int age, @RequestParam int pageNum, @RequestParam int pageSize){
        return new ResponseEntity<>(userService.selectPageByAge(age, pageNum, pageSize), HttpStatus.OK);
    }

    @GetMapping("/queryByName")
    public ResponseEntity<List<User>> queryByName(@RequestParam String name){
        return new ResponseEntity<>(userService.findByNameLike(name), HttpStatus.OK);
    }

    @GetMapping("/queryByName2")
    public ResponseEntity<List<User>> queryByName2(@RequestParam String name){
        return new ResponseEntity<>(userService.findByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/queryByExample")
    public ResponseEntity<User> queryByTemplate(@RequestParam String name, @RequestParam int age){
        return new ResponseEntity<>(userService.findByTemplate(name, age), HttpStatus.OK);
    }

    @PostMapping("/updateById")
    public ResponseEntity<String> updateById(@RequestParam int id, @RequestParam String name){
        userService.updateById(id, name);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }
}
