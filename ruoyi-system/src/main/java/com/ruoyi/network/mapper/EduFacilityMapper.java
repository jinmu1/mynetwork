package com.ruoyi.network.mapper;

import java.util.List;
import com.ruoyi.network.domain.EduFacility;

/**
 * 设施数据Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface EduFacilityMapper 
{
    /**
     * 查询设施数据
     * 
     * @param id 设施数据ID
     * @return 设施数据
     */
    public EduFacility selectEduFacilityById(Long id);

    /**
     * 查询设施数据列表
     * 
     * @param eduFacility 设施数据
     * @return 设施数据集合
     */
    public List<EduFacility> selectEduFacilityList(EduFacility eduFacility);

    /**
     * 新增设施数据
     * 
     * @param eduFacility 设施数据
     * @return 结果
     */
    public int insertEduFacility(EduFacility eduFacility);

    /**
     * 修改设施数据
     * 
     * @param eduFacility 设施数据
     * @return 结果
     */
    public int updateEduFacility(EduFacility eduFacility);

    /**
     * 删除设施数据
     * 
     * @param id 设施数据ID
     * @return 结果
     */
    public int deleteEduFacilityById(Long id);

    /**
     * 批量删除设施数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduFacilityByIds(String[] ids);

    int deleteEduFacilitiesByUserId(Long userId);

    void insertEduFacilityList(List<EduFacility> list);
}
