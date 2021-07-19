package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.EduEquipment;

/**
 * 设备数据Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface EduEquipmentMapper 
{
    /**
     * 查询设备数据
     * 
     * @param id 设备数据ID
     * @return 设备数据
     */
    public EduEquipment selectEduEquipmentById(Long id);

    /**
     * 查询设备数据列表
     * 
     * @param eduEquipment 设备数据
     * @return 设备数据集合
     */
    public List<EduEquipment> selectEduEquipmentList(EduEquipment eduEquipment);

    /**
     * 新增设备数据
     * 
     * @param eduEquipment 设备数据
     * @return 结果
     */
    public int insertEduEquipment(EduEquipment eduEquipment);

    /**
     * 修改设备数据
     * 
     * @param eduEquipment 设备数据
     * @return 结果
     */
    public int updateEduEquipment(EduEquipment eduEquipment);

    /**
     * 删除设备数据
     * 
     * @param id 设备数据ID
     * @return 结果
     */
    public int deleteEduEquipmentById(Long id);

    /**
     * 批量删除设备数据
     * 
     * @param ids 需要删除的数据ID.
     * @return 结果
     */
    public int deleteEduEquipmentByIds(String[] ids);

    int deleteEduEquipmentByUserId(Long userId);

    void insertEduEquipmentList(List<EduEquipment> list);
}
