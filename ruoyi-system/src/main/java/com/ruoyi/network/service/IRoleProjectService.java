package com.ruoyi.network.service;

import com.ruoyi.network.domain.RoleProject;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-01-19
 */
public interface IRoleProjectService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public RoleProject selectRoleProjectById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param roleProject 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<RoleProject> selectRoleProjectList(RoleProject roleProject);

    /**
     * 新增【请填写功能名称】
     * 
     * @param roleProject 【请填写功能名称】
     * @return 结果
     */
    public int insertRoleProject(RoleProject roleProject);

    /**
     * 修改【请填写功能名称】
     * 
     * @param roleProject 【请填写功能名称】
     * @return 结果
     */
    public int updateRoleProject(RoleProject roleProject);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleProjectByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteRoleProjectById(Long id);
}
