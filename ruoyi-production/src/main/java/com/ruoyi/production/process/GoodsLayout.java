package com.ruoyi.production.process;

import com.ruoyi.production.form.Cargo;
import com.ruoyi.production.form.Goods;
import com.ruoyi.production.queue.Point;
import com.ruoyi.production.resource.facilities.storage.Storage;
import com.ruoyi.production.utils.AreaUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GoodsLayout {
    /**
     * 生成存储区坐标系
     * @param storage
     * @param goods
     * @return
     */
    public static List<Cargo> initGoodsLayout(Storage storage, List<com.ruoyi.production.form.Goods> goods){
        List<com.ruoyi.production.form.Cargo> cargos1 = initCargos(goods,storage);
        List<com.ruoyi.production.form.Cargo> cargos = new ArrayList<>();
        for (int i = 1; i <=storage.getLayer(); i++) {
            for (int j = 1; j <= storage.getLine(); j++) {
                for (int k = 1; k <= storage.getRow(); k++) {
                    Point point = new Point(i*(AreaUtils.platform_width+AreaUtils.shelf_space)+AreaUtils.platform_width/2, j*(AreaUtils.platform_width+AreaUtils.shelf_space), k);
                    cargos.add(new com.ruoyi.production.form.Cargo(point, getGoods(i,j,k,cargos1)));
                }
            }
        }
        return cargos;
    }

    /**
     * 按照物料频次进行优先
     * @param list
     * @param storage
     * @return
     */
    public static List<com.ruoyi.production.form.Cargo> initCargos(List<com.ruoyi.production.form.Goods> list, Storage storage){
        LinkedList<com.ruoyi.production.form.Goods> materials = new LinkedList<>(list);
        materials.sort((x, y) -> Double.compare(y.getFrequency(), x.getFrequency()));
        List<com.ruoyi.production.form.Cargo> cargos = new ArrayList<>();
        for (int i = 1; i <= storage.getLayer(); i++) {
            for (int j = 1; j <= storage.getLine(); j++) {
                for (int k = 1; k <= storage.getRow(); k++) {
                    com.ruoyi.production.form.Goods goods1 = materials.poll();
                    Point point = new Point(i, j, k);
                    cargos.add(new com.ruoyi.production.form.Cargo(point, goods1));
                }
            }
        }
        return cargos;
    }
    /**
     * 按照物料量进行优先
     * @param list
     * @param storage
     * @return
     */
    public static List<com.ruoyi.production.form.Cargo> initCargosByNum(List<com.ruoyi.production.form.Goods> list, Storage storage){
        LinkedList<com.ruoyi.production.form.Goods> materials = new LinkedList<>(list);
        materials.sort((x, y) -> Double.compare(y.getPlutNum(), x.getPlutNum()));
        List<com.ruoyi.production.form.Cargo> cargos = new ArrayList<>();
        for (int i = 1; i <= storage.getLayer(); i++) {
            for (int j = 1; j <= storage.getLine(); j++) {
                for (int k = 1; k <= storage.getRow(); k++) {
                    com.ruoyi.production.form.Goods goods1 = materials.poll();
                    Point point = new Point(i, j, k);
                    cargos.add(new com.ruoyi.production.form.Cargo(point, goods1));
                }
            }
        }
        return cargos;
    }




    /**
     * 获取物料坐标
     * @param i
     * @param j
     * @param k
     * @param cargos
     * @return
     */
    public static com.ruoyi.production.form.Goods getGoods(int i, int j, int k, List<com.ruoyi.production.form.Cargo> cargos){
        com.ruoyi.production.form.Goods goods= new Goods();
        for (Cargo cargo :cargos){
            if (i==cargo.getPoint().getX()&&j==cargo.getPoint().getY()&&k==cargo.getPoint().getZ()){
                goods = cargo.getGoods();
            }
        }
        return goods;
    }

}
