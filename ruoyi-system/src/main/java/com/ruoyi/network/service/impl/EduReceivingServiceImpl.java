package com.ruoyi.network.service.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.network.mapper.EduReceivingMapper;
import com.ruoyi.network.domain.EduReceiving;
import com.ruoyi.network.service.IEduReceivingService;
import com.ruoyi.common.core.text.Convert;

/**
 * 入库Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Service
public class EduReceivingServiceImpl implements IEduReceivingService {
    @Autowired
    private EduReceivingMapper eduReceivingMapper;

    /**
     * 查询入库
     *
     * @param id 入库ID
     * @return 入库
     */
    @Override
    public EduReceiving selectEduReceivingById(Long id) {
        return eduReceivingMapper.selectEduReceivingById(id);
    }

    /**
     * 查询入库列表
     *
     * @param eduReceiving 入库
     * @return 入库
     */
    @Override
    public List<EduReceiving> selectEduReceivingList(EduReceiving eduReceiving) {
        return eduReceivingMapper.selectEduReceivingList(eduReceiving);
    }

    /**
     * 新增入库
     *
     * @param eduReceiving 入库
     * @return 结果
     */
    @Override
    public int insertEduReceiving(EduReceiving eduReceiving) {
        eduReceiving.setCreateTime(DateUtils.getNowDate());
        return eduReceivingMapper.insertEduReceiving(eduReceiving);
    }

    /**
     * 修改入库
     *
     * @param eduReceiving 入库
     * @return 结果
     */
    @Override
    public int updateEduReceiving(EduReceiving eduReceiving) {
        return eduReceivingMapper.updateEduReceiving(eduReceiving);
    }

    /**
     * 删除入库对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEduReceivingByIds(String ids) {
        return eduReceivingMapper.deleteEduReceivingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除入库信息
     *
     * @param id 入库ID
     * @return 结果
     */
    @Override
    public int deleteEduReceivingById(Long id) {
        return eduReceivingMapper.deleteEduReceivingById(id);
    }
    /**
     * 导入用户数据
     *
     * @param receivings        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param userId        操作用户
     * @return 结果
     */
    @Override
    public String importExcel(List<EduReceiving> receivings, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(receivings) || receivings.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            eduReceivingMapper.deleteEduReceivingByUserId(userId);
        }
        for (EduReceiving receiving: receivings){
            receiving.setBatchId(userId);
        }
        try {
            exec(receivings);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }

    @Override
    public int deleteEduReceivingByUserId(Long userId) {
        return eduReceivingMapper.deleteEduReceivingByUserId(userId);
    }

    @Override
    public List<EduReceiving> getEveryDays(Long userId) {

        return eduReceivingMapper.getEveryDay(userId);
    }
    @Override
    public List<EduReceiving> getEveryDays1(Long userId) {

        return eduReceivingMapper.getEveryDay1(userId);
    }
    @Override
    public List<EduReceiving> getShipper(Long userId) {

        return eduReceivingMapper.getShipper(userId);
    }
    @Override
    public List<EduReceiving> getCustomer(Long userId) {

        return eduReceivingMapper.getCustomer(userId);
    }
    @Override
    public List<EduReceiving> getShip_to_party(Long userId) {

        return eduReceivingMapper.getShip_to_party(userId);
    }
    @Override
    public List<EduReceiving> getGoods_num(Long userId) {

        return eduReceivingMapper.getGoods_num(userId);
    }
    @Override
    public List<EduReceiving> getGoods_num1(Long userId) {

        return eduReceivingMapper.getGoods_num1(userId);
    }
    @Override
    public List<EduReceiving> getTatol_num(Long userId) {

        return eduReceivingMapper.getTatol_num(userId);
    }

    @Override
    public List<EduReceiving> getEveryTime(Long userId) {
        return eduReceivingMapper.getEveryTime(userId);
    }

    @Override
    public List<EduReceiving> getReceivingTime(Long userId) {
        return eduReceivingMapper.getReceivingTime(userId);
    }

    @Override
    public List<EduReceiving> getReceiving(Long userId) {

        List<EduReceiving> list = eduReceivingMapper.getReceivingTimes(userId);


        return list;
    }
    @Override
    public List<EduReceiving> getReceivingCar(Long userId) {
        List<EduReceiving> list = eduReceivingMapper.getReceivingTimes(userId);
         for(EduReceiving eduReceiving:list){
             if(eduReceiving.getCreateTime().getHours()>8) {
                 eduReceiving.setGoodsName(Math.random() * 100 + "");
             }
         }
        return list;
    }
    @Override
    public List<EduReceiving> getReceivingCar1(Long userId) {
        List<EduReceiving> list = eduReceivingMapper.getReceivingTimes(userId);
        double d = 0.0;
        for(EduReceiving eduReceiving:list){
            if(eduReceiving.getCreateTime().getHours()>8) {
                d += Math.random();
                eduReceiving.setGoodsName(d + "");
            }
        }
        return list;
    }

    @Override
    public List<EduReceiving> getIndustry() {
        List<EduReceiving> list = eduReceivingMapper.getIndustry();
        for (EduReceiving eduReceiving:list){
//            eduReceiving = eduReceivingMapper.get
        }
        return list;
    }

    public  void exec(List<EduReceiving> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<EduReceiving> newlist = null;       //存放每个线程的执行数据
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
        private List<EduReceiving> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<EduReceiving> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                eduReceivingMapper.insertEduReceivingList(list);
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
