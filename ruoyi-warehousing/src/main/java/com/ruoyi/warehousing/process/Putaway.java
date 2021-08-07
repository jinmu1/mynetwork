package com.ruoyi.warehousing.process;


import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.resource.equipment.Elevator;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.form.WorkTime;
import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;

import java.util.ArrayList;
import java.util.List;

public class Putaway {
    /**
     * 上架流程---
     * @param emps 上架人员
     * @param tally 卸货理货区
     * @param tally1   高层理货区
     * @param elevators 电梯
     * @param cargos  货位
     */
    public static EmpLog work(List<Emp> emps, Tally tally, Tally tally1, List<Elevator> elevators, List<Cargo> cargos) {
        EmpLog empLog = new EmpLog();
        int m = 0;
         
        int empNums = 0;
        for (Emp emp : emps) {
            if (emp.getStatus() == 0 &&emp.getOrders().size()>0) {
//                emp.setStatus(1);
//                emp.setCurr(new Point(0,0,0));
//                emp.setTar(WarehousingUtil.getTally(tally));
                empNums++;
             
            }
            if (emp.getStatus() == 0) {


            } else if (emp.getStatus() == 1) { //达到理货区位置
                if (WarehousingUtil.getDistance(emp.getCurr(), emp.getTar()) < 1) {
                    emp.setStatus(2);
                    emp.setT2(600);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp, WorkTime.v1));
                }
            } else if (emp.getStatus() == 2) { //整理货物到托盘上
                if (emp.getT2() > 0) {
                    emp.dispose();
                } else {
                    WarehousingUtil.emptys(tally, emp.getTar(), 0);
                    emp.setStatus(3);
                    emp.setGoods(WarehousingUtil.getGoodsPullt(tally));
                    tally.deal();
                    emp.setTar(WarehousingUtil.getTally(tally));
                }
            } else if (emp.getStatus() == 3) {//到达暂存区
                if (emp.eleArrive()) {
                    emp.setStatus(4);
                    emp.setT1(WorkTime.t1);
                    WarehousingUtil.emptys1(emp.getTar(), 2, tally1);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp, WorkTime.v0));
                }
            } else if (emp.getStatus() == 4) {//卸货
                if (emp.getT1() > 0) {
                    emp.fix1();
                } else {
                    emp.setStatus(5);
                    tally1.add();
                    tally1.add(emp.getGoods());
                    emp.setGoods(new ArrayList<>());
                    emp.setTar(WarehousingUtil.getTally(tally));
                }
            } else if (emp.getStatus() == 5) {//推入电梯
                if (emp.arrive()) {
                    emp.setStatus(6);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp, WorkTime.e_v1));
                }
            } else if (emp.getStatus() == 6) {//电梯下降
                if (emp.eleArrive()) {
                    emp.setStatus(7);
                    emp.getElevator().getTar().setZ(0);
                    emp.setE_t0(WorkTime.e_T0);
                    WarehousingUtil.freeEle(emp.getElevator(), 0, elevators);
                    emp.getCurr().setZ(0);
                } else {
                    emp.getElevator().setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.e_v1));
                    emp.getCurr().setZ(emp.getElevator().getCurr().getZ());
                }

            } else if (emp.getStatus() == 7) {
                if (emp.arrive()) {
                    emp.setStatus(2);
                    emp.setT0(440);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp,WorkTime.v1));
                }
            }
        }
        empLog.setComPlut(empNums/emps.size());
        empLog.setAllPlut(m/tally.getTorr());
        return empLog;
    }



}
