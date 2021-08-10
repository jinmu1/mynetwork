package com.ruoyi.warehousing.resource.facilities.storage;


import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.LightStorage;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.utils.AreaUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Storage {
     private String type;
     private double area;
     private double price;//价格
     private  List<Cargo> cargos;
     private List<Point> points;
    public Point getEmpTar(Emp emp) {
        Point point =new Point();
        for (Cargo cargo: cargos){
            if (emp.getTary().getGoodsList().get(0).equals(cargo.getGoods())){
                point =  cargo.getPoint();
            }
        }
        return point;
    }
     public void initStorage(LightStorage lightStorage, List<Cargo> cargos1){
         LinkedList<Goods> materials = new LinkedList<>();
         for (int i = 1; i <=lightStorage.getLayer(); i++) {
             for (int j = 1; j <= lightStorage.getLine(); j++) {
                 for (int k = 1; k <= lightStorage.getRow(); k++) {
                     Goods goods1 = materials.poll();
                     Point point = new Point(i*(AreaUtils.platform_width+AreaUtils.shelf_space)+AreaUtils.platform_width/2, j*(AreaUtils.platform_width+AreaUtils.shelf_space), k);
                     cargos.add(new Cargo(point, getGoods(i,j,k,cargos1)));
                 }
             }
         }

     }

     public  Goods  getGoods(int i,int j,int k,List<Cargo> cargos){
         Goods goods= new Goods();
         for (Cargo cargo :cargos){
             if (i==cargo.getPoint().getX()&&j==cargo.getPoint().getY()&&k==cargo.getPoint().getZ()){
                 goods = cargo.getGoods();
             }
         }
         return goods;
     }
     public String getType() {
        return type;
    }

     public void setType(String type) {
        this.type = type;
    }

     public double getArea() {
        return area;
    }

     public void setArea(double area) {
        this.area = area;
    }

     public double getPrice() {
        return price;
    }

     public void setPrice(double price) {
        this.price = price;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }


}
