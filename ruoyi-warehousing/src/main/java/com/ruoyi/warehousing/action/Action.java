package com.ruoyi.warehousing.action;

import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Customer;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.form.Supplier;
import com.ruoyi.warehousing.process.Delivery;
import com.ruoyi.warehousing.process.Putaway;
import com.ruoyi.warehousing.process.Sorting;
import com.ruoyi.warehousing.process.Upload;
import com.ruoyi.warehousing.queue.Order;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Elevator;
import com.ruoyi.warehousing.resource.equipment.LightStorage;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;
import com.ruoyi.warehousing.result.Result;
import com.ruoyi.warehousing.utils.AreaUtils;
import com.ruoyi.warehousing.utils.DateUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Action {
    private static Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
    private static Date startTime = new Date();//开始时间
    private static Date endTime = new Date();//工作结束时间
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
        emps1 = WarehousingUtil.initEmpOrder(emps1, orderList);
        emps2 = WarehousingUtil.initEmpSortingOrder(emps2, orderList1, sort_type);

        double work1 = 0.0;
        double work2 = 0.0;
        Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
        Date endTime = DateUtils.convertString2Date("HH:mm:ss", "16:00:00");//当前时间
        while (runTime.getTime()<endTime.getTime()) {
            EmpLog empLog2 = Putaway.work1(emps1, tally, tally1, elevators, cargos);
            EmpLog empLog3 = Sorting.move1(emps2, cargos, tally1);
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

    public static List<Result> runAction(double transportNum,int customerNum,String carLength,int goods_num,double supplier,double b_goods_size,double m_goods_size,double s_goods_size) {
        List<Result> listr = new ArrayList<>();
        List<Goods> list = WarehousingUtil.createGoods(goods_num);
        List<Supplier> suppliers = WarehousingUtil.initSupplier(supplier);
        list = WarehousingUtil.initMaterialVolume(list, b_goods_size, m_goods_size, s_goods_size);
        list = WarehousingUtil.initMaterialSupplier(suppliers, list);
        List<Order> orderList = WarehousingUtil.initOrders(list, transportNum);
        List<Customer> customerList = WarehousingUtil.initCustomer(customerNum);

        orderList = WarehousingUtil.initOrdersCustomerList(orderList, customerList);
        WarehousingUtil.initTime(runTime, startTime, endTime);//初始化时间
        List<Emp> list1 = WarehousingUtil.initEmp(100);//卸货人员
        List<Emp> list2 = WarehousingUtil.initEmp(100);//上架人员、
        List<Emp> list3 = WarehousingUtil.initEmp(100);//分拣人员
        List<Emp> list4 = WarehousingUtil.initEmp(100);//装车人员

        List<Platform> platforms = WarehousingUtil.initPlat(AreaUtils.getPlatform(transportNum/365,carLength).getPlatform_num());
        List<Point> doors = WarehousingUtil.initDoor();
        List<Point> elevatorPark = WarehousingUtil.initElevatorPark();
        List<Order> inOrder = WarehousingUtil.getReplenishment(orderList, list);
        List<Elevator> elevators = WarehousingUtil.initElevator(5, 1);
        double total = 0.0;
        List<Cargo> cargos = WarehousingUtil.initCargo(orderList,total);
        Tally tally = WarehousingUtil.initTally();
        Tally tally1 = WarehousingUtil.initTally1();

        for (int i = 1; i < 10; i++) {
            Result result = new Result(i);
            result.setStorageArea(AreaUtils.getHightStorage(total,transportNum/365).getArea());
            result.setPlatformArea(AreaUtils.getPlatform(transportNum/365,carLength).getPlatform_area());
            result.setPlatformNum(AreaUtils.getPlatform(transportNum/365,carLength).getPlatform_num());
            result.setTallyArea(AreaUtils.getTally(transportNum/365,carLength).getArea());
            result.setTally1Area(AreaUtils.getTally(transportNum/365,carLength).getArea());
            List<String> batch = DateUtils.getIntervalTimeList("8:00:00", "18:00:00", 600 / i);
            int j = 0;
            List<com.ruoyi.warehousing.queue.Order> outOrderList = new ArrayList<>();
            List<com.ruoyi.warehousing.queue.Order> inOrderList = new ArrayList<>();
            for (Order order1 : orderList) {
                if (order1.getCompleteDate().getTime() > DateUtils.convertString2Date("HH:mm:ss", batch.get(j)).getTime()
                        && order1.getCreateDate().getTime() < DateUtils.convertString2Date("HH:mm:ss", batch.get(j + 1)).getTime()) {
                    outOrderList.add(new com.ruoyi.warehousing.queue.Order(order1.getOrderCode(), order1.getGoodsCode(), order1.getCreateDate(), order1.getCompleteDate(), order1.getGoodsNum(), order1.getVolume()));
                }
            }
            for (Order order1 : inOrder) {
                if (order1.getCreateDate().getTime() > DateUtils.convertString2Date("HH:mm:ss", batch.get(j)).getTime()
                        && order1.getCreateDate().getTime() < DateUtils.convertString2Date("HH:mm:ss", batch.get(j + 1)).getTime()) {
                    inOrderList.add(new com.ruoyi.warehousing.queue.Order(order1.getOrderCode(), order1.getGoodsCode(), order1.getCreateDate(), order1.getCreateDate(), order1.getGoodsNum(), order1.getVolume()));
                }
            }
            Map<Date, List<Order>> pm = outOrderList.stream().collect(groupingBy(com.ruoyi.warehousing.queue.Order::getCreateDate));
            List<EmpLog> empLogs1 = new ArrayList<>();
            List<EmpLog> empLogs2 = new ArrayList<>();
            List<EmpLog> empLogs3 = new ArrayList<>();
            List<EmpLog> empLogs4 = new ArrayList<>();

            while (runTime.getTime() < DateUtils.convertString2Date("HH:mm:ss", batch.get(j + 1)).getTime()) {
                List<com.ruoyi.warehousing.queue.Order> orders = pm.get(runTime);
                if (orders!=null&&orders.size() > 0) {
                    List<List<com.ruoyi.warehousing.queue.Order>> sprateList = ListUtils.partition(orders, orders.size() / list1.size());

                    for (int s = 0; s < list1.size(); s++) {
                        list1.get(s).getOrders().addAll(sprateList.get(s));
                    }
                }


                EmpLog empLog1 = Upload.move(list1, platforms, doors, tally);
                EmpLog empLog2 = Putaway.work(list2, tally, tally1, elevators, cargos);

                EmpLog empLog3 = Sorting.move(list3, cargos, tally1);
                EmpLog empLog4 = Delivery.move(list4, platforms, doors, tally1);


                Calendar calendar = Calendar.getInstance();
                calendar.setTime(runTime);
                calendar.add(Calendar.SECOND, 1);
                runTime = calendar.getTime();
                if (DateUtils.convertDate2String("HH:mm:ss", runTime).equals(batch.get(j))) {
                    j++;
                }
                empLogs1.add(empLog1);
                empLogs2.add(empLog2);
                empLogs3.add(empLog3);
                empLogs4.add(empLog4);
            }
            double empRate = 0.0;
            double areaRate = 0.0;
            for (EmpLog empLog : empLogs1) {
                empRate += empLog.getComPlut();
                areaRate += empLog.getAllPlut();
            }
            double empRate1 = 0.0;
            double areaRate1 = 0.0;
            for (EmpLog empLog : empLogs2) {
                empRate1 += empLog.getComPlut();
                areaRate1 += empLog.getAllPlut();
            }
            double empRate2 = 0.0;
            double areaRate2 = 0.0;
            for (EmpLog empLog : empLogs3) {
                empRate2 += empLog.getComPlut();
                areaRate2 += empLog.getAllPlut();
            }
            double empRate3 = 0.0;
            double areaRate3 = 0.0;
            for (EmpLog empLog : empLogs4) {
                empRate3 += empLog.getComPlut();
                areaRate3 += empLog.getAllPlut();
            }

            result.setUploadEmp((int) Math.round(empRate / list1.size() / 60 / 10 / 0.85));
            result.setPutawayEmp((int) Math.round(empRate1 / list2.size() / 60 / 10 / 0.85));
            result.setSortingEmp((int) Math.round(empRate2 / list3.size() / 60 / 10 / 0.85));
            result.setDeliveryEmp((int) Math.round(empRate3 / list4.size() / 60 / 10 / 0.85));

            listr.add(result);
        }
        return listr;
    }

    public static Result action1(double goods_num, String sort_type, double transportNum2, double transportNum3, LightStorage storage) {
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
        emps1 = WarehousingUtil.initEmpOrder(emps1, orderList);
        emps2 = WarehousingUtil.initEmpSortingOrder(emps2, orderList1, sort_type);

        double work1 = 0.0;
        double work2 = 0.0;
        double distance =0.0;
        double distance1 = 0.0;
        Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
        Date endTime = DateUtils.convertString2Date("HH:mm:ss", "16:00:00");//当前时间
        while (runTime.getTime()<endTime.getTime()) {
            EmpLog empLog2 = Putaway.work(emps1, tally, tally1, elevators, cargos);
            EmpLog empLog3 = Sorting.move(emps2, cargos, tally1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(runTime);
            calendar.add(Calendar.SECOND, 1);
            runTime = calendar.getTime();
            work1+=empLog2.getComPlut();
            work2+=empLog3.getComPlut();
            distance+=empLog2.getDistance();
            distance1+=empLog3.getDistance();

        }
        Result result = new Result();
        result.setDistance(work1);
        result.setPutawayRate(work1/60/60);
        result.setSortingRate(work2/60/60);
        result.setPutawaydistance(distance);
        result.setSortdistance(distance1);
        return result;
    }
}
