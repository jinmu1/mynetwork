package com.ruoyi.warehousing.test;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.ruoyi.warehousing.action.Action;
import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.form.*;
import com.ruoyi.warehousing.process.*;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Tray;
import com.ruoyi.warehousing.resource.facilities.buffer.Park;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.resource.facilities.storage.Storage;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;
import com.ruoyi.warehousing.result.Result;
import com.ruoyi.warehousing.utils.DateUtils;
import org.junit.Test;
import org.ujmp.core.Matrix;
import palletSingleShelvesLayout.Layout;
import org.ujmp.jmatio.ImportMatrixMAT;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WarehouseTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    @Test
    public void  getCar(){
        Platform platform = new Platform();
        for(int i=0;i<10;i++) {
        int parking_num = platform.getParking_num("小车4米6",1000,0.5,8,2);
        double area = platform.getPlatformArea(parking_num,3.5,3);
        List<Supplier> suppliers = WarehousingUtil.initSupplier(100);
        List<Goods> list = WarehousingUtil.createGoodsMessage(1000, suppliers);
        List<Car> cars = WarehousingUtil.initCar(list, 1000, "小车4米6");
        List<Platform> platforms = WarehousingUtil.initPlat(parking_num, 3.5);
        List<Emp> emps = WarehousingUtil.initEmp(parking_num);
        Park park = new Park();
        park.initPoints(cars.size(), 2);

        double distance = 0.0;
        double rate = 0.0;

            List<EmpLog> list1 = Upload.work(cars, platforms, emps, park, 300);
            for (EmpLog empLog : list1) {
                distance += empLog.getEmpStatus();
            }
            System.out.println(distance);
        }
    }

    @Test
    public void getTImes(){
        Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
        Date endTime = DateUtils.convertString2Date("HH:mm:ss", "18:00:00");//当前时间
        List<EmpLog> list = new ArrayList<>();
        HashMap<Date,List<Tray>> map = new HashMap<>();
        List<String> dates = DateUtils.getIntervalTimeList(sdf.format(runTime),sdf.format(endTime),(int)10*60/5);
        for(String str:dates){
            System.out.println(str);
        }
    }

    @Test
    public void  getTally(){
        Tally tally = new Tally();
        tally = tally.getTallyArea(1000, "小车4米6", 3, 3.5, 0.2);
        Tray tray = new Tray();

        tally.initTally(tally.getTally_transverse(), tally.getTally_longitudinal(), 3.5, 0.2, tray.getWidth(), tray.getLength());
        List<Goods> list = WarehousingUtil.createGoods(500);
        List<Tray> trays = Tray.initTrays(list,1000);
        ;
        List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());
        for (double i=1;i<2;i++ ) {
            List<EmpLog> list1 = Tallying.work(tally, trays,1, emps, i,120);
            double distance = 0.0;
            double rate = 0.0;
            for (EmpLog empLog : list1) {
                distance += empLog.getDistance();
                rate+=empLog.getEmpStatus();
            }
            System.out.println(i+":"+Math.ceil(rate));

        }
    }

    @Test
    public void getPutaway(){
        Storage storage = new Storage();
        storage = storage.getHightStorage(2000,8.2,3,0.1,1.5);
//        List<Goods> list = WarehousingUtil.createGoods(1000);
//        List<Cargo> cargos = WarehousingUtil.initCargos(list,40000,storage);
//
//        List<Emp> emps = WarehousingUtil.initEmp((int)(1000/50));
//        List<Tray> trays = Tray.initTrays(list,1000);
//        double distance = 0.0;
//        double rate = 0.0;
//        List<EmpLog> list1 = Putaway.work(storage,trays,1.1,emps);
//        for (EmpLog empLog : list1) {
//            distance += empLog.getDistance();
//            rate += empLog.getEmpStatus();
//        }
        System.out.println(storage.getArea());
    }
    @Test
    public void getSorting() {
        for (int i =1;i<=6;i++) {
            Tally tally = new Tally();
            int orderLine = 5;
            tally = tally.getTallyArea(1000, "小车4米6", 3, 3, 0.2);
            Tray tray = new Tray();

            tally.initTally(tally.getTally_transverse() * orderLine, tally.getTally_longitudinal() * orderLine, 3, 0.2, tray.getWidth(), tray.getLength());
            List<Goods> list = WarehousingUtil.createGoods(1000 );
            List<Tray> trays = Tray.initTrays(list, 1000*orderLine/10);
            List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());

            double distance = 0.0;
            double rate = 0.0;

            List<EmpLog> list1 = Sorting.work(tally, trays, 1.1, emps,1, "按单分拣", 7, 120);
            for (EmpLog empLog : list1) {
                distance += empLog.getDistance();
                rate += empLog.getEmpStatus();
            }
            System.out.println(rate);
        }

    }
    @Test
    public void  getDelivery(){
        Tally tally = new Tally();
        tally = tally.getTallyArea(1000, "小车4米6", 3, 3.5, 0.2);
        Tray tray = new Tray();

        tally.initTally(tally.getTally_transverse(), tally.getTally_longitudinal(), 3.5, 0.2, tray.getWidth(), tray.getLength());
        List<Goods> list = WarehousingUtil.createGoods(20);
        List<Tray> trays = Tray.initTrays(list,1000);
        ;
        List<Emp> emps = WarehousingUtil.initEmp(tally.getTally_transverse());
        for (double i=1;i<100;i++ ) {
            List<EmpLog> list1 = Delivery.work(tally, trays, i, emps, 3,120);
            double distance = 0.0;
            double rate = 0.0;
            for (EmpLog empLog : list1) {
                distance += empLog.getDistance();
                rate+=empLog.getEmpStatus();
            }
            System.out.println(i+":"+Math.ceil(distance));

        }
    }
    @Test
    public void loading(){
        Platform platform = new Platform();
        int parking_num = platform.getParking_num("小车4米6", 5000, 0.5, 8,2);
        double area = platform.getPlatformArea(parking_num, 3.5, 3);
        List<Customer> coustomers = WarehousingUtil.initCustomer(200);

        List<Goods> list = WarehousingUtil.createGoodsDeliveryMessage(1000, coustomers);
        List<Car> cars = WarehousingUtil.initCar1(list, 1000, "小车4米6");
        List<Platform> platforms = WarehousingUtil.initPlat(parking_num, 3.5);
        List<Emp> emps = WarehousingUtil.initEmp(parking_num);
        Park park = new Park();
        park.initPoints(cars.size(), 2);

        double distance = 0.0;
        double rate = 0.0;
        List<EmpLog> list1 = Loading.work(cars, platforms, emps, park,10800/12);
        for (EmpLog empLog : list1) {
            distance += empLog.getDistance();
            rate += empLog.getEmpStatus();
        }
        System.out.println(distance);
    }

    @Test
    public void getStorage(){
        Result result = Action.getStorage(100,9,3,0.2,1.5);

        System.out.println(Math.ceil(1/2));
    }

    /**
     * 调用matlab算法部分
     *
     * @param
     */
    @Test
    public void matlabTest() throws IOException {
        MWNumericArray a = null;
        MWNumericArray b = null;
        MWNumericArray c = null;
        Layout layout ;
        Object[] result = null;
        try {
            layout= new Layout();
            a = new MWNumericArray(Double.valueOf(1.2), MWClassID.DOUBLE);
            b = new MWNumericArray(Double.valueOf(1.5),MWClassID.DOUBLE);
            c = new MWNumericArray(Double.valueOf(1),MWClassID.DOUBLE);
            result = layout.palletSingleShelvesLayout(1,a,b,c);
        } catch (MWException e) {
            e.printStackTrace();
        }
        MWNumericArray X = (MWNumericArray) result[0];//第一个返回值是多元线性方程系数
        double b1 = X.getDouble(1);
        System.out.println("x方向的货架区域长度:" + b1);
        double b2 = X.getDouble(2);
        System.out.println("货位数:" + b2);
        double b3 = X.getDouble(3);
        System.out.println("y方向的货架区域长度:" + b3);
        System.out.println("货位面积:" + b4);
        double b5 = X.getDouble(5);
        System.out.println("z方向的货架区域高度:" + b5);
        double b6 = X.getDouble(6);
        System.out.println("货架面积率:" + b6);
        double b7 = X.getDouble(7);
        System.out.println("货架区域面积:" + b7);
        double b8 = X.getDouble(
        double b4 = X.getDouble(4);8);
        System.out.println("单位面积存量:" + b8);
        String path = System.getProperty("user.dir")+"/dat.mat";
        File file1 = new File(path);
        Matrix sampleData=ImportMatrixMAT.fromFile(file1);
        double[][] doubles = new double[][]{};
        doubles = sampleData.toDoubleArray();
        List<Point> list = new ArrayList<>();
        for (int i=0;i<doubles.length;i++){
            list.add(new Point(doubles[i][0],doubles[i][1],doubles[i][2]));
        }
        System.out.println(list.get(0).toString());

    }

}
