package com.ruoyi.production.resource.facilities;

import com.ruoyi.production.queue.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Warehouse {
    public final static int Length = 1000000;  //x坐标
    public final static int Width = 300000;   //y坐标
    public final static int height = 250000;   //z坐标

    private static boolean isAtChargingStation;
    private static final com.ruoyi.production.queue.Point start_station = new com.ruoyi.production.queue.Point(3, 0, 0);
    private static final List<com.ruoyi.production.queue.Point> platform1 = new ArrayList<>();

    private static final com.ruoyi.production.queue.Point door = new com.ruoyi.production.queue.Point(1,2,0);//门入口
    private static final com.ruoyi.production.queue.Point Shelf_1 = new com.ruoyi.production.queue.Point(5, 4,0);
    private static final com.ruoyi.production.queue.Point Shelf_2 = new com.ruoyi.production.queue.Point(5, 6,0);
    private static final com.ruoyi.production.queue.Point Shelf_3 = new com.ruoyi.production.queue.Point(15, 4,0);
    private static final com.ruoyi.production.queue.Point Shelf_4 = new com.ruoyi.production.queue.Point(15, 6,0);
    public com.ruoyi.production.queue.Point[] wall = new com.ruoyi.production.queue.Point[]{new com.ruoyi.production.queue.Point(0,2,0),door,new com.ruoyi.production.queue.Point(100,2,0)};
    public com.ruoyi.production.queue.Point[] tally_area= new com.ruoyi.production.queue.Point[]{Shelf_1, Shelf_2,Shelf_3,Shelf_4};//理货区
    public HashMap<String, com.ruoyi.production.queue.Point> cell = new HashMap<>();//单元存储位
    public Warehouse() {
        for (int i = 0; i < Length; i++) {
            for (int j = 0; j < Width; j++) {
                for (int z = 0; z < height; z++) {
                    com.ruoyi.production.queue.Point pt = new com.ruoyi.production.queue.Point(i, j, z);
                }
            }
        }

    }
    public LinkedList<com.ruoyi.production.queue.Point> getPath(com.ruoyi.production.queue.Point curr, com.ruoyi.production.queue.Point dest) {
        LinkedList<com.ruoyi.production.queue.Point> path = new LinkedList<com.ruoyi.production.queue.Point>();
        if (isPass(curr,dest)) {
            com.ruoyi.production.queue.Point curLocation = new com.ruoyi.production.queue.Point(curr.getX(), curr.getY(), curr.getZ());
            while (curLocation.getX() != dest.getX() || curLocation.getY() != dest.getY() || curLocation.getZ() != dest.getZ()) {
                if (curLocation.getX() != dest.getX()) {
                    double x1Diff = Math.abs(dest.getX() - curLocation.east().getX());  //go east, x + 1
                    double x2Diff = Math.abs(dest.getX() - curLocation.west().getX());  //go west, x - 1
                    // go east
                    if (x1Diff <= x2Diff) {
                        com.ruoyi.production.queue.Point newPt = new com.ruoyi.production.queue.Point(curLocation.east().getX(), curLocation.getY(), curLocation.getZ());
                        curLocation = newPt;
                        path.add(newPt);
                    }

                    else {
                        com.ruoyi.production.queue.Point newPt = new com.ruoyi.production.queue.Point(curLocation.west().getX(), curLocation.getY(), curLocation.getZ());
                        curLocation = newPt;
                        path.add(newPt);
                    }
                } else if (curLocation.getY() != dest.getY()) {
                    double y1Diff = Math.abs(dest.getY() - curLocation.north().getY()); //go north, y + 1
                    double y2Diff = Math.abs(dest.getY() - curLocation.south().getY()); //go south, y - 1
                    if (y1Diff <= y2Diff) {
                        com.ruoyi.production.queue.Point newPt = new com.ruoyi.production.queue.Point(curLocation.getX(), curLocation.north().getY(), curLocation.getZ());
                        //update the curLocation to the new Point
                        curLocation = newPt;
                        //System.out.println("Go North by 1 point.");
                        path.add(newPt);
                    } else {
                        com.ruoyi.production.queue.Point newPt = new com.ruoyi.production.queue.Point(curLocation.getX(), curLocation.south().getY(), curLocation.getZ());
                        //update the curLocation to the new Point
                        curLocation = newPt;
                        //System.out.println("Go South by 1 point.");
                        path.add(newPt);
                    }
                } else if (curLocation.getZ() != dest.getZ()) {
                    double z1Diff = Math.abs(dest.getZ() - curLocation.up().getZ()); //go north, y + 1
                    double z2Diff = Math.abs(dest.getZ() - curLocation.down().getZ()); //go south, y - 1
                    if (z1Diff <= z2Diff) {
                        com.ruoyi.production.queue.Point newPt = new com.ruoyi.production.queue.Point(curLocation.getX(), curLocation.getY(), curLocation.up().getZ());
                        //update the curLocation to the new Point
                        curLocation = newPt;
                        //System.out.println("Go North by 1 point.");
                        path.add(newPt);
                    } else {
                        com.ruoyi.production.queue.Point newPt = new com.ruoyi.production.queue.Point(curLocation.getX(), curLocation.getY(), curLocation.down().getZ());
                        //update the curLocation to the new Point
                        curLocation = newPt;
                        //System.out.println("Go South by 1 point.");
                        path.add(newPt);
                    }
                }
            }
        }
        return path;
    }

    private boolean isPass(com.ruoyi.production.queue.Point curr, com.ruoyi.production.queue.Point dest) {
        if ((curr.getX()-wall[0].getX())*(dest.getX()-wall[0].getX())>=0&&(curr.getY()-wall[0].getY())*(dest.getY()-wall[0].getY())>=0){
            return true;
        }else {
            return false;
        }

    }


    public  void set_isAtChargingStation(boolean isAtChargingStation) {
        this.isAtChargingStation = isAtChargingStation;
    }

    public static boolean get_isAtChargingStation() {
        return isAtChargingStation;
    }
    public static boolean isIsAtChargingStation() {
        return isAtChargingStation;
    }

    public static void setIsAtChargingStation(boolean isAtChargingStation) {
        Warehouse.isAtChargingStation = isAtChargingStation;
    }

    public static List<com.ruoyi.production.queue.Point> getPlatform1() {
        return platform1;
    }

    public static com.ruoyi.production.queue.Point getDoor() {
        return door;
    }

    public static com.ruoyi.production.queue.Point getShelf_1() {
        return Shelf_1;
    }

    public static com.ruoyi.production.queue.Point getShelf_2() {
        return Shelf_2;
    }

    public static com.ruoyi.production.queue.Point getShelf_3() {
        return Shelf_3;
    }

    public static com.ruoyi.production.queue.Point getShelf_4() {
        return Shelf_4;
    }

    public com.ruoyi.production.queue.Point[] getTally_area() {
        return tally_area;
    }

    public void setTally_area(com.ruoyi.production.queue.Point[] tally_area) {
        this.tally_area = tally_area;
    }

    public HashMap<String, com.ruoyi.production.queue.Point> getCell() {
        return cell;
    }

    public void setCell(HashMap<String, com.ruoyi.production.queue.Point> cell) {
        this.cell = cell;
    }

    public static int getLength() {
        return Length;
    }

    public static int getWidth() {
        return Width;
    }

    public static int getHeight() {
        return height;
    }

    public Point getCharging_Station() {
        return start_station;
    }


}
