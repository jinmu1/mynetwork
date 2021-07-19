package com.ruoyi.network.service;

import java.util.List;

import com.ruoyi.system.domain.EduDelivery;

/**
 * 出库单Service接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface IEduDeliveryService 
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
     * 批量删除出库单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduDeliveryByIds(String ids);

    /**
     * 删除出库单信息
     * 
     * @param id 出库单ID
     * @return 结果
     */
    public int deleteEduDeliveryById(Long id);

    String importExcel(List<EduDelivery> userList, boolean updateSupport, Long userId);


    int deleteEduDeliveryByUserId(Long userId);


    List<EduDelivery> getCustomers(Long userId);

    List<EduDelivery> customersType(Long userId);

    List<EduDelivery> getCustomersGoods(Long userId);

    List<EduDelivery> getCustomersTimes(Long userId);

    List<EduDelivery> getCustomersOrders(Long userId);

    List<EduDelivery> getIK(Long userId);

    List<EduDelivery> getIKHist(Long userId);

    List<EduDelivery> getIQ(Long userId);

    List<EduDelivery> getIQHist(Long userId);


    List<EduDelivery> getDeliveryNum(Long userId);

    List<EduDelivery> getDeliveryDay(Long userId);

    List<EduDelivery> getPart(Long userId);


    List<EduDelivery> getSort(Long userId);
}
