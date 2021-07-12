package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.RoleProject;
import com.ruoyi.system.mapper.RoleProjectMapper;
import com.ruoyi.system.service.IRoleProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-19
 */
@Service
public class RoleProjectServiceImpl implements IRoleProjectService 
{
    @Autowired
    private RoleProjectMapper roleProjectMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public RoleProject selectRoleProjectById(Long id)
    {
        return roleProjectMapper.selectRoleProjectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param roleProject 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<RoleProject> selectRoleProjectList(RoleProject roleProject)
    {
        return roleProjectMapper.selectRoleProjectList(roleProject);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param roleProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertRoleProject(RoleProject roleProject)
    {
        roleProject.setCreateTime(DateUtils.getNowDate());
        return roleProjectMapper.insertRoleProject(roleProject);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param roleProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateRoleProject(RoleProject roleProject)
    {
        return roleProjectMapper.updateRoleProject(roleProject);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRoleProjectByIds(String ids)
    {
        return roleProjectMapper.deleteRoleProjectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteRoleProjectById(Long id)
    {
        return roleProjectMapper.deleteRoleProjectById(id);
    }
}
