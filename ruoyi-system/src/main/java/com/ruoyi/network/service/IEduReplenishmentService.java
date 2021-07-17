package com.ruoyi.network.service;

import java.util.List;

import com.ruoyi.network.domain.EduReplenishment;

/**
 * 补货数据Service接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface IEduReplenishmentService 
{
    /**
     * 查询补货数据
     * 
     * @param id 补货数据ID
     * @return 补货数据
     */
    public EduReplenishment selectEduReplenishmentById(Long id);

    /**
     * 查询补货数据列表
     * 
     * @param eduReplenishment 补货数据
     * @return 补货数据集合
     */
    public List<EduReplenishment> selectEduReplenishmentList(EduReplenishment eduReplenishment);

    /**
     * 新增补货数据
     * 
     * @param eduReplenishment 补货数据
     * @return 结果
     */
    public int insertEduReplenishment(EduReplenishment eduReplenishment);

    /**
     * 修改补货数据
     * 
     * @param eduReplenishment 补货数据
     * @return 结果
     */
    public int updateEduReplenishment(EduReplenishment eduReplenishment);

    /**
     * 批量删除补货数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduReplenishmentByIds(String ids);

    /**
     * 删除补货数据信息
     * 
     * @param id 补货数据ID
     * @return 结果
     */
    public int deleteEduReplenishmentById(Long id);

    String importExcel(List<EduReplenishment> userList, boolean updateSupport, Long userId);

    int deleteEduReplenishmentByUserId(Long id);

    List<EduReplenishment> getTime(Long userId);

    List<EduReplenishment> getDays(Long userId);

    List<EduReplenishment> getGoodsNum(Long userId);

    List<EduReplenishment> getGoodsType(Long userId);
}
