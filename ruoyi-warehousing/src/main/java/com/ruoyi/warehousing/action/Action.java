package com.ruoyi.warehousing.action;

import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.process.Delivery;
import com.ruoyi.warehousing.process.Putaway;
import com.ruoyi.warehousing.process.Sorting;
import com.ruoyi.warehousing.queue.Order;
import com.ruoyi.warehousing.resource.equipment.Elevator;
import com.ruoyi.warehousing.resource.equipment.LightStorage;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;
import com.ruoyi.warehousing.result.Result;
import com.ruoyi.warehousing.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Action {

    /**
     * 根据输入的上架和分拣量，计算上架和分拣的人员利用率
     *
     * @param sort_type
     * @param transportNum2
     * @param transportNum3
     * @param storage
     * @return
     */
    public static Result action(double goods_num, String sort_type, double transportNum2, double transportNum3, LightStorage storage) {
        List<Goods> list = WarehousingUtil.createGoods(goods_num);
        Tally tally = WarehousingUtil.initTally();
        Tally tally1 = WarehousingUtil.initTally1();
        List<Order> orderList = WarehousingUtil.initOrders(list, transportNum2);
        List<Order> orderList1 = WarehousingUtil.initOrders(list, transportNum3);
        List<Elevator> elevators = WarehousingUtil.initElevator(5, 1);
        double total = 0.0;
        List<Cargo> cargos = WarehousingUtil.initCargo(orderList, total);
        List<Emp> emps1 = WarehousingUtil.initEmp(storage.getPutawayemp());
        List<Emp> emps2 = WarehousingUtil.initEmp(storage.getSortingemp());
        List<Emp> emps3 = WarehousingUtil.initEmp(storage.getSortingemp());
        emps1 = WarehousingUtil.initEmpOrder(emps1, orderList);
        emps2 = WarehousingUtil.initEmpSortingOrder(emps2, orderList1, sort_type);

        double work1 = 0.0;
        double work2 = 0.0;
        Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
        Date endTime = DateUtils.convertString2Date("HH:mm:ss", "16:00:00");//当前时间
        while (runTime.getTime()<endTime.getTime()) {
            EmpLog empLog2 = Putaway.work1(emps1, tally, tally1, elevators, cargos);
            EmpLog empLog3 = Sorting.move1(emps2, cargos, tally1);
//            EmpLog empLog4 = Delivery.move(emps3);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(runTime);
            calendar.add(Calendar.SECOND, 1);
            runTime = calendar.getTime();
            work1+=empLog2.getComPlut();
            work2+=empLog3.getComPlut();

        }
        Result result = new Result();
        result.setPutawayRate(work1/60/60);
        result.setSortingRate(work2/60/60);
        return result;
    }
}
