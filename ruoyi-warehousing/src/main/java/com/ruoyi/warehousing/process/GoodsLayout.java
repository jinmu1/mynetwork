package com.ruoyi.warehousing.process;

import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.facilities.storage.Storage;
import com.ruoyi.warehousing.utils.AreaUtils;

import java.util.ArrayList;
import java.util.List;

public class GoodsLayout {

    public static List<Cargo> initGoodsLayout(Storage storage, List<Cargo> cargos1){
        List<Cargo> cargos = new ArrayList<>();
        for (int i = 1; i <=storage.getLayer(); i++) {
            for (int j = 1; j <= storage.getLine(); j++) {
                for (int k = 1; k <= storage.getRow(); k++) {
                    Point point = new Point(i*(AreaUtils.platform_width+AreaUtils.shelf_space)+AreaUtils.platform_width/2, j*(AreaUtils.platform_width+AreaUtils.shelf_space), k);
                    cargos.add(new Cargo(point, getGoods(i,j,k,cargos1)));
                }
            }
        }
        return cargos;
    }
    public static Goods getGoods(int i, int j, int k, List<Cargo> cargos){
        Goods goods= new Goods();
        for (Cargo cargo :cargos){
            if (i==cargo.getPoint().getX()&&j==cargo.getPoint().getY()&&k==cargo.getPoint().getZ()){
                goods = cargo.getGoods();
            }
        }
        return goods;
    }

}
