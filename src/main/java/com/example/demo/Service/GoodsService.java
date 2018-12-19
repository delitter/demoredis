package com.example.demo.Service;

import com.example.demo.Entity.Goods;

import java.util.List;

public interface GoodsService {
    Goods addGoods(Goods goods);
    List<Goods> selectById(int id);
    Goods updateById(int id, String name);
    String deleteById(int id);
}
