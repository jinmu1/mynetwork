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
import com.ruoyi.network.domain.EduReplenishment;
import com.ruoyi.network.service.IEduReplenishmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 补货数据Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/replenishment")
public class EduReplenishmentController extends BaseController
{
    private String prefix = "system/replenishment";

    @Autowired
    private IEduReplenishmentService eduReplenishmentService;

    @RequiresPermissions("system:replenishment:view")
    @GetMapping()
    public String replenishment()
    {
        return prefix + "/replenishment";
    }

    /**
     * 查询补货数据列表
     */
    @RequiresPermissions("system:replenishment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduReplenishment eduReplenishment, HttpServletRequest request)
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
        eduReplenishment.setBatchId(userId);
        List<EduReplenishment> list = eduReplenishmentService.selectEduReplenishmentList(eduReplenishment);
        return getDataTable(list);
    }

    /**
     * 导出补货数据列表
     */
    @RequiresPermissions("system:replenishment:export")
    @Log(title = "补货数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduReplenishment eduReplenishment, HttpServletRequest request)
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
        eduReplenishment.setBatchId(userId);
        List<EduReplenishment> list = eduReplenishmentService.selectEduReplenishmentList(eduReplenishment);
        ExcelUtil<EduReplenishment> util = new ExcelUtil<EduReplenishment>(EduReplenishment.class);
        return util.exportExcel(list, "replenishment");
    }

    /**
     * 新增补货数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存补货数据
     */
    @RequiresPermissions("system:replenishment:add")
    @Log(title = "补货数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduReplenishment eduReplenishment)
    {
        return toAjax(eduReplenishmentService.insertEduReplenishment(eduReplenishment));
    }

    /**
     * 修改补货数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EduReplenishment eduReplenishment = eduReplenishmentService.selectEduReplenishmentById(id);
        mmap.put("eduReplenishment", eduReplenishment);
        return prefix + "/edit";
    }

    /**
     * 修改保存补货数据
     */
    @RequiresPermissions("system:replenishment:edit")
    @Log(title = "补货数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduReplenishment eduReplenishment)
    {
        return toAjax(eduReplenishmentService.updateEduReplenishment(eduReplenishment));
    }

    /**
     * 删除补货数据
     */
    @RequiresPermissions("system:replenishment:remove")
    @Log(title = "补货数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eduReplenishmentService.deleteEduReplenishmentByIds(ids));
    }

    @RequiresPermissions("system:replenishment:remove")
    @Log(title = "补货数据", businessType = BusinessType.CLEAN)
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
        return toAjax(eduReplenishmentService.deleteEduReplenishmentByUserId(userId));
    }

    @Log(title = "库存数据导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:physical:import")
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
        ExcelUtil<EduReplenishment> util = new ExcelUtil<>(EduReplenishment.class);
        List<EduReplenishment> userList = util.importExcel(file.getInputStream());
        String message = eduReplenishmentService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:physical:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<EduReplenishment> util = new ExcelUtil<EduReplenishment>(EduReplenishment.class);
        return util.importTemplateExcel("库存数据");
    }
}
