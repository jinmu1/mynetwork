package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import com.ruoyi.system.domain.SysOrder;
import org.jdom2.JDOMException;

/**
 * 微信支付订单Service接口
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
public interface ISysOrderService 
{
    /**
     * 查询微信支付订单
     * 
     * @param id 微信支付订单ID
     * @return 微信支付订单
     */
    public SysOrder selectSysOrderById(Long id);

    /**
     * 查询微信支付订单列表
     * 
     * @param sysOrder 微信支付订单
     * @return 微信支付订单集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增微信支付订单
     * 
     * @param sysOrder 微信支付订单
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改微信支付订单
     * 
     * @param sysOrder 微信支付订单
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 批量删除微信支付订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysOrderByIds(String ids);

    /**
     * 删除微信支付订单信息
     * 
     * @param id 微信支付订单ID
     * @return 结果
     */
    public int deleteSysOrderById(Long id);

    String getUrlCode(SortedMap<String, String> packageParams) throws JDOMException, IOException;

    String getResponseMessage();
}
