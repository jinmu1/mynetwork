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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GlcNetworkSupplyChainMapper;
import com.ruoyi.system.domain.GlcNetworkSupplyChain;
import com.ruoyi.system.service.IGlcNetworkSupplyChainService;
import com.ruoyi.common.core.text.Convert;

/**
 * glcService业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
@Service
public class GlcNetworkSupplyChainServiceImpl implements IGlcNetworkSupplyChainService 
{
    @Autowired
    private GlcNetworkSupplyChainMapper glcNetworkSupplyChainMapper;

    /**
     * 查询glc
     * 
     * @param id glcID
     * @return glc
     */
    @Override
    public GlcNetworkSupplyChain selectGlcNetworkSupplyChainById(Long id)
    {
        return glcNetworkSupplyChainMapper.selectGlcNetworkSupplyChainById(id);
    }

    /**
     * 查询glc列表
     * 
     * @param glcNetworkSupplyChain glc
     * @return glc
     */
    @Override
    public List<GlcNetworkSupplyChain> selectGlcNetworkSupplyChainList(GlcNetworkSupplyChain glcNetworkSupplyChain)
    {
        return glcNetworkSupplyChainMapper.selectGlcNetworkSupplyChainList(glcNetworkSupplyChain);
    }

    /**
     * 新增glc
     * 
     * @param glcNetworkSupplyChain glc
     * @return 结果
     */
    @Override
    public int insertGlcNetworkSupplyChain(GlcNetworkSupplyChain glcNetworkSupplyChain)
    {
        glcNetworkSupplyChain.setCreateTime(DateUtils.getNowDate());
        return glcNetworkSupplyChainMapper.insertGlcNetworkSupplyChain(glcNetworkSupplyChain);
    }

    /**
     * 修改glc
     * 
     * @param glcNetworkSupplyChain glc
     * @return 结果
     */
    @Override
    public int updateGlcNetworkSupplyChain(GlcNetworkSupplyChain glcNetworkSupplyChain)
    {
        return glcNetworkSupplyChainMapper.updateGlcNetworkSupplyChain(glcNetworkSupplyChain);
    }

    /**
     * 删除glc对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGlcNetworkSupplyChainByIds(String ids)
    {
        return glcNetworkSupplyChainMapper.deleteGlcNetworkSupplyChainByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除glc信息
     * 
     * @param id glcID
     * @return 结果
     */
    @Override
    public int deleteGlcNetworkSupplyChainById(Long id)
    {
        return glcNetworkSupplyChainMapper.deleteGlcNetworkSupplyChainById(id);
    }

    @Override
    public int deleteGlcNetworkSupplyChainByUserId(Long userId) {
        return glcNetworkSupplyChainMapper.deleteGlcNetworkSupplyChainByBatchId(userId);
    }

    @Override
    public String importExcel(List<GlcNetworkSupplyChain> chainList, boolean isUpdateSupport, Long userId) {
        if (StringUtils.isNull(chainList) || chainList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        if (isUpdateSupport){
            glcNetworkSupplyChainMapper.deleteGlcNetworkSupplyChainByBatchId(userId);
        }
        for (GlcNetworkSupplyChain chain: chainList){
            chain.setBatchId(userId);
            chain.setCreateBy(ShiroUtils.getSysUser().getLoginName());
            chain.setCreateTime(new Date());
        }
        try {
            exec(chainList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }
    public  void exec(List<GlcNetworkSupplyChain> list) throws InterruptedException{
        int count = 4000;                   //一个线程处理300条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize/count)+1;  //开启的线程数
        List<GlcNetworkSupplyChain> newlist = null;       //存放每个线程的执行数据
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
        private List<GlcNetworkSupplyChain> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        //创建个构造函数初始化 list,和其他用到的参数
        public MyThread(List<GlcNetworkSupplyChain> list, CountDownLatch begin, CountDownLatch end) {
            this.list = list;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                glcNetworkSupplyChainMapper.insertGlcNetworkSupplyChainList(list);
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
