package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.EduPhysical;

/**
 * 库存数据Service接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface IEduPhysicalService 
{
    /**
     * 查询库存数据
     * 
     * @param id 库存数据ID
     * @return 库存数据
     */
    public EduPhysical selectEduPhysicalById(Long id);

    /**
     * 查询库存数据列表
     * 
     * @param eduPhysical 库存数据
     * @return 库存数据集合
     */
    public List<EduPhysical> selectEduPhysicalList(EduPhysical eduPhysical);

    /**
     * 新增库存数据
     * 
     * @param eduPhysical 库存数据
     * @return 结果
     */
    public int insertEduPhysical(EduPhysical eduPhysical);

    /**
     * 修改库存数据
     * 
     * @param eduPhysical 库存数据
     * @return 结果
     */
    public int updateEduPhysical(EduPhysical eduPhysical);

    /**
     * 批量删除库存数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduPhysicalByIds(String ids);

    /**
     * 删除库存数据信息
     * 
     * @param id 库存数据ID
     * @return 结果
     */
    public int deleteEduPhysicalById(Long id);

    String importExcel(List<EduPhysical> userList, boolean updateSupport, Long userId);

    int deleteEduPhysicalByUserId(Long userId);
}
