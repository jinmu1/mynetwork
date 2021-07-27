package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.EduReceiving;

/**
 * 入库Service接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface IEduReceivingService 
{
    /**
     * 查询入库
     * 
     * @param id 入库ID
     * @return 入库
     */
    public EduReceiving selectEduReceivingById(Long id);

    /**
     * 查询入库列表
     * 
     * @param eduReceiving 入库
     * @return 入库集合
     */
    public List<EduReceiving> selectEduReceivingList(EduReceiving eduReceiving);

    /**
     * 新增入库
     * 
     * @param eduReceiving 入库
     * @return 结果
     */
    public int insertEduReceiving(EduReceiving eduReceiving);

    /**
     * 修改入库
     * 
     * @param eduReceiving 入库
     * @return 结果
     */
    public int updateEduReceiving(EduReceiving eduReceiving);

    /**
     * 批量删除入库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduReceivingByIds(String ids);

    /**
     * 删除入库信息
     * 
     * @param id 入库ID
     * @return 结果
     */
    public int deleteEduReceivingById(Long id);

    String importExcel(List<EduReceiving> receivings, boolean updateSupport, Long userId);

    int deleteEduReceivingByUserId(Long userId);

    List<EduReceiving> getEveryDays(Long userId);

    List<EduReceiving> getEveryDays1(Long userId);

    List<EduReceiving> getShipper(Long userId);

    List<EduReceiving> getCustomer(Long userId);

    List<EduReceiving> getShip_to_party(Long userId);

    List<EduReceiving> getGoods_num(Long userId);

    List<EduReceiving> getGoods_num1(Long userId);

    List<EduReceiving> getTatol_num(Long userId);

    List<EduReceiving> getEveryTime(Long userId);

    List<EduReceiving> getReceivingTime(Long userId);

    List<EduReceiving> getReceiving(Long userId);

    List<EduReceiving> getReceivingCar(Long userId);

    List<EduReceiving> getReceivingCar1(Long userId);

    List<EduReceiving> getIndustry();
}
