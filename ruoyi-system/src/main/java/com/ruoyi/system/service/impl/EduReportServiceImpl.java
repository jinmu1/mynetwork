package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EduReportMapper;
import com.ruoyi.system.domain.EduReport;
import com.ruoyi.system.service.IEduReportService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
@Service
public class EduReportServiceImpl implements IEduReportService 
{
    @Autowired
    private EduReportMapper eduReportMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public EduReport selectEduReportById(Long id)
    {
        return eduReportMapper.selectEduReportById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param eduReport 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<EduReport> selectEduReportList(EduReport eduReport)
    {
        return eduReportMapper.selectEduReportList(eduReport);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param eduReport 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertEduReport(EduReport eduReport)
    {
        return eduReportMapper.insertEduReport(eduReport);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param eduReport 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateEduReport(EduReport eduReport)
    {
        return eduReportMapper.updateEduReport(eduReport);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEduReportByIds(String ids)
    {
        return eduReportMapper.deleteEduReportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteEduReportById(Long id)
    {
        return eduReportMapper.deleteEduReportById(id);
    }

    @Override
    public List<EduReport> selectEduReportByBatchId(HashMap<String,String> map) {
        return eduReportMapper.selectEduReportByBatchId(map);
    }

    @Override
    public EduReport getReportByTitle(HashMap<String, Object> pm) {
        return eduReportMapper.getgetReportByTitle(pm);
    }

    @Override
    public int inserReport(HashMap<String, Object> pm) {
        return eduReportMapper.inserReport(pm);
    }

    @Override
    public int updateReport(HashMap<String, Object> pm) {
        return eduReportMapper.updateReport(pm);
    }

    @Override
    public List<EduReport> getReports(HashMap<String, String> map) {
        return eduReportMapper.getReports(map);
    }

    @Override
    public List<EduReport> getReports1(HashMap<String, String> map) {
        return eduReportMapper.getReports1(map);
    }
}
