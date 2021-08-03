package com.ruoyi.warehousing.process;


import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.form.WorkTime;
import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * 卸货流程模拟
 */
public class Upload{


    /**
     * 卸货流程
     * @param emps
     * @param platforms
     * @param doors
     * @param tally
     */
    public static EmpLog move(List<Emp> emps, List<Platform> platforms, List<Point> doors, Tally tally) {

        EmpLog empLog = new EmpLog();
        int m = 0;
        for (Emp emp : emps) {
            if (emp.getStatus() != 0) {
                m++;
            }
            if (emp.getStatus() == 0) {

            } else if (emp.getStatus() == 1) {
                if (emp.arrive()) {//到达卸货区
                    emp.setStatus(2);
                    emp.setT0(WorkTime.t0);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp,WorkTime.v0));
                }
            } else if (emp.getStatus() == 2) {//处理卸货
                if (emp.getT0() > 0) {
                    emp.fix();
                } else {
                    emp.setStatus(3);
                    WarehousingUtil.removeCar(emp.getCode(),platforms);
                    emp.setCode(null);
                    emp.setTar(WarehousingUtil.getDoor(emp.getCurr(),doors));
                }
            } else if (emp.getStatus() == 3) {//去往卸货门洞
                if (emp.arrive()) {
                    emp.setStatus(4);
                    emp.setTar(WarehousingUtil.getTally(tally));
                    WarehousingUtil.emptys(tally, emp.getCurr(),1);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp,WorkTime.v1));
                }
            } else if (emp.getStatus() == 4) {//去往理货区
                if (emp.arrive()) {
                    emp.setStatus(5);
                    emp.setT1(WorkTime.t1);
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp,WorkTime.v1));
                }
            } else if (emp.getStatus() == 5) {//将货物卸在理货区
                if (emp.getT1() > 0) {
                    emp.fix1();
                } else {
                    emp.setStatus(0);
                    tally.add();
                    tally.add(emp.getGoods());
                    emp.setGoods(new ArrayList<>());
                    WarehousingUtil.emptys(tally,emp.getTar(), 2);
                }
            } else if (emp.getStatus() == 7) {//卸货完毕，返回门洞，并重新分配任务
                if (emp.arrive()) {
                    emp.setStatus(1);
                    emp.setTar(WarehousingUtil.getPlatform(emp,platforms));
                } else {
                    emp.setCurr(WarehousingUtil.getPath(emp,WorkTime.v0));
                }
            }
        }
        int l = 0;
        for (Platform platform : platforms) {
            if (platform.getStatus() != 0) {
                l++;
            }
        }
        empLog.setComPlut(m);
        empLog.setAllPlut(l);
        return empLog;

    }


}
