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
import com.ruoyi.system.domain.GlcNetworkSupplyChain;
import com.ruoyi.system.service.IGlcNetworkSupplyChainService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * glcController
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
@Controller
@RequestMapping("/system/chain")
public class GlcNetworkSupplyChainController extends BaseController
{
    private String prefix = "system/chain";

    @Autowired
    private IGlcNetworkSupplyChainService glcNetworkSupplyChainService;

    @RequiresPermissions("system:chain:view")
    @GetMapping()
    public String chain()
    {
        return prefix + "/chain";
    }

    /**
     * 查询glc列表
     */
    @RequiresPermissions("system:chain:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GlcNetworkSupplyChain glcNetworkSupplyChain)
    {
        startPage();
        List<GlcNetworkSupplyChain> list = glcNetworkSupplyChainService.selectGlcNetworkSupplyChainList(glcNetworkSupplyChain);
        return getDataTable(list);
    }

    /**
     * 导出基础数据列表
     */
    @RequiresPermissions("system:chain:export")
    @Log(title = "glc", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GlcNetworkSupplyChain glcNetworkSupplyChain)
    {
        List<GlcNetworkSupplyChain> list = glcNetworkSupplyChainService.selectGlcNetworkSupplyChainList(glcNetworkSupplyChain);
        ExcelUtil<GlcNetworkSupplyChain> util = new ExcelUtil<GlcNetworkSupplyChain>(GlcNetworkSupplyChain.class);
        return util.exportExcel(list, "chain");
    }

    /**
     * 新增基础数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存基础数据
     */
    @RequiresPermissions("system:chain:add")
    @Log(title = "glc", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GlcNetworkSupplyChain glcNetworkSupplyChain)
    {
        return toAjax(glcNetworkSupplyChainService.insertGlcNetworkSupplyChain(glcNetworkSupplyChain));
    }

    /**
     * 修改glc
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GlcNetworkSupplyChain glcNetworkSupplyChain = glcNetworkSupplyChainService.selectGlcNetworkSupplyChainById(id);
        mmap.put("glcNetworkSupplyChain", glcNetworkSupplyChain);
        return prefix + "/edit";
    }

    /**
     * 修改保存基础数据
     */
    @RequiresPermissions("system:chain:edit")
    @Log(title = "glc", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GlcNetworkSupplyChain glcNetworkSupplyChain)
    {
        return toAjax(glcNetworkSupplyChainService.updateGlcNetworkSupplyChain(glcNetworkSupplyChain));
    }

    /**
     * 删除基础数据
     */
    @RequiresPermissions("system:chain:remove")
    @Log(title = "glc", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(glcNetworkSupplyChainService.deleteGlcNetworkSupplyChainByIds(ids));
    }

    /**
     * 删除运输属性数据
     */
    @RequiresPermissions("system:chain:clean")
    @Log(title = "运输属性数据导入", businessType = BusinessType.DELETE)
    @PostMapping( "/clean")
    @ResponseBody
    public AjaxResult clean(HttpServletRequest request)
    {
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        return toAjax(glcNetworkSupplyChainService.deleteGlcNetworkSupplyChainByUserId(userId));
    }
    @Log(title = "人员数据导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:chain:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, HttpServletRequest request) throws Exception
    {
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        ExcelUtil<GlcNetworkSupplyChain> util = new ExcelUtil<>(GlcNetworkSupplyChain.class);
        List<GlcNetworkSupplyChain> propertyList = util.importExcel(file.getInputStream());
        String message = glcNetworkSupplyChainService.importExcel(propertyList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:chain:view")
    @Log(title = "运输属性数据导入", businessType = BusinessType.DELETE)
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<GlcNetworkSupplyChain> util = new ExcelUtil<GlcNetworkSupplyChain>(GlcNetworkSupplyChain.class);
        return util.importTemplateExcel("运输属性数据");
    }
}
