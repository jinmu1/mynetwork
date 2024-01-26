package com.ruoyi.web.controller.glc;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GlcMdis;
import com.ruoyi.system.service.IGlcMdisService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-10-10
 */
@Controller
@RequestMapping("/system/mdis")
public class GlcMdisController extends BaseController
{
    private String prefix = "system/mdis";

    @Autowired
    private IGlcMdisService glcMdisService;

    @RequiresPermissions("system:mdis:view")
    @GetMapping()
    public String mdis()
    {
        return prefix + "/mdis";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:mdis:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GlcMdis glcMdis)
    {
        startPage();
        List<GlcMdis> list = glcMdisService.selectGlcMdisList(glcMdis);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:mdis:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GlcMdis glcMdis)
    {
        List<GlcMdis> list = glcMdisService.selectGlcMdisList(glcMdis);
        ExcelUtil<GlcMdis> util = new ExcelUtil<GlcMdis>(GlcMdis.class);
        return util.exportExcel(list, "mdis");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:mdis:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GlcMdis glcMdis)
    {
        return toAjax(glcMdisService.insertGlcMdis(glcMdis));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{name}")
    public String edit(@PathVariable("name") String name, ModelMap mmap)
    {
        GlcMdis glcMdis = glcMdisService.selectGlcMdisById(name);
        mmap.put("glcMdis", glcMdis);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:mdis:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GlcMdis glcMdis)
    {
        return toAjax(glcMdisService.updateGlcMdis(glcMdis));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:mdis:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(glcMdisService.deleteGlcMdisByIds(ids));
    }
}
