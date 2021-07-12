package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.EduDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EduEmpDataMapper;
import com.ruoyi.system.domain.EduEmpData;
import com.ruoyi.system.service.IEduEmpDataService;
import com.ruoyi.common.core.text.Convert;

/**
 * 人员数据导入Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Service
public class EduEmpDataServiceImpl implements IEduEmpDataService 
{
    @Autowired
    private EduEmpDataMapper eduEmpDataMapper;

    /**
     * 查询人员数据导入
     * 
     * @param id 人员数据导入ID
     * @return 人员数据导入
     */
    @Override
    public EduEmpData selectEduEmpDataById(Long id)
    {
        return eduEmpDataMapper.selectEduEmpDataById(id);
    }

    /**
     * 查询人员数据导入列表
     * 
     * @param eduEmpData 人员数据导入
     * @return 人员数据导入
     */
    @Override
    public List<EduEmpData> selectEduEmpDataList(EduEmpData eduEmpData)
    {
        return eduEmpDataMapper.selectEduEmpDataList(eduEmpData);
    }

    /**
     * 新增人员数据导入
     * 
     * @param eduEmpData 人员数据导入
     * @return 结果
     */
    @Override
    public int insertEduEmpData(EduEmpData eduEmpData)
    {
        return eduEmpDataMapper.insertEduEmpData(eduEmpData);
    }

    /**
     * 修改人员数据导入
     * 
     * @param eduEmpData 人员数据导入
     * @return 结果
     */
    @Override
    public int updateEduEmpData(EduEmpData eduEmpData)
    {
        return eduEmpDataMapper.updateEduEmpData(eduEmpData);
    }

    /**
     * 删除人员数据导入对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEduEmpDataByIds(String ids)
    {
        return eduEmpDataMapper.deleteEduEmpDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除人员数据导入信息
     * 
     * @param id 人员数据导入ID
     * @return 结果
     */
    @Override
    public int deleteEduEmpDataById(Long id)
    {
        return eduEmpDataMapper.deleteEduEmpDataById(id);
    }

    @Override
    public String importExcel(List<EduEmpData> empDataList, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(empDataList) || empDataList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            eduEmpDataMapper.deleteEmpDataListByUserId(userId);
        }
        for (EduEmpData delivery: empDataList){
            delivery.setBatchId(userId);
        }
        try {
            exec(empDataList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";

    }

    @Override
    public int deleteEduEmpDataByUserId(Long userId) {

        return eduEmpDataMapper.deleteEmpDataListByUserId(userId);
    }

    public  void exec(List<EduEmpData> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<EduEmpData> newlist = null;       //存放每个线程的执行数据
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
            EduEmpDataServiceImpl.MyThread mythead = new EduEmpDataServiceImpl.MyThread(newlist,begin,end);
            //这里执行线程的方式是调用线程池里的executor.execute(mythead)方法。
            executor.execute(mythead);
        }
        begin.countDown();
        end.await();

        //执行完关闭线程池
        executor.shutdown();
    }

    class MyThread implements Runnable {
        private List<EduEmpData> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<EduEmpData> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                eduEmpDataMapper.insertEduEmpDataList(list);
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
