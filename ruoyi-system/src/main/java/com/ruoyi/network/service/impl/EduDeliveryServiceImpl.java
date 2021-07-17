package com.ruoyi.network.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.network.mapper.EduDeliveryMapper;
import com.ruoyi.network.domain.EduDelivery;
import com.ruoyi.network.service.IEduDeliveryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 出库单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Service
public class EduDeliveryServiceImpl implements IEduDeliveryService 
{
    @Autowired
    private EduDeliveryMapper eduDeliveryMapper;

    /**
     * 查询出库单
     * 
     * @param id 出库单ID
     * @return 出库单
     */
    @Override
    public EduDelivery selectEduDeliveryById(Long id)
    {
        return eduDeliveryMapper.selectEduDeliveryById(id);
    }

    /**
     * 查询出库单列表
     * 
     * @param eduDelivery 出库单
     * @return 出库单
     */
    @Override
    public List<EduDelivery> selectEduDeliveryList(EduDelivery eduDelivery)
    {
        return eduDeliveryMapper.selectEduDeliveryList(eduDelivery);
    }

    /**
     * 新增出库单
     * 
     * @param eduDelivery 出库单
     * @return 结果
     */
    @Override
    public int insertEduDelivery(EduDelivery eduDelivery)
    {
        return eduDeliveryMapper.insertEduDelivery(eduDelivery);
    }

    /**
     * 修改出库单
     * 
     * @param eduDelivery 出库单
     * @return 结果
     */
    @Override
    public int updateEduDelivery(EduDelivery eduDelivery)
    {
        return eduDeliveryMapper.updateEduDelivery(eduDelivery);
    }

    /**
     * 删除出库单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEduDeliveryByIds(String ids)
    {
        return eduDeliveryMapper.deleteEduDeliveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出库单信息
     * 
     * @param id 出库单ID
     * @return 结果
     */
    @Override
    public int deleteEduDeliveryById(Long id)
    {
        return eduDeliveryMapper.deleteEduDeliveryById(id);
    }

