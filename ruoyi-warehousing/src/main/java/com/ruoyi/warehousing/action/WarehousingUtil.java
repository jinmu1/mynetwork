package com.ruoyi.warehousing.action;

import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Elevator;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.form.WorkTime;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.utils.AreaUtils;
import com.ruoyi.warehousing.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WarehousingUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");



    public static void initTime(Date runTime,Date startTime,Date endTime) {
        startTime = DateUtils.randomDate("2019-12-24 08:00:00","2019-12-24 08:01:00");
        runTime   = DateUtils.randomDate("2019-12-24 08:00:00","2019-12-24 08:01:00");
        endTime   = DateUtils.randomDate("2019-12-24 24:00:00","2019-12-24 24:01:00");
    }
    public static  List<Platform> initPlat(int num) {
        List<Platform> platforms = new ArrayList<>();
        for (int i =0 ;i<num ; i++){
            platforms.add(new Platform("月台"+1+"号",0,0,new Point(0+i* AreaUtils.platform_width,0,0)));
        }
        return platforms;
    }

    /**
     * 初始化门洞
     *
     */
    public static List<Point> initDoor() {
        List<Point> door = new ArrayList<>();
        door.add(new Point(8,3,0));
        door.add(new Point(34,3,0));
        door.add(new Point(40,3,0));
        return door;
    }
    /**
     * 初始化电梯
     */
    public static List<Elevator> initElevator(int num, int floor) {
        List<Elevator> elevators = new ArrayList<>();
         for(int i =0 ;i<num; i++){
             int f = (int)(Math.random()*floor);
             elevators.add(new Elevator("电梯"+0+"号",3,f , WorkTime.e_v0,WorkTime.e_v1,0,new Point(23,0,f )));
         }
         return elevators;
    }

    /**
     * 获取电梯区域位置
     * @return
     */
     public  static List<Point> initElevatorPark(){
           List<Point> points = new ArrayList<>();
         for(int i=1;i<=5;i++) {
             if (i!=2) {
                 points.add(new Point(23,1,(i-1)*5));
             }
         }
         return points;
     }

    /**
     * 获取月台工作人员
     * @param emp
     * @return
     */
    public static Point getPlatform(Emp emp, List<Platform> platforms) {
        Point point = new Point();
        for(Platform platform:platforms) {
            if (emp.getCode().equals(platform.getCode())) {
                point=platform.getPosition();
            }
        }
        return point;
    }


    public static Point getPath(Emp emp, double num) {
        Point curr = emp.getCurr(); Point dest = emp.getTar();
        Point curLocation = new Point(curr.getX(), curr.getY(), curr.getZ());
        if (Math.abs(curLocation.getX()-dest.getX())>=1) {
            double x1Diff = Math.abs(dest.getX() - curLocation.east(num).getX());  //go east, x + 1
            double x2Diff = Math.abs(dest.getX() - curLocation.west(num).getX());  //go west, x - 1
            if (x1Diff <= x2Diff) {
                Point newPt = new Point(curLocation.east(num).getX(), curLocation.getY(), curLocation.getZ());
                curLocation = newPt;
            } else {
                Point newPt = new Point(curLocation.west(num).getX(), curLocation.getY(), curLocation.getZ());
                curLocation = newPt;
            }
        } else if (Math.abs(curLocation.getY() - dest.getY())>=1) {
            double y1Diff = Math.abs(dest.getY() - curLocation.north(num).getY()); //go north, y + 1
            double y2Diff = Math.abs(dest.getY() - curLocation.south(num).getY()); //go south, y - 1
            if (y1Diff <= y2Diff) {
                Point newPt = new Point(curLocation.getX(), curLocation.north(num).getY(), curLocation.getZ());
                curLocation = newPt;
            } else {
                Point newPt = new Point(curLocation.getX(), curLocation.south(num).getY(), curLocation.getZ());
                curLocation = newPt;
            }
        } else if (curLocation.getZ() != dest.getZ()) {
            double z1Diff = Math.abs(dest.getZ() - curLocation.up(num).getZ()); //go north, y + 1
            double z2Diff = Math.abs(dest.getZ() - curLocation.down(num).getZ()); //go south, y - 1
            if (z1Diff <= z2Diff) {
                Point newPt = new Point(curLocation.getX(), curLocation.getY(), curLocation.up().getZ());
                curLocation = newPt;
            } else {
                Point newPt = new Point(curLocation.getX(), curLocation.getY(), curLocation.down().getZ());
                curLocation = newPt;

            }
        }else {
            curLocation = dest;
        }
        return curLocation;
    }

    /**
     * 获取当前到达坐标和目标坐标的距离
     * @param point
     * @param tar
     * @return
     */
    public static double getDistance(Point point, Point tar) {
        return Math.abs(point.getX()-tar.getX())+Math.abs(point.getY()-tar.getY());
    }

    /**
     * 到达理货区
     * @param tally
     * @param tar
     * @param i
     */
    public static void emptys(Tally tally, Point tar, int i) {
        Iterator<Point> iterator = tally.getPoints().iterator();
        while (iterator.hasNext()){
            Point point = iterator.next();
            if (getDistance(point,tar)==0){
                point.setStatus(i);
            }
        }

    }

    /**
     * 获取当前理货区物料
     * @return
     */
    public static List<Goods> getGoodsPullt(Tally tally) {
        List<Goods> goods = new ArrayList<>();
        List<Goods> goodsList = tally.getGoodsList();
        for (int i=0;i<goodsList.size()/tally.getTorr();i++){
            goods.add(goodsList.get(i));
        }
        Iterator<Goods> iterator = tally.getGoodsList().iterator();
        while (iterator.hasNext()){
            Goods goods1 = iterator.next();
            int m= 0;
            for (Goods goods2:goods){
                if (goods1.getGoodsCode().equals(goods2.getGoodsCode())&&goods1.getPlutNum()==goods2.getPlutNum()&&m==0){
                    iterator.remove();
                    m++;
                }
            }
        }
        return goods;
    }

    /**
     * 获取电梯
     * @param emp
     * @return
     */
    public static Point getPark(Emp emp,List<Point> elevatorPark) {
        Point p = new Point();
        for(Point point:elevatorPark){
            if (point.getZ()==emp.getCurr().getZ()){
                p = point;
            }
        }
        return p;
    }

    /**
     * 获取最快的电梯
     * @param num
     * @param elevators
     * @return
     */
    public static Elevator waitEle(double num,List<Elevator> elevators) {
        int i=1000;
        Elevator elevator = new Elevator();
        for(Elevator elevator1:elevators){
            if (elevator1.getStatus()==0&&Math.abs(elevator1.getFloor()-num)<i){
                elevator = elevator1;
                i=elevator1.getFloor();
            }
        }
        return elevator;
    }

    /**
     * 设置电梯是否运行
     * @param elevator
     * @param status
     * @param elevators
     */
    public static void freeEle(Elevator elevator,int status,List<Elevator> elevators) {
        for (Elevator elevator1:elevators){
            if (elevator!=null&&elevator.getCode()!=null&&elevator.getCode().equals(elevator1.getCode())){
                elevator1.setStatus(status);
            }
        }
    }

    /**
     * 获取路径
      * @param curr
     * @param dest
     * @param num
     * @return
     */
    public static Point getPath1(Point curr,Point dest,double num) {
        Point curLocation = new Point(curr.getX(), curr.getY(), curr.getZ());
        if (Math.abs(curLocation.getX()-dest.getX())>=1) {
            double x1Diff = Math.abs(dest.getX() - curLocation.east(num).getX());  //go east, x + 1
            double x2Diff = Math.abs(dest.getX() - curLocation.west(num).getX());  //go west, x - 1
            if (x1Diff <= x2Diff) {
                Point newPt = new Point(curLocation.east(num).getX(), curLocation.getY(), curLocation.getZ());
                curLocation = newPt;
            } else {
                Point newPt = new Point(curLocation.west(num).getX(), curLocation.getY(), curLocation.getZ());
                curLocation = newPt;
            }
        } else if (Math.abs(curLocation.getY() - dest.getY())>=1) {
            double y1Diff = Math.abs(dest.getY() - curLocation.north(num).getY()); //go north, y + 1
            double y2Diff = Math.abs(dest.getY() - curLocation.south(num).getY()); //go south, y - 1
            if (y1Diff <= y2Diff) {
                Point newPt = new Point(curLocation.getX(), curLocation.north(num).getY(), curLocation.getZ());
                curLocation = newPt;
            } else {
                Point newPt = new Point(curLocation.getX(), curLocation.south(num).getY(), curLocation.getZ());
                curLocation = newPt;
            }
        } else if (curLocation.getZ() != dest.getZ()) {
            double z1Diff = Math.abs(dest.getZ() - curLocation.up(num).getZ()); //go north, y + 1
            double z2Diff = Math.abs(dest.getZ() - curLocation.down(num).getZ()); //go south, y - 1
            if (z1Diff <= z2Diff) {
                Point newPt = new Point(curLocation.getX(), curLocation.getY(), curLocation.up().getZ());
                curLocation = newPt;
            } else {
                Point newPt = new Point(curLocation.getX(), curLocation.getY(), curLocation.down().getZ());
                curLocation = newPt;

            }
        }else {
            curLocation = dest;
        }
        return curLocation;
    }

    /**
     * 高层理货区
     * @param tar
     * @param i
     */
    public static void emptys1(Point tar, int i,Tally tally) {
        Iterator<Point> iterator = tally.getPoints().iterator();
        while (iterator.hasNext()){
            Point point = iterator.next();
            if (getDistance(point,tar)==0){
                point.setStatus(i);
            }
        }
    }

    /**
     * 获取高层理货区
     * @param tally
     * @return
     */
    public static Point getTally1(Tally tally) {
        Point point =  new Point();
        for (Point point1:tally.getPoints()){
            if (point1.getStatus()==0){
                point = point1;
                break;
            }
        }
        return point;
    }

    /***
     * 获取物料存储区位置
     * @param emp
     * @param cargos
     * @return
     */
    public static Point getGoodsPosition(Emp emp,List<Cargo> cargos) {
        Point point = new Point(35,15,15);
        List<Goods> list = emp.getGoods();
        if (list.size()>0) {
            Goods goods = list.get(0);
            for (Cargo cargo : cargos) {
                if (cargo.getGoods() != null && cargo.getPoint() != null && cargo.getGoods().getGoodsCode() != null && goods != null && cargo.getGoods().getGoodsCode().equals(goods.getGoodsCode())) {
                    point = cargo.getPoint();
                }
            }
            emp.getGoods().remove(0);
        }
        return point;
    }

    /**
     * 从车辆上搬出货物
     * @param code
     */
    public static void removeCar(String code,List<Platform> platforms) {
        Iterator<Platform> platformIterator = platforms.iterator();
        while (platformIterator.hasNext()){
            Platform platform = platformIterator.next();
            if (platform.getCode().equals(code)){
                platform.getCarLine().removeCar();
                platform.remoteEmp();

                if (platform.getCarLine().getTora()<=0) {
                    platform.setStatus(-1);
                    platform.setSign(WorkTime.T);
                }
            }
        }
    }

    /**
     * 获取仓库门洞位置
     * @param curr
     * @return
     */
    public static Point getDoor(Point curr,List<Point> door) {
        double num =1000000;
        Point point = new Point();
        for (Point point1:door) {
            if (num >(Math.abs(point1.getX()-curr.getX())+Math.abs(point1.getY()-curr.getY()))) {
                num = Math.abs(point1.getX()-curr.getX())+Math.abs(point1.getY()-curr.getY());
                point = point1;
            }
        }
        return point;
    }

    /**
     * 获取缓存区存储点
     * @param tally
     * @return
     */
    public static Point getTally(Tally tally) {
        Point point =  new Point();
        for (Point point1:tally.getPoints()){
            if (point1.getStatus()==0){
                point = point1;
                break;
            }
        }
        return point;
    }


    public static List<Emp> initEmp(int s) {
        List<Emp> emps = new ArrayList<>();
        for (int i=0;i<s;i++){
            emps.add(new Emp("上架"+i+"号",0,new Point((int)Math.random()*8,(int)Math.random()*8,10)));
        }
        return emps;

    }

    public static Tally initTally() {
       Tally tally = new Tally();
        List<Point> points = new ArrayList<>();
        for (int i=10;i<20;i++){
            for (int j=4;j<12;j++){
                points.add(new Point(i,j,0,0));
            }
        }
        tally.setPoints(points);
        tally.setTorr(0);
        tally.setTorr(0);
        return tally;


    }

    public static Tally initTally1() {
        Tally tally1=new Tally();
        List<Point> points1 = new ArrayList<>();
        for (int i=20;i<32;i++){
            for (int j =4;j<12;j++ ){
                if (i<24||i>28){
                    points1.add(new Point(i,j,10,0));
                }
            }
        }
//        tally1.setArea(points1);
        return tally1;
    }


}
