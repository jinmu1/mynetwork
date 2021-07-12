package com.ruoyi.system.mapper;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.system.domain.EduABC;
import com.ruoyi.system.domain.EduDelivery;
import com.ruoyi.system.domain.EduReceiving;

/**
 * 出库单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface EduDeliveryMapper 
{
    /**
     * 查询出库单
     * 
     * @param id 出库单ID
     * @return 出库单
     */
    public EduDelivery selectEduDeliveryById(Long id);

    /**
     * 查询出库单列表
     * 
     * @param eduDelivery 出库单
     * @return 出库单集合
     */
    public List<EduDelivery> selectEduDeliveryList(EduDelivery eduDelivery);

    /**
     * 新增出库单
     * 
     * @param eduDelivery 出库单
     * @return 结果
     */
    public int insertEduDelivery(EduDelivery eduDelivery);

    /**
     * 修改出库单
     * 
     * @param eduDelivery 出库单
     * @return 结果
     */
    public int updateEduDelivery(EduDelivery eduDelivery);

    /**
     * 删除出库单
     * 
     * @param id 出库单ID
     * @return 结果
     */
    public int deleteEduDeliveryById(Long id);

    /**
     * 批量删除出库单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduDeliveryByIds(String[] ids);

    int deleteEduDeliveryByUserId(Long userId);

    void insertEduDeliveryList(List<EduDelivery> list);

    List<EduDelivery> getCustomers(Long userId);

    EduDelivery getCustomersType(HashMap<String, Object> pm4);

    EduDelivery getCustomersGoods(HashMap<String, Object> pm);

    EduDelivery getDayss(HashMap<String, Object> pms);

    List<EduDelivery> getCustomersOrders(Long userId);

    List<EduDelivery> getCustomersTimes(Long userId);

    List<EduDelivery> getIK(Long userId);

    List<EduDelivery> getIKHist(Long userId);

    List<EduDelivery> getIQ(Long userId);

    List<EduDelivery> getIQHist(Long userId);


    List<EduDelivery> getDeliveryNum(Long userId);

    List<EduDelivery> getDeliveryDay(Long userId);

    List<EduDelivery> getPart(Long userId);


}
