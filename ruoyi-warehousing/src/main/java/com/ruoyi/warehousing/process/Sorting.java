package com.ruoyi.warehousing.process;

import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Order;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.form.WorkTime;
import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 分拣流程模拟
 */
public class Sorting {

    /***
     * 分拣流程数据
     * @param emps
     * @param cargos
     * @param tally
     */
    public static EmpLog move(List<Emp> emps, List<Cargo> cargos, Tally tally){
        EmpLog empLog = new EmpLog();
        int m=0;
        int n=0;
        int d=0;
        for (Emp emp:emps) {
            d++;
            if (emp.getStatus() == 0) {
            } else if (emp.getStatus() == 1) {
                m++;
                if (emp.getTar()==null){
                    if (emp.getOrders()!=null) {
                        Point point = new Point(65, 15, 15);
                        int s = 0;
                        Goods goodsd = new Goods();
                        for (Order order : emp.getOrders()) {
                            if (order.getStatus() == 0 && s == 0) {
                                goodsd.setGoodsCode(order.getGoodsCode());
                                goodsd.setPlutNum(order.getGoodsNum());
                                order.setStatus(1);
                                s++;
                            }
                        }
                        if (goodsd.getGoodsCode() != null) {
                            for (Cargo cargo : cargos) {
                                if (cargo.getGoods() != null && cargo.getPoint() != null && cargo.getGoods().getGoodsCode() != null && goodsd != null && cargo.getGoods().getGoodsCode().equals(goodsd.getGoodsCode())) {
                                    point = cargo.getPoint();
                                }
                            }
                        } else {
                            point = new Point(65, 10, 15);
                            tally.getOrders().add(emp.getOrders().get(0));
                            emp.getOrders().remove(0);
                        }
                        emp.setTar(point);
                    }else {
                        emp.setStatus(0);
                    }
                }else if (emp.arrive()) {
                    if (emp.getOrders().size()>0){
                        Point point = new Point(65, 15, 15);
                        int s = 0;
                        Goods goodsd = new Goods();
                        for (Order order : emp.getOrders()) {
                            if (order.getStatus() == 0 && s == 0) {
                                goodsd.setGoodsCode(order.getGoodsCode());
                                goodsd.setPlutNum(order.getGoodsNum());
                                order.setStatus(1);
                                s++;
                            }
                        }
                        if (goodsd.getGoodsCode() != null) {
                            for (Cargo cargo : cargos) {
                                if (cargo.getGoods() != null && cargo.getPoint() != null && cargo.getGoods().getGoodsCode() != null && goodsd != null && cargo.getGoods().getGoodsCode().equals(goodsd.getGoodsCode())) {
                                    point = cargo.getPoint();
                                }
                            }
                        } else {
                            point = new Point(65, 10, 15);
                            tally.getOrders().add(emp.getOrders().get(0));
                            emp.getOrders().remove(0);
                        }
                        emp.setTar(point);

                    }else {
                        emp.setStatus(0);
                    }
                }else{
                    emp.setCurr(WarehousingUtil.getPath1(emp.getCurr(),emp.getTar(),WorkTime.v0));
                }
            }else if (emp.getStatus()==2){
                n++;
                if (emp.arrive()){
                    emp.setStatus(3);
                    emp.setTar(tally.getPoints().get(0));
                }else {
                    emp.setCurr(WarehousingUtil.getPath1(emp.getCurr(),emp.getTar(),WorkTime.v1));
                }
            }else if (emp.getStatus()==3){
                n++;
                if (emp.arrive()){
                    emp.setTar(tally.getPoints().get(tally.getPoints().size()-1));
                    emp.setStatus(4);
                }else {
                    emp.setCurr(WarehousingUtil.getPath1(emp.getCurr(),emp.getTar(),WorkTime.v1));
                }
            }else if (emp.getStatus()==4){
                n++;
                if (emp.arrive()){
                    emp.setStatus(5);
                    emp.setTar(tally.getPoints().get(0));
                    emp.setOrders(new ArrayList<>());
                    d++;
                }else {
                    emp.setCurr(WarehousingUtil.getPath1(emp.getCurr(),emp.getTar(),WorkTime.v1));
                }
            }else if (emp.getStatus()==5){
                n++;
                if (emp.arrive()){
                    emp.setStatus(0);
                }else {
                    emp.setCurr(WarehousingUtil.getPath1(emp.getCurr(),emp.getTar(),WorkTime.v1));
                }
            }

        }

        empLog.setComPlut(m/emps.size());
        empLog.setAllPlut(d/tally.getTorr());
        return empLog;
    }



}
