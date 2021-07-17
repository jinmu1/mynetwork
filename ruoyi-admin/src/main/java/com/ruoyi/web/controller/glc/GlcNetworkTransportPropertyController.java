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
import com.ruoyi.network.domain.GlcNetworkTransportProperty;
import com.ruoyi.network.service.IGlcNetworkTransportPropertyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 运输属性数据Controller
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
@Controller
@RequestMapping("/system/property")
public class GlcNetworkTransportPropertyController extends BaseController
{
    private String prefix = "system/property";

    @Autowired
    private IGlcNetworkTransportPropertyService glcNetworkTransportPropertyService;

    @RequiresPermissions("system:property:view")
    @GetMapping()
    public String property()
    {
        return prefix + "/property";
    }

    /**
     * 查询运输属性数据列表
     */
    @RequiresPermissions("system:property:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GlcNetworkTransportProperty glcNetworkTransportProperty, HttpServletRequest request)
    {
        startPage();
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        glcNetworkTransportProperty.setBatchId(userId);
        List<GlcNetworkTransportProperty> list = glcNetworkTransportPropertyService.selectGlcNetworkTransportPropertyList(glcNetworkTransportProperty);
        return getDataTable(list);
    }

    /**
     * 导出运输属性数据列表
     */
    @RequiresPermissions("system:property:export")
    @Log(title = "运输属性数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GlcNetworkTransportProperty glcNetworkTransportProperty, HttpServletRequest request)
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
        glcNetworkTransportProperty.setBatchId(userId);
        List<GlcNetworkTransportProperty> list = glcNetworkTransportPropertyService.selectGlcNetworkTransportPropertyList(glcNetworkTransportProperty);
        ExcelUtil<GlcNetworkTransportProperty> util = new ExcelUtil<GlcNetworkTransportProperty>(GlcNetworkTransportProperty.class);
        return util.exportExcel(list, "property");
    }

    /**
     * 新增运输属性数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存运输属性数据
     */
    @RequiresPermissions("system:property:add")
    @Log(title = "运输属性数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GlcNetworkTransportProperty glcNetworkTransportProperty, HttpServletRequest request)
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
        glcNetworkTransportProperty.setBatchId(userId);
        return toAjax(glcNetworkTransportPropertyService.insertGlcNetworkTransportProperty(glcNetworkTransportProperty));
    }

    /**
     * 修改运输属性数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GlcNetworkTransportProperty glcNetworkTransportProperty = glcNetworkTransportPropertyService.selectGlcNetworkTransportPropertyById(id);
        mmap.put("glcNetworkTransportProperty", glcNetworkTransportProperty);
        return prefix + "/edit";
    }

    /**
     * 修改保存运输属性数据
     */
    @RequiresPermissions("system:property:edit")
    @Log(title = "运输属性数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GlcNetworkTransportProperty glcNetworkTransportProperty, HttpServletRequest request)
    {        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        glcNetworkTransportProperty.setBatchId(userId);

        return toAjax(glcNetworkTransportPropertyService.updateGlcNetworkTransportProperty(glcNetworkTransportProperty));
    }

    /**
     * 删除运输属性数据
     */
    @RequiresPermissions("system:property:remove")
    @Log(title = "运输属性数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(glcNetworkTransportPropertyService.deleteGlcNetworkTransportPropertyByIds(ids));
    }

    /**
     * 删除运输属性数据
     */
    @RequiresPermissions("system:property:clean")
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
        return toAjax(glcNetworkTransportPropertyService.deleteNetworkTransportByUserId(userId));
    }
    @Log(title = "人员数据导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:property:import")
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
        ExcelUtil<GlcNetworkTransportProperty> util = new ExcelUtil<>(GlcNetworkTransportProperty.class);
        List<GlcNetworkTransportProperty> propertyList = util.importExcel(file.getInputStream());
        String message = glcNetworkTransportPropertyService.importExcel(propertyList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:property:view")
    @Log(title = "运输属性数据导入", businessType = BusinessType.DELETE)
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<GlcNetworkTransportProperty> util = new ExcelUtil<GlcNetworkTransportProperty>(GlcNetworkTransportProperty.class);
        return util.importTemplateExcel("运输属性数据");
    }
}
