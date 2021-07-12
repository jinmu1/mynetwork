package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.EduDelivery;
import com.ruoyi.system.domain.EduReceiving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EduReplenishmentMapper;
import com.ruoyi.system.domain.EduReplenishment;
import com.ruoyi.system.service.IEduReplenishmentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 补货数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Service
public class EduReplenishmentServiceImpl implements IEduReplenishmentService 
{
    @Autowired
    private EduReplenishmentMapper eduReplenishmentMapper;

    /**
     * 查询补货数据
     * 
     * @param id 补货数据ID
     * @return 补货数据
     */
    @Override
    public EduReplenishment selectEduReplenishmentById(Long id)
    {
        return eduReplenishmentMapper.selectEduReplenishmentById(id);
    }

    /**
     * 查询补货数据列表
     * 
     * @param eduReplenishment 补货数据
     * @return 补货数据
     */
    @Override
    public List<EduReplenishment> selectEduReplenishmentList(EduReplenishment eduReplenishment)
    {
        return eduReplenishmentMapper.selectEduReplenishmentList(eduReplenishment);
    }

    /**
     * 新增补货数据
     * 
     * @param eduReplenishment 补货数据
     * @return 结果
     */
    @Override
    public int insertEduReplenishment(EduReplenishment eduReplenishment)
    {
        eduReplenishment.setCreateTime(DateUtils.getNowDate());
        return eduReplenishmentMapper.insertEduReplenishment(eduReplenishment);
    }

    /**
     * 修改补货数据
     * 
     * @param eduReplenishment 补货数据
     * @return 结果
     */
    @Override
    public int updateEduReplenishment(EduReplenishment eduReplenishment)
    {
        return eduReplenishmentMapper.updateEduReplenishment(eduReplenishment);
    }

    /**
     * 删除补货数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEduReplenishmentByIds(String ids)
    {
        return eduReplenishmentMapper.deleteEduReplenishmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除补货数据信息
     * 
     * @param id 补货数据ID
     * @return 结果
     */
    @Override
    public int deleteEduReplenishmentById(Long id)
    {
        return eduReplenishmentMapper.deleteEduReplenishmentById(id);
    }

    @Override
    public String importExcel(List<EduReplenishment> replenishments, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(replenishments) || replenishments.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            eduReplenishmentMapper.deleteEduReplenishmentByUserId(userId);
        }
        for (EduReplenishment delivery: replenishments){
            delivery.setBatchId(userId);
        }
        try {
            exec(replenishments);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";

    }

    @Override
    public int deleteEduReplenishmentByUserId(Long userId) {
        return eduReplenishmentMapper.deleteEduReplenishmentByUserId(userId);
    }

    @Override
    public List<EduReplenishment> getTime(Long userId) {
        return eduReplenishmentMapper.getTime(userId);
    }
    @Override
    public List<EduReplenishment> getDays(Long userId) {
        return eduReplenishmentMapper.getDays(userId);
    }
    @Override
    public List<EduReplenishment> getGoodsNum(Long userId) {
        return eduReplenishmentMapper.getGoodsNum(userId);
    }
    @Override
    public List<EduReplenishment> getGoodsType(Long userId) {
        List<EduReplenishment> list2 =     eduReplenishmentMapper.getGoodsNum(userId);
        List<EduReplenishment> list =     eduReplenishmentMapper.getGoodsType(userId);
        List<EduReplenishment> list1 = new ArrayList<>();
        int num=0,num1=0;

        for (EduReplenishment eduReplenishment:list){
            if (eduReplenishment.getPieceNum()>(list2.size()/2)){
                num++;
            }else {
                num1++;
            }
        }
        EduReplenishment eduReplenishment = new EduReplenishment();
        eduReplenishment.setPieceNum(Long.parseLong(num+""));
        eduReplenishment.setPulletNum(Long.parseLong(num1+""));
        list1.add(eduReplenishment);
        return list1;
    }
    public  void exec(List<EduReplenishment> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<EduReplenishment> newlist = null;       //存放每个线程的执行数据
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
        private List<EduReplenishment> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<EduReplenishment> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                eduReplenishmentMapper.insertEduReplenishmentList(list);
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
