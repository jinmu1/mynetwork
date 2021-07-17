package com.ruoyi.network.mapper;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.network.domain.*;

/**
 * 原始出库数据Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface GlcDeliveryMapper 
{
    /**
     * 查询原始出库数据
     * 
     * @param id 原始出库数据ID
     * @return 原始出库数据
     */
    public GlcDelivery selectGlcDeliveryById(Long id);

    /**
     * 查询原始出库数据列表
     * 
     * @param glcDelivery 原始出库数据
     * @return 原始出库数据集合
     */
    public List<GlcDelivery> selectGlcDeliveryList(GlcDelivery glcDelivery);

    /**
     * 新增原始出库数据
     * 
     * @param glcDelivery 原始出库数据
     * @return 结果
     */
    public int insertGlcDelivery(GlcDelivery glcDelivery);

    /**
     * 修改原始出库数据
     * 
     * @param glcDelivery 原始出库数据
     * @return 结果
     */
    public int updateGlcDelivery(GlcDelivery glcDelivery);

    /**
     * 删除原始出库数据
     * 
     * @param id 原始出库数据ID
     * @return 结果
     */
    public int deleteGlcDeliveryById(Long id);

    /**
     * 批量删除原始出库数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGlcDeliveryByIds(String[] ids);

    int deleteGlcDeliveryByUserId(Long userId);

    void insertGlcDeliveryList(List<GlcDelivery> list);

    List<EduEIQ> getEIQOrderI(Long userId);

    List<EduEIQ> getEIQOrderEI(Long userId);

    List<EduEIQ> getEIQOrderE(Long userId);

    List<EduEIQ> getEIQOrderN(Long userId);

    List<EduEIQ> getEIQOrderEN(Long userId);

    List<EduEIQ> getEIQOrderQ(Long userId);

    List<EduEIQ> getEIQOrderEQ(Long userId);

    List<EduEIQ> getEIQOrderIK(Long userId);

    List<EduEIQ> getEIQOrderIQ(Long userId);

    List<EduEIQ> getEIQOrderEQ1(Long userId);

    List<EduABC> getABCCount(Long userId);

    List<EduABC> getABCFrequency(Long userId);

    List<EduABC> selectABCQuantity(Long userId);

    List<EduABC> selectEduEOQ(Long userId);

    List<EduPCB> selectReceivingList(Long userId);

    List<EduPCB> selectEduPCB(EduPCB eduPCB);


    EduPCB selectDeliveryListByGoodsName(HashMap<String,String> map);

    List<EduEIQ> getEIQOrderENHist(Long userId);

    List<EduEIQ> getEIQOrderEQHist(Long userId);

    List<EduEIQ> getCustomerOrder(Long userId);

    List<EduEIQ> getEIQOrderEN1(Long userId);

    List<EduEIQ> getEIQOrderE1(Long userId);

    List<EduEIQ> getEIQOrderEN2(Long userId);

    List<EduEIQ> getEduEn2(HashMap<String, String> map);

    List<EduEIQ> getEduEn1(HashMap<String, String> map);

    List<EduEIQ> getEduEq2(HashMap<String, String> map);

    List<EduEIQ> getEduEq1(HashMap<String, String> map);
}
