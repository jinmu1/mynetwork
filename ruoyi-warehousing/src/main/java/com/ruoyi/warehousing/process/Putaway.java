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
     * @param elevatorPark 电梯区域
     * @param elevators 电梯
     * @param cargos  货位
     */
    public static EmpLog work(List<Emp> emps, Tally tally, Tally tally1, List<Point> elevatorPark, List<Elevator> elevators, List<Cargo> cargos) {
        EmpLog empLog = new EmpLog();
        int m = 0;

        int empNums = 0;
        for (Emp emp : emps) {
            if (emp.getStatus() != 0) {
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
                    emp.setTar(WarehousingUtil.getPark(emp, elevatorPark));
                }
            } else if (emp.getStatus() == 3) { //到达电梯区域，并等待电梯
                if (emp.arrive()) {
                    emp.setStatus(4);
                    Elevator elevator = WarehousingUtil.waitEle(emp.getTar().getZ(), elevators);
                    WarehousingUtil.freeEle(elevator, 1, elevators);
                    Point point = elevator.getCurr();
                    point.setZ(emp.getCurr().getZ());
                    elevator.setTar(point);
                    emp.setElevator(elevator);
                } else {
                    emp.setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.v1));
                }
            } else if (emp.getStatus() == 4) {//等待电梯下降中
                if (emp.eleArrive()) {
                    emp.setStatus(5);
                    emp.getElevator().getTar().setZ(10);
                    emp.setE_t0(WorkTime.e_T0);
                } else {
                    emp.getElevator().setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.e_v0));
                    emp.getCurr().setZ(emp.getElevator().getCurr().getZ());
                }
            } else if (emp.getStatus() == 5) {//将托盘推入电梯
                if (emp.getE_t0() == 0) {
                    emp.setStatus(6);
                } else {
                    emp.setE_t0(emp.getE_t0() - 1);
                }
            } else if (emp.getStatus() == 6) {//电梯上升
                if (emp.eleArrive()) {
                    emp.setStatus(7);
                    emp.setE_t0(WorkTime.e_T0);
                    WarehousingUtil.freeEle(emp.getElevator(), 0, elevators);
                    emp.getCurr().setZ(10);
                    emp.setTar(WarehousingUtil.getTally1(tally1));
                    WarehousingUtil.emptys1(emp.getTar(), 1, tally1);
                } else {
                    emp.getElevator().setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.e_v0));
                    emp.getCurr().setZ(emp.getElevator().getCurr().getZ());
                }
            } else if (emp.getStatus() == 7) {//到达暂存区
                if (emp.eleArrive()) {
                    emp.setStatus(8);
                    emp.setT1(WorkTime.t1);
                    WarehousingUtil.emptys1(emp.getTar(), 2, tally1);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp, WorkTime.v0));
                }
            } else if (emp.getStatus() == 8) {//卸货
                if (emp.getT1() > 0) {
                    emp.fix1();
                } else {
                    emp.setStatus(9);
                    tally1.add();
                    tally1.add(emp.getGoods());
                    emp.setGoods(new ArrayList<>());
                    emp.setTar(WarehousingUtil.getPark(emp, elevatorPark));
                    Elevator elevator = WarehousingUtil.waitEle(emp.getTar().getZ(), elevators);
                    WarehousingUtil.freeEle(elevator, 1, elevators);
                    Point point = elevator.getCurr();
                    point.setZ(emp.getCurr().getZ());
                    elevator.setTar(point);
                    emp.setElevator(elevator);
                }
            } else if (emp.getStatus() == 9) {//推入电梯
                if (emp.arrive()) {
                    emp.setStatus(10);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp, WorkTime.e_v1));
                }
            } else if (emp.getStatus() == 10) {//电梯下降
                if (emp.eleArrive()) {
                    emp.setStatus(11);
                    emp.getElevator().getTar().setZ(0);
                    emp.setE_t0(WorkTime.e_T0);
                    WarehousingUtil.freeEle(emp.getElevator(), 0, elevators);
                    emp.getCurr().setZ(0);
                } else {
                    emp.getElevator().setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.e_v1));
                    emp.getCurr().setZ(emp.getElevator().getCurr().getZ());
                }

            } else if (emp.getStatus() == 11) {
                if (emp.arrive()) {
                    emp.setStatus(2);
                    emp.setT0(440);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp,WorkTime.v1));
                }
            } else if (emp.getStatus() == 2) {//准备上架
                if (emp.getT0() > 1) {
                    emp.setT0(emp.getT0() - 1);
                } else {
                    emp.setStatus(3);
                    emp.setTar(WarehousingUtil.getPark(emp,elevatorPark));
                    tally1.deal();
                }
            } else if (emp.getStatus() == 3) { //准备送货
                if (emp.getGoods().size() == 0) {
                    emp.setStatus(0);
                } else {
                    emp.setStatus(7);
                }
            } else if (emp.getStatus() == 4) {//将托盘推入电梯
                if (emp.getE_t0() == 0) {
                    emp.setStatus(5);
                } else {
                    Elevator elevator = WarehousingUtil.waitEle(emp.getTar().getZ(),elevators);
                    if (emp.getElevator() == null && elevator != null) {
                        WarehousingUtil.freeEle(elevator, 1,elevators);
                        Point point = elevator.getCurr();
                        point.setZ(10);
                        elevator.setTar(point);
                        emp.setElevator(elevator);
                    }
                    if (emp.getElevator() != null) {
                        emp.setE_t0(emp.getE_t0() - 1);
                    }
                }
            } else if (emp.getStatus() == 5) {//等待电梯
                if (emp.eleArrive()) {
                    emp.setStatus(6);
                    emp.getElevator().getTar().setZ(15);
                    emp.setE_t0(WorkTime.e_T0);
                } else {
                    emp.getElevator().setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.e_v0));
                    emp.getCurr().setZ(emp.getElevator().getCurr().getZ());
                }
            } else if (emp.getStatus() == 6) {//到达楼层
                if (emp.eleArrive()) {
                    emp.setStatus(7);
                    emp.setE_t0(WorkTime.e_T0);
                    WarehousingUtil.freeEle(emp.getElevator(), 0,elevators);
                    emp.getCurr().setZ(10);
                    emp.setTar(WarehousingUtil.getGoodsPosition(emp,cargos));
                } else {
                    emp.getElevator().setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.e_v0));
                    emp.getCurr().setZ(emp.getElevator().getCurr().getZ());
                }
            } else if (emp.getStatus() == 7) { //去送货
                if (emp.getGoods() == null || emp.getGoods().size() == 0) {
                    emp.setStatus(8);
                }
                if (emp.arrive()) {
                    if (emp.getGoods() == null || emp.getGoods().size() == 0) {
                        emp.setStatus(8);
                    } else {
                        emp.setTar(WarehousingUtil.getGoodsPosition(emp,cargos));
                    }
                } else {
                    emp.setCurr(WarehousingUtil.getPath1(emp.getCurr(), emp.getTar(), WorkTime.v0));
                }
            } else if (emp.getStatus() == 8) {
                if (emp.getCurr().getZ() < 15 && emp.getCurr().getZ() > 5) {
                    emp.setStatus(0);
                } else {
                    emp.setTar(WarehousingUtil.getPark(emp,elevatorPark));
                    emp.setStatus(9);
                }
            } else if (emp.getStatus() == 9) {
                if (emp.arrive()) {
                    emp.setStatus(10);
                    Elevator elevator = WarehousingUtil.waitEle(emp.getTar().getZ(),elevators);
                    WarehousingUtil.freeEle(elevator, 1,elevators);
                    Point point = elevator.getCurr();
                    point.setZ(emp.getCurr().getZ());
                    elevator.setTar(point);
                    emp.setElevator(elevator);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp,WorkTime.e_v0));
                }
            } else if (emp.getStatus() == 10) {

                if (emp.getE_t0() == 0) {
                    emp.setStatus(11);
                } else {
                    emp.setE_t0(emp.getE_t0() - 1);
                }
            } else if (emp.getStatus() == 11) {
                if (emp.eleArrive()) {
                    emp.setE_t0(WorkTime.e_T0);
                    emp.setCurr(emp.getElevator().getCurr());
                    WarehousingUtil.freeEle(emp.getElevator(), 0,elevators);
                } else {
                    emp.getElevator().setCurr(WarehousingUtil.getPath1(emp.getElevator().getCurr(), emp.getElevator().getTar(), WorkTime.e_v0));
                    emp.getCurr().setZ(emp.getElevator().getCurr().getZ());
                }
            }
        }
        empLog.setComPlut(empNums/emps.size());
        empLog.setAllPlut(m/tally.getTorr());
        return empLog;
    }



}
