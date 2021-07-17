package com.ruoyi.network.service;

import java.util.List;
import com.ruoyi.network.domain.EduEmpData;

/**
 * 人员数据导入Service接口
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
public interface IEduEmpDataService 
{
    /**
     * 查询人员数据导入
     * 
     * @param id 人员数据导入ID
     * @return 人员数据导入
     */
    public EduEmpData selectEduEmpDataById(Long id);

    /**
     * 查询人员数据导入列表
     * 
     * @param eduEmpData 人员数据导入
     * @return 人员数据导入集合
     */
    public List<EduEmpData> selectEduEmpDataList(EduEmpData eduEmpData);

    /**
     * 新增人员数据导入
     * 
     * @param eduEmpData 人员数据导入
     * @return 结果
     */
    public int insertEduEmpData(EduEmpData eduEmpData);

    /**
     * 修改人员数据导入
     * 
     * @param eduEmpData 人员数据导入
     * @return 结果
     */
    public int updateEduEmpData(EduEmpData eduEmpData);

    /**
     * 批量删除人员数据导入
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduEmpDataByIds(String ids);

    /**
     * 删除人员数据导入信息
     * 
     * @param id 人员数据导入ID
     * @return 结果
     */
    public int deleteEduEmpDataById(Long id);

    String importExcel(List<EduEmpData> userList, boolean updateSupport, Long userId);

    int deleteEduEmpDataByUserId(Long userId);
}
