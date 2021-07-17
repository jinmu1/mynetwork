package com.ruoyi.network.service.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.network.mapper.EduFacilityMapper;
import com.ruoyi.network.domain.EduFacility;
import com.ruoyi.network.service.IEduFacilityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 设施数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Service
public class EduFacilityServiceImpl implements IEduFacilityService 
{
    @Autowired
    private EduFacilityMapper eduFacilityMapper;

    /**
     * 查询设施数据
     * 
     * @param id 设施数据ID
     * @return 设施数据
     */
    @Override
    public EduFacility selectEduFacilityById(Long id)
    {
        return eduFacilityMapper.selectEduFacilityById(id);
    }

    /**
     * 查询设施数据列表
     * 
     * @param eduFacility 设施数据
     * @return 设施数据
     */
    @Override
    public List<EduFacility> selectEduFacilityList(EduFacility eduFacility)
    {
        return eduFacilityMapper.selectEduFacilityList(eduFacility);
    }

    /**
     * 新增设施数据
     * 
     * @param eduFacility 设施数据
     * @return 结果
     */
    @Override
    public int insertEduFacility(EduFacility eduFacility)
    {
        return eduFacilityMapper.insertEduFacility(eduFacility);
    }

    /**
     * 修改设施数据
     * 
     * @param eduFacility 设施数据
     * @return 结果
     */
    @Override
    public int updateEduFacility(EduFacility eduFacility)
    {
        return eduFacilityMapper.updateEduFacility(eduFacility);
    }

    /**
     * 删除设施数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEduFacilityByIds(String ids)
    {
        return eduFacilityMapper.deleteEduFacilityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设施数据信息
     * 
     * @param id 设施数据ID
     * @return 结果
     */
    @Override
    public int deleteEduFacilityById(Long id)
    {
        return eduFacilityMapper.deleteEduFacilityById(id);
    }

    @Override
    public String importExcel(List<EduFacility> eduFacilities, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(eduFacilities) || eduFacilities.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            eduFacilityMapper.deleteEduFacilitiesByUserId(userId);
        }
        for (EduFacility delivery: eduFacilities){
            delivery.setBatchId(userId);
        }
        try {
            exec(eduFacilities);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";

    }

    @Override
    public int deleteEduFacilityByUserId(Long userId) {
        return eduFacilityMapper.deleteEduFacilitiesByUserId(userId);
    }

    public  void exec(List<EduFacility> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<EduFacility> newlist = null;       //存放每个线程的执行数据
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
        private List<EduFacility> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<EduFacility> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                eduFacilityMapper.insertEduFacilityList(list);
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
