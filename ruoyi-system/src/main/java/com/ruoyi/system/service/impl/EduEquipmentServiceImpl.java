package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EduEquipmentMapper;
import com.ruoyi.system.domain.EduEquipment;
import com.ruoyi.network.service.IEduEquipmentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 设备数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Service
public class EduEquipmentServiceImpl implements IEduEquipmentService 
{
    @Autowired
    private EduEquipmentMapper eduEquipmentMapper;

    /**
     * 查询设备数据
     * 
     * @param id 设备数据ID
     * @return 设备数据
     */
    @Override
    public EduEquipment selectEduEquipmentById(Long id)
    {
        return eduEquipmentMapper.selectEduEquipmentById(id);
    }

    /**
     * 查询设备数据列表
     * 
     * @param eduEquipment 设备数据
     * @return 设备数据
     */
    @Override
    public List<EduEquipment> selectEduEquipmentList(EduEquipment eduEquipment)
    {
        return eduEquipmentMapper.selectEduEquipmentList(eduEquipment);
    }

    /**
     * 新增设备数据
     * 
     * @param eduEquipment 设备数据
     * @return 结果
     */
    @Override
    public int insertEduEquipment(EduEquipment eduEquipment)
    {
        return eduEquipmentMapper.insertEduEquipment(eduEquipment);
    }

    /**
     * 修改设备数据
     * 
     * @param eduEquipment 设备数据
     * @return 结果
     */
    @Override
    public int updateEduEquipment(EduEquipment eduEquipment)
    {
        return eduEquipmentMapper.updateEduEquipment(eduEquipment);
    }

    /**
     * 删除设备数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEduEquipmentByIds(String ids)
    {
        return eduEquipmentMapper.deleteEduEquipmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备数据信息
     * 
     * @param id 设备数据ID
     * @return 结果
     */
    @Override
    public int deleteEduEquipmentById(Long id)
    {
        return eduEquipmentMapper.deleteEduEquipmentById(id);
    }

    @Override
    public String importExcel(List<EduEquipment> eduEquipmentList, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(eduEquipmentList) || eduEquipmentList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            eduEquipmentMapper.deleteEduEquipmentByUserId(userId);
        }
        for (EduEquipment eduEquipment: eduEquipmentList){
            eduEquipment.setBatchId(userId);
        }
        try {
            exec(eduEquipmentList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";

    }

    @Override
    public int deleteEduEquipmentByUserId(Long userId) {
        return eduEquipmentMapper.deleteEduEquipmentByUserId(userId);
    }

    public  void exec(List<EduEquipment> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<EduEquipment> newlist = null;       //存放每个线程的执行数据
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
        private List<EduEquipment> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<EduEquipment> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                eduEquipmentMapper.insertEduEquipmentList(list);
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
