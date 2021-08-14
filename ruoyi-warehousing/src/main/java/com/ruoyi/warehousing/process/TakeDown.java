package com.ruoyi.warehousing.process;

import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Tray;
import com.ruoyi.warehousing.resource.facilities.storage.Storage;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;
import com.ruoyi.warehousing.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TakeDown {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    /**
     * x下架流程---
     *
     * @param emps    下架人员
     * @param storage 货位
     */
    public static List<EmpLog> work(Storage storage, List<Tray> trays, double putaway_speed, List<Emp> emps) {
        Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
        Date endTime = DateUtils.convertString2Date("HH:mm:ss", "18:00:00");//当前时间
        List<EmpLog> list = new ArrayList<>();
        while (runTime.getTime() < endTime.getTime()) {

            if (isEmpNull(emps) && TrayisNotNull(trays)) {
                EmpToStorage(emps, trays, storage);
            }

            for (Emp emp : emps) {
                EmpLog empLog = new EmpLog();
                empLog.setEmpNo(emp.getCode());
                empLog.setTime(sdf.format(runTime));
                switch (emp.getStatus()) {
                    case 0:
                        empLog.setEmpStatus(0);
                        break;
                    case 1:
                        empLog.setEmpStatus(1);
                        if (WarehousingUtil.getDistance(emp.getCurr(), emp.getTar()) < 1) {
                            emp.addStatus();
                            emp.setT2(40);
                        } else {
                            empLog.setDistance(putaway_speed);
                            emp.setCurr(WarehousingUtil.getPath(emp, putaway_speed));
                        }
                        break;
                    case 2:
                        empLog.setEmpStatus(1);
                        if (emp.getT2() > 0) {
                            emp.dispose();
                        } else {
                            emp.setStatus(3);
                            emp.setTar(new Point(0, 0, 0));
                        }
                        break;
                    case 3:
                        empLog.setEmpStatus(1);
                        if (WarehousingUtil.getDistance(emp.getCurr(), emp.getTar()) < 1) {
                            emp.setStatus(0);
                        } else {
                            empLog.setDistance(putaway_speed);
                            emp.setCurr(WarehousingUtil.getPath(emp, putaway_speed));
                        }
                        break;
                }
                list.add(empLog);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(runTime);
            calendar.add(Calendar.SECOND, 1);
            runTime = calendar.getTime();

        }


        return list;

    }

    private static void EmpToStorage(List<Emp> emps, List<Tray> trays, Storage storage) {
        for (Emp emp : emps) {
            if (emp.getStatus() == 0) {
                emp.setStatus(1);
                Tray tray = trays.get(0);
                emp.setTary(tray);
                trays.remove(0);
                emp.setTar(storage.getEmpTar(emp));
            }
        }
    }


    /**
     * 是否已经上架完成
     *
     * @param trays
     * @return
     */
    private static boolean TrayisNotNull(List<Tray> trays) {
        boolean isnull = false;
        if (trays != null && trays.size() > 0) {
            isnull = true;
        }
        return isnull;
    }

    private static boolean isEmpNull(List<Emp> emps) {
        boolean isnull = false;
        for (Emp emp : emps) {
            if (emp.getStatus() == 0) {
                isnull = true;
            }
        }

        return isnull;
    }
}