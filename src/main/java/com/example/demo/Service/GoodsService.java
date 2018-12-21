package com.example.demo.Service;

import com.example.demo.Entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public interface GoodsService {
    Goods addGoods(Goods goods);
    Goods selectById(int id);
    Goods updateById(int id, String name);
    String deleteById(int id);
}
