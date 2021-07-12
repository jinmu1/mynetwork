package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.GlcDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GlcNetworkTransportPropertyMapper;
import com.ruoyi.system.domain.GlcNetworkTransportProperty;
import com.ruoyi.system.service.IGlcNetworkTransportPropertyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 运输属性数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
@Service
public class GlcNetworkTransportPropertyServiceImpl implements IGlcNetworkTransportPropertyService 
{
    @Autowired
    private GlcNetworkTransportPropertyMapper glcNetworkTransportPropertyMapper;

    /**
     * 查询运输属性数据
     * 
     * @param id 运输属性数据ID
     * @return 运输属性数据
     */
    @Override
    public GlcNetworkTransportProperty selectGlcNetworkTransportPropertyById(Long id)
    {
        return glcNetworkTransportPropertyMapper.selectGlcNetworkTransportPropertyById(id);
    }

    /**
     * 查询运输属性数据列表
     * 
     * @param glcNetworkTransportProperty 运输属性数据
     * @return 运输属性数据
     */
    @Override
    public List<GlcNetworkTransportProperty> selectGlcNetworkTransportPropertyList(GlcNetworkTransportProperty glcNetworkTransportProperty)
    {
        return glcNetworkTransportPropertyMapper.selectGlcNetworkTransportPropertyList(glcNetworkTransportProperty);
    }

    /**
     * 新增运输属性数据
     * 
     * @param glcNetworkTransportProperty 运输属性数据
     * @return 结果
     */
    @Override
    public int insertGlcNetworkTransportProperty(GlcNetworkTransportProperty glcNetworkTransportProperty)
    {
        glcNetworkTransportProperty.setCreateTime(DateUtils.getNowDate());
        return glcNetworkTransportPropertyMapper.insertGlcNetworkTransportProperty(glcNetworkTransportProperty);
    }

    /**
     * 修改运输属性数据
     * 
     * @param glcNetworkTransportProperty 运输属性数据
     * @return 结果
     */
    @Override
    public int updateGlcNetworkTransportProperty(GlcNetworkTransportProperty glcNetworkTransportProperty)
    {
        return glcNetworkTransportPropertyMapper.updateGlcNetworkTransportProperty(glcNetworkTransportProperty);
    }

    /**
     * 删除运输属性数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGlcNetworkTransportPropertyByIds(String ids)
    {
        return glcNetworkTransportPropertyMapper.deleteGlcNetworkTransportPropertyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除运输属性数据信息
     * 
     * @param id 运输属性数据ID
     * @return 结果
     */
    @Override
    public int deleteGlcNetworkTransportPropertyById(Long id)
    {
        return glcNetworkTransportPropertyMapper.deleteGlcNetworkTransportPropertyById(id);
    }
    @Override
    public int deleteNetworkTransportByUserId(Long userId)
    {
        return glcNetworkTransportPropertyMapper.deleteNetworkTransportByUserId(userId);
    }

    @Override
    public String importExcel(List<GlcNetworkTransportProperty> propertyList, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(propertyList) || propertyList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            glcNetworkTransportPropertyMapper.deleteNetworkTransportByUserId(userId);
        }
        for (GlcNetworkTransportProperty property: propertyList){
            property.setBatchId(userId);
            property.setCreateBy(ShiroUtils.getSysUser().getLoginName());
            property.setCreateTime(new Date());
        }
        try {
            exec(propertyList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";

    }


    public  void exec(List<GlcNetworkTransportProperty> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<GlcNetworkTransportProperty> newlist = null;       //存放每个线程的执行数据
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
        private List<GlcNetworkTransportProperty> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<GlcNetworkTransportProperty> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                glcNetworkTransportPropertyMapper.insertGlcNetworkTransportPropertyList(list);
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
