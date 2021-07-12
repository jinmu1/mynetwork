package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.RoleProject;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysProjectMapper;
import com.ruoyi.system.service.ISysProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-19
 */
@Service
public class SysProjectServiceImpl implements ISysProjectService 
{
    @Autowired
    private SysProjectMapper sysProjectMapper;
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysProject selectSysProjectById(Long id)
    {
        return sysProjectMapper.selectSysProjectById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysProject 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysProject> selectSysProjectList(SysProject sysProject)
    {
        return sysProjectMapper.selectSysProjectList(sysProject);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysProject(SysProject sysProject)
    {
        sysProject.setCreateTime(DateUtils.getNowDate());
        sysProjectMapper.insertSysProject(sysProject);
        SysProject sysProject1 = sysProjectMapper.selectSysProject(sysProject);
        sysProject1.setCreateId(sysProject.getCreateId());
        return  sysProjectMapper.insertSysProjectUserId(sysProject1);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysProject 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysProject(SysProject sysProject)
    {
        sysProject.setUpdateTime(DateUtils.getNowDate());
        return sysProjectMapper.updateSysProject(sysProject);
    }

    /**
     * 删除【请填写功能名称】对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysProjectByIds(String ids)
    {
        return sysProjectMapper.deleteSysProjectByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<SysProject> selectSysProjects(Long userId) {

        List<SysProject> list =  sysProjectMapper.SysProjectByUserId(userId);

        SysUserRole role = sysProjectMapper.selectRoleByUserId(userId);
        List<SysProject> list1 = new ArrayList<>();
        for (SysProject sysProject:list){
            SysProject    sysProject1 = sysProjectMapper.selectSysProjectById(sysProject.getId());
            list1.add(sysProject1);
        }
        List<SysProject> list2 = sysProjectMapper.SysUserProjectByUserId(userId);
        for (SysProject sysProject : list2) {
            SysProject sysProject1 = sysProjectMapper.selectSysProjectById(sysProject.getId());
            list1.add(sysProject1);
        }
        return list1;
    }

    @Override
    public List<SysProject> selectSysProjectByUserId(Long userId) {
        return sysProjectMapper.selectSysProjectByUserId(userId);
    }


}
