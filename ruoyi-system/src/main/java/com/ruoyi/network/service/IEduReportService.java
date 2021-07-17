package com.ruoyi.network.service;

import java.util.HashMap;
import java.util.List;
import com.ruoyi.network.domain.EduReport;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
public interface IEduReportService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public EduReport selectEduReportById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param eduReport 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<EduReport> selectEduReportList(EduReport eduReport);

    /**
     * 新增【请填写功能名称】
     * 
     * @param eduReport 【请填写功能名称】
     * @return 结果
     */
    public int insertEduReport(EduReport eduReport);

    /**
     * 修改【请填写功能名称】
     * 
     * @param eduReport 【请填写功能名称】
     * @return 结果
     */
    public int updateEduReport(EduReport eduReport);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEduReportByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteEduReportById(Long id);

    List<EduReport> selectEduReportByBatchId(HashMap<String,String> map );

    EduReport getReportByTitle(HashMap<String, Object> pm);

    int inserReport(HashMap<String, Object> pm);

    int updateReport(HashMap<String, Object> pm);

    List<EduReport> getReports(HashMap<String, String> map);

    List<EduReport> getReports1(HashMap<String, String> map);
}
