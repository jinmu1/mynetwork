package com.ruoyi.network.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.network.domain.*;
import com.ruoyi.network.utils.ABCUtils;
import com.ruoyi.network.utils.MathUtils;
import com.ruoyi.network.utils.Mutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.network.mapper.GlcDeliveryMapper;
import com.ruoyi.network.service.IGlcDeliveryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 原始出库数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Service
public class GlcDeliveryServiceImpl implements IGlcDeliveryService 
{
    @Autowired
    private GlcDeliveryMapper glcDeliveryMapper;

    /**
     * 查询原始出库数据
     * 
     * @param id 原始出库数据ID
     * @return 原始出库数据
     */
    @Override
    public GlcDelivery selectGlcDeliveryById(Long id)
    {
        return glcDeliveryMapper.selectGlcDeliveryById(id);
    }

    /**
     * 查询原始出库数据列表
     * 
     * @param glcDelivery 原始出库数据
     * @return 原始出库数据
     */
    @Override
    public List<GlcDelivery> selectGlcDeliveryList(GlcDelivery glcDelivery)
    {
        return glcDeliveryMapper.selectGlcDeliveryList(glcDelivery);
    }

    /**
     * 新增原始出库数据
     * 
     * @param glcDelivery 原始出库数据
     * @return 结果
     */
    @Override
    public int insertGlcDelivery(GlcDelivery glcDelivery)
    {
        return glcDeliveryMapper.insertGlcDelivery(glcDelivery);
    }

    /**
     * 修改原始出库数据
     * 
     * @param glcDelivery 原始出库数据
     * @return 结果
     */
    @Override
    public int updateGlcDelivery(GlcDelivery glcDelivery)
    {
        return glcDeliveryMapper.updateGlcDelivery(glcDelivery);
    }

    /**
     * 删除原始出库数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGlcDeliveryByIds(String ids)
    {
        return glcDeliveryMapper.deleteGlcDeliveryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除原始出库数据信息
     * 
     * @param id 原始出库数据ID
     * @return 结果
     */
    @Override
    public int deleteGlcDeliveryById(Long id)
    {
        return glcDeliveryMapper.deleteGlcDeliveryById(id);
    }

