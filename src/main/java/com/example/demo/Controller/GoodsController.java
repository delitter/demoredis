package com.example.demo.Controller;

import com.example.demo.Entity.Goods;
import com.example.demo.Service.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private GoodsServiceImpl goodsServiceImpl;
    public GoodsController( GoodsServiceImpl goodsServiceImpl){
        this.goodsServiceImpl = goodsServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<Goods> add(@RequestBody Goods goods){
        redisTemplate.opsForValue().set(goods.getName(), goods.getName());
        return new ResponseEntity<>(goodsServiceImpl.addGoods(goods), HttpStatus.OK);
    }

    @GetMapping("/getName")
    public ResponseEntity<String> getName(@RequestParam String name){
        return new ResponseEntity<>(redisTemplate.opsForValue().get(name), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Goods goods){
        Goods origin = goodsServiceImpl.selectById(goods.getId());
        String name = redisTemplate.opsForValue().getAndSet(origin.getName(), goods.getName());
        goodsServiceImpl.updateById(goods.getId(), goods.getName());
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id){
        Goods origin = goodsServiceImpl.selectById(id);
        redisTemplate.delete(origin.getName());
        return new ResponseEntity<>(goodsServiceImpl.deleteById(id), HttpStatus.OK);
    }
}
