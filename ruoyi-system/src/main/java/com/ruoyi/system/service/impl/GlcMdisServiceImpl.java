package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GlcMdisMapper;
import com.ruoyi.system.domain.GlcMdis;
import com.ruoyi.system.service.IGlcMdisService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-10
 */
@Service
public class GlcMdisServiceImpl implements IGlcMdisService 
{
    @Autowired
    private GlcMdisMapper glcMdisMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param name 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public GlcMdis selectGlcMdisById(String name)
    {
        return glcMdisMapper.selectGlcMdisById(name);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param glcMdis 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GlcMdis> selectGlcMdisList(GlcMdis glcMdis)
    {
        return glcMdisMapper.selectGlcMdisList(glcMdis);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param glcMdis 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGlcMdis(GlcMdis glcMdis)
    {
        return glcMdisMapper.insertGlcMdis(glcMdis);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param glcMdis 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGlcMdis(GlcMdis glcMdis)
    {
        return glcMdisMapper.updateGlcMdis(glcMdis);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGlcMdisByIds(String ids)
    {
        return glcMdisMapper.deleteGlcMdisByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param name 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteGlcMdisById(String name)
    {
        return glcMdisMapper.deleteGlcMdisById(name);
    }
}