    @Override
    public String importExcel(List<GlcDelivery> deliveryList, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(deliveryList) || deliveryList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            glcDeliveryMapper.deleteGlcDeliveryByUserId(userId);
        }
        for (GlcDelivery delivery: deliveryList){
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
    public List<EduEIQ> getEIQOrderI(Long userId) {
        return glcDeliveryMapper.getEIQOrderI(userId);
    }

    @Override
    public List<EduEIQ> getEIQOrderEI(Long userId) {
        List<EduEIQ> list =  glcDeliveryMapper.getEIQOrderEI(userId);

        for (int i=0;i<list.size();i++){
            list.get(i).setAnInt(i);
        }
        return list;
    }

    @Override
    public List<EduEIQ> getEIQOrderE(Long userId) {

        List<EduEIQ> list =  glcDeliveryMapper.getEIQOrderE(userId);
        return list;
    }
    @Override
    public List<EduEIQ> getEIQOrderN(Long userId) {

        List<EduEIQ> list =  glcDeliveryMapper.getEIQOrderN(userId);
        return list;
    }
    @Override
    public List<EduEIQ> getEIQOrderEN(Long userId) {

        List<EduEIQ> list =  glcDeliveryMapper.getEIQOrderEN(userId);
        for (int i=0;i<list.size();i++){
            list.get(i).setAnInt(i);
        }
        return list;
    }
    @Override
    public List<EduEIQ> getEIQOrderQ(Long userId) {

        List<EduEIQ> list1 =  glcDeliveryMapper.getEIQOrderQ(userId);
        List<EduEIQ> list = new ArrayList<EduEIQ>();
        for (int i=0;i<list1.size();i++) {
            EduEIQ eduEIQ = new EduEIQ();
            eduEIQ.setDate(list1.get(i).getDate());
            eduEIQ.setTotal_num(new BigDecimal(list1.get(i).getTotal_num()).setScale(0,BigDecimal.ROUND_UP).toString());
            eduEIQ.setDelivery_num(new BigDecimal(list1.get(i).getDelivery_num()).setScale(0,BigDecimal.ROUND_UP).toString());
            eduEIQ.setOdd_num((new BigDecimal(eduEIQ.getTotal_num())).subtract(new BigDecimal(eduEIQ.getDelivery_num())).setScale(0,BigDecimal.ROUND_UP).toString());
            list.add(eduEIQ);
        }
        return list;
    }
    @Override
    public List<EduEIQ> getEIQOrderEQ(Long userId) {

        List<EduEIQ> list =  glcDeliveryMapper.getEIQOrderEQ(userId);
        for (int i=0;i<list.size();i++){
            list.get(i).setAnInt(i);
        }
        return list;
    }
    @Override
    public List<EduEIQ> getEIQOrderIK(Long userId) {

        List<EduEIQ> list =  glcDeliveryMapper.getEIQOrderIK(userId);
        for (int i=0;i<list.size();i++){
            list.get(i).setAnInt(i);
        }
        return list;
    }
    @Override
    public List<EduEIQ> getEIQOrderIQ(Long userId) {

        List<EduEIQ> list =  glcDeliveryMapper.getEIQOrderIQ(userId);
        for (int i=0;i<list.size();i++){
            list.get(i).setAnInt(i);
        }
        return list;
    }
    @Override
    public List<EduABC> getABCCount(Long userId) {
        List<EduABC> list = glcDeliveryMapper.getABCCount(userId);
        List<EduABC> list1 = new ArrayList<EduABC>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getTotal_delivery()==null||list.get(i).getData()==null){
                list.remove(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            EduABC eduABC1 = new EduABC();
            eduABC1.setGoods_name(list.get(i).getGoods_name());
            eduABC1.setGoods_code(list.get(i).getGoods_code());
            eduABC1.setData(list.get(i).getData());
            eduABC1.setTotal_delivery(list.get(i).getTotal_delivery());
            eduABC1.setStock_price(list.get(i).getStock_price());
            eduABC1.setAverage_inventory(ABCUtils.getAvg(eduABC1.getTotal_delivery(), eduABC1.getData()));
            eduABC1.setMoney_occupied(ABCUtils.getMoney_occupied(eduABC1.getStock_price(), eduABC1.getAverage_inventory()));

            list1.add(eduABC1);
        }
        for(int i=0;i<list1.size();i++){
            if(list1.get(i).getAverage_inventory().equals("0")||list1.get(i).getAverage_inventory().equals("")){
                list1.remove(i);
            }

        }
        sort1(list1);
        for (int i = 0; i < list1.size(); i++) {
            list1.get(i).setAccumulated_item(i+1);
            String Percentage_item =  (new BigDecimal(list1.get(i).getAccumulated_item())).multiply(new BigDecimal("100").divide(new BigDecimal(list1.size()),2,BigDecimal.ROUND_HALF_UP)).toString();
            list1.get(i).setPercentage_item(Percentage_item);
            if (i==0){
                list1.get(i).setDelivery_occupied(list1.get(i).getAverage_inventory());
            }else {
                list1.get(i).setDelivery_occupied((new BigDecimal(list1.get(i).getAverage_inventory())).add(new BigDecimal(list1.get(i-1).getDelivery_occupied())).toString());
            }
            if (i==0){
                list1.get(i).setAccumulates_money_occupied(list1.get(i).getMoney_occupied());
            }else {
                list1.get(i).setAccumulates_money_occupied((new BigDecimal(list1.get(i).getMoney_occupied())).add(new BigDecimal(list1.get(i-1).getAccumulates_money_occupied())).toString());
            }
        }
        sort5(list1);
        for (int i = 0; i < list1.size(); i++){
            list1.get(i).setPercentage_money((new BigDecimal(list1.get(i).getDelivery_occupied())).multiply(new BigDecimal("100")).divide(new BigDecimal(list1.get(list1.size() - 1).getDelivery_occupied()), 2, BigDecimal.ROUND_HALF_UP).toString());
        }

        return list1;
    }

    @Override
    public List<EduABC> getABCCount1(Long userId) {
        List<EduABC> list = glcDeliveryMapper.getABCFrequency(userId);
        List<EduABC> list1 = new ArrayList<EduABC>();
        for (int i=0;i<list.size();i++) {
            EduABC eduABC1 = new EduABC();
            eduABC1.setGoods_name(list.get(i).getGoods_name());
            eduABC1.setGoods_code(list.get(i).getGoods_code());
            eduABC1.setDelivery_frequency(list.get(i).getDelivery_frequency());
            list1.add(eduABC1);
        }
        sort2(list1);
        for (int i=0;i<list1.size();i++){
            if (i==0) {
                list1.get(i).setAccumulated_frequency(list1.get(i).getDelivery_frequency());
            }else {
                list1.get(i).setAccumulated_frequency((new BigDecimal(list1.get(i).getDelivery_frequency())).add(new BigDecimal(list1.get(i-1).getAccumulated_frequency())).toString());
            }
        }
        for (int i=0;i<list1.size();i++) {
            list1.get(i).setPercentage((new BigDecimal(list1.get(i).getAccumulated_frequency())).multiply(new BigDecimal("100")).divide(new BigDecimal(list1.get(list1.size()-1).getAccumulated_frequency()), 2, BigDecimal.ROUND_HALF_UP).toString());
        }

        return list1;

    }

    @Override
    public List<EduABC> getGoodsNameList(Long userId) {

        return glcDeliveryMapper.getABCCount(userId);
    }

    @Override
    public int deleteGlcDeliveryByUserId(Long userId) {
        return glcDeliveryMapper.deleteGlcDeliveryByUserId(userId);
    }

    @Override
    public List<EduABC> getABCCount2(Long userId) {
        List<EduABC> list = glcDeliveryMapper.selectABCQuantity(userId);
        List<EduABC> list1 = new ArrayList<EduABC>();
        for (int i=0;i<list.size();i++) {
            EduABC eduABC1 = new EduABC();
            eduABC1.setGoods_name(list.get(i).getGoods_name());
            eduABC1.setGoods_code(list.get(i).getGoods_code());
            eduABC1.setDelivery_quantity(list.get(i).getDelivery_quantity());
            list1.add(eduABC1);
        }
        sort3(list1);
        for (int i=0;i<list1.size();i++){
            if (i==0) {
                list1.get(i).setAccumulate_quantity(list1.get(i).getDelivery_quantity());
            }else {
                list1.get(i).setAccumulate_quantity((new BigDecimal(list1.get(i).getDelivery_quantity()).setScale(0,BigDecimal.ROUND_HALF_UP)).add(new BigDecimal(list1.get(i-1).getAccumulate_quantity()).setScale(0,BigDecimal.ROUND_HALF_UP)).toString());
            }
        }
        for (int i=0;i<list1.size();i++) {
            list1.get(i).setPercentage((new BigDecimal(list1.get(i).getAccumulate_quantity())).multiply(new BigDecimal("100")).divide(new BigDecimal(list1.get(list1.size()-1).getAccumulate_quantity()), 2, BigDecimal.ROUND_HALF_UP).toString());
        }



        return list1;
    }

    @Override
    public List<EduABC> getEOQCount(Long userId) {
        return glcDeliveryMapper.selectEduEOQ(userId);
    }

    @Override
    public EduPCB selectReceivingListByGoodsName(String goods_name,Long userId) {
        HashMap<String,String> map = new HashMap<>();
        map.put("goods_name",goods_name);
        map.put("userId",String.valueOf(userId));
        return glcDeliveryMapper.selectDeliveryListByGoodsName(map);
    }

    @Override
    public List<EduPCB> selectDeliveryListByGoodsName(String goods_name, Long userId) {
        HashMap<String,String> map = new HashMap<>();
        map.put("goods_name",goods_name);
        map.put("userId",String.valueOf(userId));
        EduPCB eduPCB = glcDeliveryMapper.selectDeliveryListByGoodsName(map);
        List<EduPCB> list1 = new ArrayList<>();
        EduPCB eduPCBDemo = new EduPCB();
        eduPCBDemo.setGoods_name(eduPCB.getGoods_name());
        eduPCBDemo.setNum(eduPCB.getNum());
        eduPCBDemo.setGoods_num1(new BigDecimal(eduPCB.getUnit_cases()).toString());
        eduPCBDemo.setGoods_num2(new BigDecimal(eduPCB.getPallet_num()).toString());
        eduPCBDemo.setPallet_capacity(eduPCB.getPallet_capacity());
        eduPCBDemo.setSpecification(eduPCB.getSpecification());
        eduPCBDemo.setPallet_num(String.valueOf(Double.parseDouble(eduPCBDemo.getGoods_num1()) / (Double.parseDouble(eduPCBDemo.getPallet_capacity()))));
        eduPCBDemo.setPallet_num1(eduPCBDemo.getPallet_num());
        eduPCBDemo.setCases_num2(String.valueOf(Double.parseDouble(eduPCBDemo.getGoods_num1())/(Double.parseDouble(eduPCBDemo.getSpecification()))-(Double.parseDouble(eduPCBDemo.getGoods_num1())/(Double.parseDouble(eduPCBDemo.getSpecification())))*(Double.parseDouble(eduPCBDemo.getSpecification()))));
        eduPCBDemo.setCases_num3(new BigDecimal(eduPCBDemo.getNum()).toString());
        list1.add(eduPCBDemo);

        EduPCB eduPCBDemo1 = new EduPCB();
        BigDecimal PCB1 = null;
        BigDecimal PCB2 = null;
        BigDecimal PCB3 = null;

        PCB1 = new BigDecimal(list1.get(0).getPallet_num1()).setScale(0,BigDecimal.ROUND_DOWN);//托
        PCB2 = new BigDecimal(list1.get(0).getCases_num2()).setScale(0,BigDecimal.ROUND_DOWN);//件数
        PCB3 = new BigDecimal(list1.get(0).getCases_num3()).setScale(0,BigDecimal.ROUND_DOWN);//单品


        if (Double.parseDouble(PCB1.toString())>0&&Double.parseDouble(PCB2.toString())>0&&Double.parseDouble(PCB3.toString())>0){
            eduPCBDemo1.setResult2("P,C,B");
        }else if (Double.parseDouble(PCB1.toString())==0&&Double.parseDouble(PCB2.toString())>0&&Double.parseDouble(PCB3.toString())==0){
            eduPCBDemo1.setResult2("C");
        }else if (Double.parseDouble(PCB1.toString())==0&&Double.parseDouble(PCB2.toString())>0&&Double.parseDouble(PCB3.toString())>0){
            eduPCBDemo1.setResult2("C,B");
        }else if (Double.parseDouble(PCB1.toString())==0&&Double.parseDouble(PCB2.toString())==0&&Double.parseDouble(PCB3.toString())>0){
            eduPCBDemo1.setResult2("B");
        }else if (Double.parseDouble(PCB1.toString())>0&&Double.parseDouble(PCB2.toString())==0&&Double.parseDouble(PCB3.toString())>0){
            eduPCBDemo1.setResult2("P,C");
        }else if (Double.parseDouble(PCB1.toString())>0&&Double.parseDouble(PCB2.toString())==0&&Double.parseDouble(PCB3.toString())==0){
            eduPCBDemo1.setResult2("P");
        }else if (Double.parseDouble(PCB1.toString())>0&&Double.parseDouble(PCB2.toString())>0&&Double.parseDouble(PCB3.toString())==0){
            eduPCBDemo1.setResult2("P,B");
        }

        list1.get(0).setResult2(eduPCBDemo1.getResult2());

//        for(EduPCB pcb:lists){
//            if (goods_name.equals("薄荷味糖（宝路，750G*6包/件）") ||goods_name.equals("鸡蛋干（沈师傅，150G*50包/件）")||goods_name.equals("柠檬味糖（绿爱，2.5KG*6包/件）")||goods_name.equals("香脆椒（黄飞红，308G*10包/件）") ){
//                pcb.setResult2("C,B");
//            }else if (goods_name.equals("咖啡伴侣（雀巢，10ML*50个*6包/件）")){
//                pcb.setResult2("B");
//            }
//        }
        return list1;
    }

    @Override
    public List<EduEIQ> getEIQOrderENHist(Long userId) {
        return glcDeliveryMapper.getEIQOrderENHist(userId);
    }
    @Override
    public List<EduEIQ> getEIQOrderEQHist(Long userId) {
        return glcDeliveryMapper.getEIQOrderEQHist(userId);
    }
    @Override
    public List<EduEIQ> getCustomerOrder(Long userId) {
        return glcDeliveryMapper.getCustomerOrder(userId);
    }

    @Override
    public List<EduEIQ> getEIQOrderEQ1(Long userId) {
        return glcDeliveryMapper.getEIQOrderEQ1(userId);
    }

    @Override
    public List<EduEIQ> getEIQOrderEN1(Long userId) {
        return glcDeliveryMapper.getEIQOrderEN1(userId);
    }
    @Override
    public List<EduEIQ> getEIQOrderE1(Long userId) {
        return glcDeliveryMapper.getEIQOrderE1(userId);
    }
    @Override
    public List<EduEIQ> getEIQOrderEN2(Long userId) {
        return glcDeliveryMapper.getEIQOrderEN2(userId);
    }

    @Override
    public MathCount getENS(HashMap<String, String> map) {
        MathCount warehousing = new MathCount();
        int action = Integer.parseInt(map.get("action"));
        List<EduEIQ> list = new ArrayList<>();
        if (action ==1||action==2) {
            list = glcDeliveryMapper.getEduEn2(map);
        }else if(action==3||action==4){
            list = glcDeliveryMapper.getEduEn1(map);
        }
        double [] res  = new double[list.size()];
        for (int i=0;i<list.size();i++){
            res[i] = Double.parseDouble(list.get(i).getTimes());
        }
        warehousing.setAverage_value(new BigDecimal(String.valueOf(Mutil.find_average(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        warehousing.setMin_value(String.valueOf(Mutil.min(res)));
        warehousing.setMax_value(String.valueOf(Mutil.max(res)));
        warehousing.setRange(String.valueOf(MathUtils.range(res)));
        warehousing.setMedian(String.valueOf(MathUtils.median(res)));
        warehousing.setModes1(StringUtils.strip(String.valueOf(MathUtils.mode(res)),"[]"));
        warehousing.setKurtosis(String.valueOf(MathUtils.kurtosis(res)));
        warehousing.setSkewness(new BigDecimal(String.valueOf(MathUtils.skewness(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        warehousing.setStandard_deviation(new BigDecimal(String.valueOf(MathUtils.standardDeviation(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        warehousing.setStandard_error(new BigDecimal(String.valueOf(MathUtils.standardError(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        return warehousing;
    }
    @Override
    public MathCount getEQS(HashMap<String, String> map) {
        MathCount warehousing = new MathCount();
        int action = Integer.parseInt(map.get("action"));
        List<EduEIQ> list = new ArrayList<>();
        if (action ==1||action==2) {
            list = glcDeliveryMapper.getEduEq2(map);
        }else if(action==3||action==4){
            list = glcDeliveryMapper.getEduEq1(map);
        }
        double [] res  = new double[list.size()];
        for (int i=0;i<list.size();i++){
            res[i] = Double.parseDouble(list.get(i).getTimes());
        }
        warehousing.setAverage_value(new BigDecimal(String.valueOf(Mutil.find_average(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        warehousing.setMin_value(String.valueOf(Mutil.min(res)));
        warehousing.setMax_value(String.valueOf(Mutil.max(res)));
        warehousing.setRange(String.valueOf(MathUtils.range(res)));
        warehousing.setMedian(String.valueOf(MathUtils.median(res)));
        warehousing.setModes1(StringUtils.strip(String.valueOf(MathUtils.mode(res)),"[]"));
        warehousing.setKurtosis(String.valueOf(MathUtils.kurtosis(res)));
        warehousing.setSkewness(new BigDecimal(String.valueOf(MathUtils.skewness(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        warehousing.setStandard_deviation(new BigDecimal(String.valueOf(MathUtils.standardDeviation(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        warehousing.setStandard_error(new BigDecimal(String.valueOf(MathUtils.standardError(res))).setScale(2,BigDecimal.ROUND_UP).toString());
        return warehousing;
    }

    /**
     * 根据平均资金占有额降序
     * @param list
     */
    public void sort1(List<EduABC> list){

        Collections.sort(list, new Comparator<EduABC>() {

            @Override
            public int compare(EduABC o1, EduABC o2) {
                double a = Double.parseDouble(o1.getMoney_occupied())- Double.parseDouble(o2.getMoney_occupied());
                if (a>0){
                    return -1;
                }else if(a<0){
                    return 1;
                }else {
                    return 0;
                }
            }

        });

    }
    public void sort2(List<EduABC> list) {
        Collections.sort(list, new Comparator<EduABC>() {

            @Override
            public int compare(EduABC o1, EduABC o2) {
                int a = Integer.parseInt(o1.getDelivery_frequency()) -Integer.parseInt(o2.getDelivery_frequency() );
                if (a>0) {
                    return -1;
                } else if(a==0) {
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }
    public void sort3(List<EduABC> list) {
        Collections.sort(list, new Comparator<EduABC>() {

            @Override
            public int compare(EduABC o1, EduABC o2) {
                if (Double.parseDouble(o1.getDelivery_quantity()) -Double.parseDouble(o2.getDelivery_quantity() )> 0) {
                    return -1;
                } else if (Double.parseDouble(o1.getDelivery_quantity()) -Double.parseDouble(o2.getDelivery_quantity())==0){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
    public void sort5(List<EduABC> list){
        Collections.sort(list, new Comparator<EduABC>() {

            @Override
            public int compare(EduABC o1, EduABC o2) {
                double a = Double.parseDouble(o1.getDelivery_occupied())- Double.parseDouble(o2.getDelivery_occupied());
                if (a>0){
                    return 1;
                }else if (a==0) {
                    return 0;
                }else{
                    return -1;
                }
            }

        });
    }


    public  void exec(List<GlcDelivery> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<GlcDelivery> newlist = null;       //存放每个线程的执行数据
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
        private List<GlcDelivery> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<GlcDelivery> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                glcDeliveryMapper.insertGlcDeliveryList(list);
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
