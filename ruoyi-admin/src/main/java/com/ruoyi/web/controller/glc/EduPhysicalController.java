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
import com.ruoyi.system.domain.EduPhysical;
import com.ruoyi.system.service.IEduPhysicalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 库存数据Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/physical")
public class EduPhysicalController extends BaseController
{
    private String prefix = "system/physical";

    @Autowired
    private IEduPhysicalService eduPhysicalService;

    @RequiresPermissions("system:physical:view")
    @GetMapping()
    public String physical()
    {
        return prefix + "/physical";
    }

    /**
     * 查询库存数据列表
     */
    @RequiresPermissions("system:physical:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduPhysical eduPhysical, HttpServletRequest request)
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
        eduPhysical.setBatchId(userId);
        List<EduPhysical> list = eduPhysicalService.selectEduPhysicalList(eduPhysical);
        return getDataTable(list);
    }

    /**
     * 导出库存数据列表
     */
    @RequiresPermissions("system:physical:export")
    @Log(title = "库存数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduPhysical eduPhysical, HttpServletRequest request)
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
        eduPhysical.setBatchId(userId);
        List<EduPhysical> list = eduPhysicalService.selectEduPhysicalList(eduPhysical);
        ExcelUtil<EduPhysical> util = new ExcelUtil<EduPhysical>(EduPhysical.class);
        return util.exportExcel(list, "physical");
    }

    /**
     * 新增库存数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存数据
     */
    @RequiresPermissions("system:physical:add")
    @Log(title = "库存数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduPhysical eduPhysical, HttpServletRequest request)
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
        eduPhysical.setBatchId(userId);
        return toAjax(eduPhysicalService.insertEduPhysical(eduPhysical));
    }

    /**
     * 修改库存数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EduPhysical eduPhysical = eduPhysicalService.selectEduPhysicalById(id);
        mmap.put("eduPhysical", eduPhysical);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存数据
     */
    @RequiresPermissions("system:physical:edit")
    @Log(title = "库存数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduPhysical eduPhysical, HttpServletRequest request)
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
        eduPhysical.setBatchId(userId);
        return toAjax(eduPhysicalService.updateEduPhysical(eduPhysical));
    }

    /**
     * 删除库存数据
     */
    @RequiresPermissions("system:physical:remove")
    @Log(title = "库存数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eduPhysicalService.deleteEduPhysicalByIds(ids));
    }

    @RequiresPermissions("system:physical:clean")
    @Log(title = "库存数据", businessType = BusinessType.DELETE)
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
        return toAjax(eduPhysicalService.deleteEduPhysicalByUserId(userId));
    }

    @Log(title = "库存数据导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:physical:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, HttpServletRequest request) throws Exception
    {
        ExcelUtil<EduPhysical> util = new ExcelUtil<>(EduPhysical.class);
        List<EduPhysical> userList = util.importExcel(file.getInputStream());
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        String message = eduPhysicalService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:physical:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<EduPhysical> util = new ExcelUtil<EduPhysical>(EduPhysical.class);
        return util.importTemplateExcel("库存数据");
    }
}
