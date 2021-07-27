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
import com.ruoyi.system.domain.EduEquipment;
import com.ruoyi.system.service.IEduEquipmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 设备数据Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/equipment")
public class EduEquipmentController extends BaseController
{
    private String prefix = "system/equipment";

    @Autowired
    private IEduEquipmentService eduEquipmentService;

    @RequiresPermissions("system:equipment:view")
    @GetMapping()
    public String equipment()
    {
        return prefix + "/equipment";
    }

    /**
     * 查询设备数据列表
     */
    @RequiresPermissions("system:equipment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduEquipment eduEquipment, HttpServletRequest request)
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
        eduEquipment.setBatchId(userId);
        List<EduEquipment> list = eduEquipmentService.selectEduEquipmentList(eduEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备数据列表
     */
    @RequiresPermissions("system:equipment:export")
    @Log(title = "设备数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduEquipment eduEquipment)
    {
        List<EduEquipment> list = eduEquipmentService.selectEduEquipmentList(eduEquipment);
        ExcelUtil<EduEquipment> util = new ExcelUtil<EduEquipment>(EduEquipment.class);
        return util.exportExcel(list, "equipment");
    }

    /**
     * 新增设备数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存设备数据
     */
    @RequiresPermissions("system:equipment:add")
    @Log(title = "设备数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduEquipment eduEquipment)
    {
        return toAjax(eduEquipmentService.insertEduEquipment(eduEquipment));
    }

    /**
     * 修改设备数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EduEquipment eduEquipment = eduEquipmentService.selectEduEquipmentById(id);
        mmap.put("eduEquipment", eduEquipment);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备数据
     */
    @RequiresPermissions("system:equipment:edit")
    @Log(title = "设备数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduEquipment eduEquipment)
    {
        return toAjax(eduEquipmentService.updateEduEquipment(eduEquipment));
    }

    /**
     * 删除设备数据
     */
    @RequiresPermissions("system:equipment:remove")
    @Log(title = "设备数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eduEquipmentService.deleteEduEquipmentByIds(ids));
    }
    @RequiresPermissions("system:equipment:clean")
    @Log(title = "设备数据", businessType = BusinessType.CLEAN)
    @PostMapping( "/clean")
    @ResponseBody
    public AjaxResult clean(HttpServletRequest request)
    {     Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        return toAjax(eduEquipmentService.deleteEduEquipmentByUserId(userId));
    }

    @Log(title = "设备数据导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:equipment:import")
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
        ExcelUtil<EduEquipment> util = new ExcelUtil<>(EduEquipment.class);
        List<EduEquipment> userList = util.importExcel(file.getInputStream());
        String message = eduEquipmentService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:equipment:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<EduEquipment> util = new ExcelUtil<EduEquipment>(EduEquipment.class);
        return util.importTemplateExcel("设备数据");
    }
}
