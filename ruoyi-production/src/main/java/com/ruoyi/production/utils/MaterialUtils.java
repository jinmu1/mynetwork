package com.ruoyi.production.utils;

import com.ruoyi.production.form.Goods;

import java.util.ArrayList;
import java.util.List;

public final class MaterialUtils {

    /**
     * 物料生成类
     */
    public List<Goods> initGoodsList(int goodsNum){
        List<Goods> goodsList = new ArrayList<>();
        for(int i =0;i<goodsNum;i++){
          goodsList.add(new Goods( RandomUtil.generateString(16)));
        }
        return goodsList;

    }

    /**
     * 生成物料属性
     * @return
     */
    public List<Goods> initVolume(List<Goods> goodsList,int size){





        return goodsList;
    }


    /**
     * 生成物料重量
     */
    public List<Goods> initWight(List<Goods> goodsList){


        return goodsList;
    }

    /**
     * 生成物料
     */
}
