package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GlcMdis;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-10
 */
public interface IGlcMdisService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param name 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public GlcMdis selectGlcMdisById(String name);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param glcMdis 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GlcMdis> selectGlcMdisList(GlcMdis glcMdis);

    /**
     * 新增【请填写功能名称】
     * 
     * @param glcMdis 【请填写功能名称】
     * @return 结果
     */
    public int insertGlcMdis(GlcMdis glcMdis);

    /**
     * 修改【请填写功能名称】
     * 
     * @param glcMdis 【请填写功能名称】
     * @return 结果
     */
    public int updateGlcMdis(GlcMdis glcMdis);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGlcMdisByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param name 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteGlcMdisById(String name);
}
