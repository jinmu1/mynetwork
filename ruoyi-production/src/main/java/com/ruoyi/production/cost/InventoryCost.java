package com.ruoyi.production.cost;

import com.ruoyi.production.form.Goods;

import java.util.List;

/**
 * 库存持有成本
 */
public class InventoryCost {
    private List<Goods> midbingoods;//中间仓库存
    private List<Goods> sideGoods;//线边仓库存


    public List<Goods> getMidbingoods() {
        return midbingoods;
    }

    public void setMidbingoods(List<Goods> midbingoods) {
        this.midbingoods = midbingoods;
    }

    public List<Goods> getSideGoods() {
        return sideGoods;
    }

    public void setSideGoods(List<Goods> sideGoods) {
        this.sideGoods = sideGoods;
    }
}
