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
import com.ruoyi.system.domain.GlcDelivery;
import com.ruoyi.network.service.IGlcDeliveryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 原始出库数据Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/glcDelivery")
public class GlcDeliveryController extends BaseController
{
    private String prefix = "system/glcDelivery";

    @Autowired
    private IGlcDeliveryService glcDeliveryService;

    @RequiresPermissions("system:glcDelivery:view")
    @GetMapping()
    public String delivery()
    {
        return prefix + "/delivery";
    }

    /**
     * 查询原始出库数据列表
     */
    @RequiresPermissions("system:glcDelivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GlcDelivery glcDelivery, HttpServletRequest request)
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
        glcDelivery.setBatchId(userId);
        List<GlcDelivery> list = glcDeliveryService.selectGlcDeliveryList(glcDelivery);
        return getDataTable(list);
    }

    /**
     * 导出原始出库数据列表
     */
    @RequiresPermissions("system:glcDelivery:export")
    @Log(title = "原始出库数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GlcDelivery glcDelivery, HttpServletRequest request)
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
        glcDelivery.setBatchId(userId);
        List<GlcDelivery> list = glcDeliveryService.selectGlcDeliveryList(glcDelivery);
        ExcelUtil<GlcDelivery> util = new ExcelUtil<GlcDelivery>(GlcDelivery.class);
        return util.exportExcel(list, "delivery");
    }

    /**
     * 新增原始出库数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存原始出库数据
     */
    @RequiresPermissions("system:glcDelivery:add")
    @Log(title = "原始出库数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GlcDelivery glcDelivery)
    {
        return toAjax(glcDeliveryService.insertGlcDelivery(glcDelivery));
    }

    /**
     * 修改原始出库数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GlcDelivery glcDelivery = glcDeliveryService.selectGlcDeliveryById(id);
        mmap.put("glcDelivery", glcDelivery);
        return prefix + "/edit";
    }

    /**
     * 修改保存原始出库数据
     */
    @RequiresPermissions("system:glcDelivery:edit")
    @Log(title = "原始出库数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GlcDelivery glcDelivery)
    {
        return toAjax(glcDeliveryService.updateGlcDelivery(glcDelivery));
    }

    /**
     * 删除原始出库数据
     */
    @RequiresPermissions("system:glcDelivery:remove")
    @Log(title = "原始出库数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(glcDeliveryService.deleteGlcDeliveryByIds(ids));
    }

    @RequiresPermissions("system:glcDelivery:clean")
    @Log(title = "原始出库数据", businessType = BusinessType.CLEAN)
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
        return toAjax(glcDeliveryService.deleteGlcDeliveryByUserId(userId));
    }

    @Log(title = "出库导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:glcDelivery:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, HttpServletRequest request) throws Exception
    {
        ExcelUtil<GlcDelivery> util = new ExcelUtil<>(GlcDelivery.class);
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        List<GlcDelivery> userList = util.importExcel(file.getInputStream());
        String message = glcDeliveryService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:glcDelivery:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<GlcDelivery> util = new ExcelUtil<GlcDelivery>(GlcDelivery.class);
        return util.importTemplateExcel("原始出库数据");
    }
}
