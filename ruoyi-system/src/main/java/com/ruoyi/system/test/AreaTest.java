package com.ruoyi.system.test;

import com.ruoyi.system.network.enumType.Car;

import com.ruoyi.system.network.resource.LightStorage;
import com.ruoyi.system.network.resource.StereoStorage;
import com.ruoyi.system.utils.AreaUtils;
import org.junit.Test;

public class AreaTest {

    @Test
    public void printStatus(){
        System.out.println("车辆大小:"+ Car.小车4米6.getCode());
    }
    @Test
    public void printPlatform(){
        System.out.println("月台数量:"+ AreaUtils.getPlatform(600,"中车7米2").getPlatform_num()+";"+"月台面积:"+ AreaUtils.getPlatform(600,"中车7米2").getPlatform_area());

    }
    @Test
    public void printTally(){
        System.out.println("理货区托盘数量:"+ AreaUtils.getTally(1,"中车7米2").getPallet()+";"+"理货区面积:"+ AreaUtils.getTally(1,"中车7米2").getArea()+",横向托盘数量:"+AreaUtils.getTally(1,"中车7米2").getTally_longitudinal()+",纵向托盘数量:"+AreaUtils.getTally(1,"中车7米2").getTally_transverse());

    }
    @Test
    public void printHeapStorage(){
        System.out.println("地堆区数据:"+ AreaUtils.getHeapStorage(600).getArea()+";");

    }
    @Test
    public void printStereoStorage(){
        StereoStorage storage = AreaUtils.getStereoStorage(1,1,22);
        System.out.println("存储区数据:"+ Math.round(storage.getArea())+"平方米，层数:"+storage.getLayer()+"层，列数:"+storage.getLine()+"，排数:"+storage.getRow()+"，价格:"+storage.getPrice()+"，货位数量："+storage.getCargo()+"，堆垛机数量:"+storage.getStacker()+"，传送带长度:"+storage.getBelt()+"米");

    }
    @Test
    public void printDoubuleStereoStorage(){
        StereoStorage storage = AreaUtils.getDoubuleStereoStorageArea(2500,2000,24);
        System.out.println("存储区数据:"+ Math.round(storage.getArea())+"平方米，层数:"+storage.getLayer()+"层，列数:"+storage.getLine()+"列，排数:"+storage.getRow()+"排，价格:"+storage.getPrice()+"万，货位数量："+storage.getCargo()+"个，堆垛机数量:"+storage.getStacker()+"台，传送带长度:"+storage.getBelt()+"米");

    }
    @Test
    public void printMoreStereoStorage(){
        StereoStorage storage = AreaUtils.getMoreStereoStorageArea(2500,2000,24);
        System.out.println("存储区数据:"+ Math.round(storage.getArea())+"平方米，层数:"+storage.getLayer()+"层，列数:"+storage.getLine()+"列，排数:"+storage.getRow()+"排，价格:"+storage.getPrice()+"万，货位数量："+storage.getCargo()+"个，堆垛机数量:"+storage.getStacker()+"台，传送带长度:"+storage.getBelt()+"米");

    }
    @Test
    public void printLightStorage(){
        LightStorage storage = AreaUtils.getLightStorage(2500,200);
        System.out.println("存储区数据:"+ Math.round(storage.getArea())+"平方米，层数:"+storage.getLayer()+"层，列数:"+storage.getLine()+"列，排数:"+storage.getRow()+"排，价格:"+storage.getPrice()+"万，货位数量："+storage.getCargo()+"个,人员数量:"+storage.getEmp());

    }
    @Test
    public void printHighStorage(){
        LightStorage storage = AreaUtils.getHightStorage(3400,0);
        System.out.println("存储区数据:"+ Math.round(storage.getArea())+"平方米，层数:"+storage.getLayer()+"层，列数:"+storage.getLine()+"列，排数:"+storage.getRow()+"排，价格:"+storage.getPrice()+"万，货位数量："+storage.getCargo()+"个,人员数量:"+storage.getEmp());
//        //用excel写出距离
//        HSSFWorkbook wb=new HSSFWorkbook();
//        HSSFSheet sheet=wb.createSheet("sheet");
//        HSSFRow row=sheet.createRow(0);
//        HSSFCell cell1=row.createCell(0);
//        cell1.setCellValue("输出");
//        HSSFCell cell2=row.createCell(1);
//        cell2.setCellValue("数据");
//        HSSFRow row1=sheet.createRow(1);
//        HSSFCell name=row1.createCell(0);
//        name.setCellValue("存储区数据");
//        HSSFCell value=row1.createCell(1);
//        value.setCellValue(Math.round(storage.getArea()));
//
//        HSSFRow row2=sheet.createRow(2);
//        HSSFCell name2=row2.createCell(0);
//        name2.setCellValue("层数");
//        HSSFCell value2=row2.createCell(1);
//        value2.setCellValue(Math.round(storage.getLayer()));
//
//        HSSFRow row3=sheet.createRow(2);
//        HSSFCell name3=row2.createCell(0);
//        name2.setCellValue("层数");
//        HSSFCell value3=row2.createCell(1);
//        value2.setCellValue(Math.round(storage.getLayer()));
//        try {
//            String s="d:\\新数据.xls";
//            FileOutputStream fileOutputStream=new FileOutputStream(s);
//            wb.write(fileOutputStream);
//            fileOutputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
    @Test
    public void printEveryArea(){
        double heap = 0.0;//地堆区面积
        double stereo = 0.0;//自动化区域
        double doubleStereo = 0.0;
        double moreSteStereo = 0.0;
        double Light = 0.0;
        double  storage = 50000;//存储量
        double  throughput = 500;//通过量
        int total = 10;
        for (int i=0;i<=10;i++){//地堆存储区存储量
          for (int j=0;j<=10;j++){//高位货架存储区存储量
              for (int k=0;k<=10;k++){//立体货位存储区
                  for (int l=0;l<10;l++) {//双升立体货架存储区
                      for(int m=0;m<10;m++) {//多升立体货架存储区
                      if ((i+j+k+l+m)==total){
                           double cost = AreaUtils.getHeapStorage(i/10*storage).getPrice();
                           double area = AreaUtils.getHeapStorage(i/10*storage).getArea();
                           double cost1= AreaUtils.getHightStorage(j/10*storage,throughput).getPrice();
//                           double area = AreaUtils.getStereoStorageArea()
                        }
                      }
                  }
              }
          }
        }
    }



}