    @Override
    public String importExcel(List<EduDelivery> deliveryList, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(deliveryList) || deliveryList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            eduDeliveryMapper.deleteEduDeliveryByUserId(userId);
        }
        for (EduDelivery delivery: deliveryList){
            delivery.setBatchId(userId);
        }
        try {
            exec(deliveryList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";

    }

    @Override
    public int deleteEduDeliveryByUserId(Long userId) {
        return eduDeliveryMapper.deleteEduDeliveryByUserId(userId);
    }
    @Override
    public  List<EduDelivery> getCustomers(Long userId) {
        return eduDeliveryMapper.getCustomers(userId);
    }
    @Override
    public  List<EduDelivery> getCustomersTimes(Long userId) {
        return eduDeliveryMapper.getCustomersTimes(userId);
    }
    @Override
    public  List<EduDelivery> getCustomersOrders(Long userId) {
        return eduDeliveryMapper.getCustomersOrders(userId);
    }

    @Override
    public List<EduDelivery> getIK(Long userId) {
        return eduDeliveryMapper.getIK(userId);
    }
    @Override
    public List<EduDelivery> getIKHist(Long userId) {
        return eduDeliveryMapper.getIKHist(userId);
    }
    @Override
    public List<EduDelivery> getIQ(Long userId) {
        return eduDeliveryMapper.getIQ(userId);
    }
    @Override
    public List<EduDelivery> getIQHist(Long userId) {

        return eduDeliveryMapper.getIQHist(userId);
    }

    @Override
    public List<EduDelivery> getDeliveryNum(Long userId) {
        return eduDeliveryMapper.getDeliveryNum(userId);
    }
    @Override
    public List<EduDelivery> getDeliveryDay(Long userId) {
        return eduDeliveryMapper.getDeliveryDay(userId);
    }
    @Override
    public List<EduDelivery> getPart(Long userId) {
        return eduDeliveryMapper.getPart(userId);
    }
    @Override
    public List<EduDelivery> getSort(Long userId) {
        List<EduDelivery> list = eduDeliveryMapper.getDeliveryNum(userId);
         for(EduDelivery eduDelivery:list){
             eduDelivery.setPulletNum(eduDelivery.getPulletNum()+ Math.round(Math.random()*100));
         }
        return list;
    }
    @Override
    public  List<EduDelivery> customersType(Long userId) {
        HashMap<String,Object> pms= new HashMap<String, Object>();
        pms.put("batch_id",userId);
        EduDelivery deliverys = eduDeliveryMapper.getDayss(pms);
        EduDelivery delivery = new EduDelivery();
        HashMap<String,Object> pm= new HashMap<String, Object>();
        pm.put("batch_id",userId);
        pm.put("num1",0);
        pm.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("1")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        delivery.setGoodsCode(eduDeliveryMapper.getCustomersType(pm).getCustomerCode());
        HashMap<String,Object> pm1= new HashMap<String, Object>();
        pm1.put("batch_id",userId);
        pm1.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("2")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm1.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("3")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        delivery.setCustomerCode(eduDeliveryMapper.getCustomersType(pm1).getCustomerCode());
        HashMap<String,Object> pm3= new HashMap<String, Object>();
        pm3.put("batch_id",userId);
        pm3.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("4")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm3.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("8")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        delivery.setGoodsName(eduDeliveryMapper.getCustomersType(pm3).getCustomerCode());
        HashMap<String,Object> pm4= new HashMap<String, Object>();
        pm4.put("batch_id",userId);
        pm4.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("9")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm4.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("12")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        delivery.setOrderCode(eduDeliveryMapper.getCustomersType(pm4).getCustomerCode());
        HashMap<String,Object> pm5= new HashMap<String, Object>();
        pm5.put("batch_id",userId);
        pm5.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("13")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm5.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("20")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        delivery.setShipToParty(eduDeliveryMapper.getCustomersType(pm5).getCustomerCode());
        HashMap<String,Object> pm6= new HashMap<String, Object>();
        pm6.put("batch_id",userId);
        pm6.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("21")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm6.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("10000")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        delivery.setWarmArea(eduDeliveryMapper.getCustomersType(pm6).getCustomerCode());
        List<EduDelivery> list = new ArrayList<>();
        list.add(delivery);
        return list;
    }

    @Override
    public List<EduDelivery> getCustomersGoods(Long userId) {
        HashMap<String,Object> pms= new HashMap<String, Object>();
        pms.put("batch_id",userId);
        EduDelivery deliverys = eduDeliveryMapper.getDayss(pms);
        EduDelivery delivery = new EduDelivery();
        HashMap<String,Object> pm= new HashMap<String, Object>();
        pm.put("batch_id",userId);
        pm.put("num1",0);
        pm.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("1")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        EduDelivery delivery1 = eduDeliveryMapper.getCustomersGoods(pm);
        if (delivery1!=null&&delivery1.getCustomerCode()!=null) {
            delivery.setGoodsCode(delivery1.getCustomerCode());
        }else {
            delivery.setGoodsCode("0");
        }
        HashMap<String,Object> pm1= new HashMap<String, Object>();
        pm1.put("batch_id",userId);
        pm1.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("2")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm1.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("3")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        EduDelivery delivery2 = eduDeliveryMapper.getCustomersGoods(pm1);
        if (delivery2!=null&&delivery2.getCustomerCode()!=null) {
            delivery.setCustomerCode(delivery2.getCustomerCode());
        }else {
            delivery.setCustomerCode("0");
        }
        HashMap<String,Object> pm3= new HashMap<String, Object>();
        pm3.put("batch_id",userId);
        pm3.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("4")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm3.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("8")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());

        EduDelivery delivery3 = eduDeliveryMapper.getCustomersGoods(pm3);
        if (delivery3!=null&&delivery3.getCustomerCode()!=null) {
            delivery.setGoodsName(delivery3.getCustomerCode());
        }else {
            delivery.setGoodsName("0");
        }
        HashMap<String,Object> pm4= new HashMap<String, Object>();
        pm4.put("batch_id",userId);
        pm4.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("9")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm4.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("12")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        EduDelivery delivery4 = eduDeliveryMapper.getCustomersGoods(pm4);
        if (delivery4!=null&&delivery4.getCustomerCode()!=null){
            delivery.setOrderCode(delivery4.getCustomerCode());
        }else {
            delivery.setOrderCode("0.0");
        }

        HashMap<String,Object> pm5= new HashMap<String, Object>();
        pm5.put("batch_id",userId);
        pm5.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("13")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm5.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("20")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        EduDelivery delivery5 =eduDeliveryMapper.getCustomersGoods(pm5);
        if (delivery5!=null&&delivery5.getCustomerCode()!=null) {
            delivery.setShipToParty(delivery5.getCustomerCode());
        }else {
            delivery.setShipToParty("0");
        }
        HashMap<String,Object> pm6= new HashMap<String, Object>();
        pm6.put("batch_id",userId);
        pm6.put("num1",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("21")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        pm6.put("num2",new BigDecimal(Double.parseDouble(deliverys.getGoodsCode())).multiply(new BigDecimal("10000")).divide(new BigDecimal("30"),2,BigDecimal.ROUND_UP).doubleValue());
        EduDelivery delivery6 = eduDeliveryMapper.getCustomersGoods(pm6);
        if (delivery6!=null&&delivery6.getCustomerCode()!=null) {
            delivery.setWarmArea(delivery6.getCustomerCode());
        }else {
            delivery.setWarmArea("0");
        }
        List<EduDelivery> list = new ArrayList<>();
        list.add(delivery);
        return list;
    }

    public  void exec(List<EduDelivery> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<EduDelivery> newlist = null;       //存放每个线程的执行数据
        ExecutorService executor = Executors.newFixedThreadPool(runSize);      //创建一个线程池，数量和开启线程的数量一样
        //创建两个个计数器
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(runSize);
        //循环创建线程
        for (int i = 0; i < runSize ; i++){
            //计算每个线程执行的数据
            if((i+1)==runSize){
                int startIndex = (i*count);
                int endIndex = list.size();
                newlist= list.subList(startIndex, endIndex);
            }else{
                int startIndex = (i*count);
                int endIndex = (i+1)*count;
                newlist= list.subList(startIndex, endIndex);
            }
            //线程类
            MyThread mythead = new MyThread(newlist,begin,end);
            //这里执行线程的方式是调用线程池里的executor.execute(mythead)方法。
            executor.execute(mythead);
        }
        begin.countDown();
        end.await();

        //执行完关闭线程池
        executor.shutdown();
    }

    class MyThread implements Runnable {
        private List<EduDelivery> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<EduDelivery> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                eduDeliveryMapper.insertEduDeliveryList(list);
                begin.await();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } finally {
                //这里要主要了，当一个线程执行完 了计数要减一不然这个线程会被一直挂起
// ，end.countDown()，这个方法就是直接把计数器减一的
                end.countDown();
            }
        }


    }
}
