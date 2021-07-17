package com.ruoyi.network.test;

import com.ruoyi.network.network.form.GlcPoint;
import com.ruoyi.network.utils.MathUtils;
import com.ruoyi.network.utils.NetworkUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class NetWorkTest {

    /**
     * 得到不同业务量变化下的仓库面积需求
     */





    @Test
    public void getGoods(){

        double total = 0.0;
        double transportNum = 10000;
        double[] num = new double[1000];
        for (int i = 0;i<1000;i+=1){
            num[i] = Math.abs(NetworkUtils.NormalDistribution(1,(float)1000));
        }
        double avg = MathUtils.sum(num);
        for (int i = 0; i<num.length;i++){
            num[i] = num[i]*transportNum/avg;
        }
        System.out.println(MathUtils.sum(num));


    }




    @Test
    public void getNodesNum(){
        File file=new File("F:/取值数据.xlsx");
        XSSFWorkbook workbook= null;
        try {
            workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //两种方式读取工作表
        //  HSSFSheet sheet=workbook.getSheet("Sheet0");
        XSSFSheet sheet;
        sheet = workbook.getSheetAt(0);
        String carType = getStringValue(sheet,2,7);//车辆厂度
        double transportNum = Double.parseDouble(getStringValue(sheet,5,7));//运输托数
        double eqmCost = Double.parseDouble(getStringValue(sheet,7,7));//设备成本
        double eqm_user_year = Double.parseDouble(getStringValue(sheet,8,7));//设备使用年限
        double unit_storage_price = Double.parseDouble(getStringValue(sheet,9,7));//单位建筑面积建设成本
        double storage_user_year = Double.parseDouble(getStringValue(sheet,10,7));//建筑使用年限
        double max_area = Double.parseDouble(getStringValue(sheet,12,7));//最大面积限制
        double min_area = Double.parseDouble(getStringValue(sheet,13,7));//最小面积限制
        String provinces =getStringValue(sheet,14,7);//省份

        double warehousing = Double.parseDouble(getStringValue(sheet,15,7));//仓储人员单位成本
        double b_goods_size = Double.parseDouble(getStringValue(sheet,16,7));//物料比例-大占比 5立方
        double m_goods_size = Double.parseDouble(getStringValue(sheet,16,8));//物料比例-中占比 0.5立方
        double s_goods_size = Double.parseDouble(getStringValue(sheet,16,9));//小类型占比 0.05立方
        double delivery_num = Double.parseDouble(getStringValue(sheet,17,7));//总出库量
        double inventory_day = Double.parseDouble(getStringValue(sheet,18,7));//库存周转天数
        double management_free = Double.parseDouble(getStringValue(sheet,19,7));//管理费用
        double inventory_num = Double.parseDouble(getStringValue(sheet,20,7));//管理费用
        double h_goods_price = Double.parseDouble(getStringValue(sheet,21,7));//物料价格高于1000
        double m_goods_price = Double.parseDouble(getStringValue(sheet,21,8));//物料价格高于100
        double s_goods_price = Double.parseDouble(getStringValue(sheet,21,9));//物料价格100以下
        double inventory_rate = Double.parseDouble(getStringValue(sheet,22,7));//库存利率
        double proportion = Double.parseDouble(getStringValue(sheet,23,7));//调拨比例
        double inventory_loss = Double.parseDouble(getStringValue(sheet,24,7));//库存损耗金额
        double h_supplier = Double.parseDouble(getStringValue(sheet,26,7));//供应商80km左右
        double m_supplier = Double.parseDouble(getStringValue(sheet,26,8));//供应商200左右
        double s_supplier = Double.parseDouble(getStringValue(sheet,26,9));//供应商1000km左右
        double h_order = Double.parseDouble(getStringValue(sheet,28,7));//高峰订单量

        XSSFSheet sheet1= workbook.getSheetAt(1);
        double result[] = {42622200,1522412,6232550,5262420,2233115,0.46,0.95,364,526,626,351,352,522,transportNum,h_order,0.02,0.33,0.14,0.33,0.98,0.56};
        for (int i=1;i<21;i++) {
            XSSFRow row = sheet1.getRow(i);
            row.createCell(2).setCellValue(result[i]);
        }
        System.out.println(getStringValue(sheet1,1,2));
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(tansportCost);

    }
    @Test
    public void getDistance(){
//        City city = new City("黄冈市","114.8784905","30.45935886");
//        City city1 = new City("上海市","121.4805389","31.23592904");
//        System.out.println(NetworkUtils.twoJuLi(city,city1));

    }


    protected String getStringValue( XSSFSheet sheet, int rowIndex,int cellIndex) {
        XSSFRow row=sheet.getRow(rowIndex);
        XSSFCell cell=row.getCell(cellIndex);
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }




}
