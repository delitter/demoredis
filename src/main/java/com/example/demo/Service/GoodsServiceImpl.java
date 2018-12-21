package com.example.demo.Service;

import com.example.demo.Entity.Goods;
import com.example.demo.Repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    private GoodsRepository goodsRepository;
    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository){
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Goods addGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Goods selectById(int id) {

        return goodsRepository.findById(id).get();
    }

    @Override
    public Goods updateById(int id, String name) {
        goodsRepository.updateById(id, name);
        return goodsRepository.findById(id).get();
    }

    @Override
    public String deleteById(int id) {
        goodsRepository.deleteById(id);
        return "success!";
    }
}
