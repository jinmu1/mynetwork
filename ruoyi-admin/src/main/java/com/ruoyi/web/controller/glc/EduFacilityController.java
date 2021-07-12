package com.ruoyi.web.controller.glc;

import java.util.List;

import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.system.domain.EduDelivery;
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
import com.ruoyi.system.domain.EduFacility;
import com.ruoyi.system.service.IEduFacilityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 设施数据Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/facility")
public class EduFacilityController extends BaseController
{
    private String prefix = "system/facility";

    @Autowired
    private IEduFacilityService eduFacilityService;

    @RequiresPermissions("system:facility:view")
    @GetMapping()
    public String facility()
    {
        return prefix + "/facility";
    }

    /**
     * 查询设施数据列表
     */
    @RequiresPermissions("system:facility:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduFacility eduFacility, HttpServletRequest request)
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
        eduFacility.setBatchId(userId);

        List<EduFacility> list = eduFacilityService.selectEduFacilityList(eduFacility);
        return getDataTable(list);
    }

    /**
     * 导出设施数据列表
     */
    @RequiresPermissions("system:facility:export")
    @Log(title = "设施数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduFacility eduFacility)
    {
        List<EduFacility> list = eduFacilityService.selectEduFacilityList(eduFacility);
        ExcelUtil<EduFacility> util = new ExcelUtil<EduFacility>(EduFacility.class);
        return util.exportExcel(list, "facility");
    }

    /**
     * 新增设施数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存设施数据
     */
    @RequiresPermissions("system:facility:add")
    @Log(title = "设施数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduFacility eduFacility)
    {
        return toAjax(eduFacilityService.insertEduFacility(eduFacility));
    }

    /**
     * 修改设施数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EduFacility eduFacility = eduFacilityService.selectEduFacilityById(id);
        mmap.put("eduFacility", eduFacility);
        return prefix + "/edit";
    }

    /**
     * 修改保存设施数据
     */
    @RequiresPermissions("system:facility:edit")
    @Log(title = "设施数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduFacility eduFacility)
    {
        return toAjax(eduFacilityService.updateEduFacility(eduFacility));
    }

    /**
     * 删除设施数据
     */
    @RequiresPermissions("system:facility:remove")
@Log(title = "设施数据", businessType = BusinessType.DELETE)
@PostMapping( "/remove")
@ResponseBody
public AjaxResult remove(String ids)
{
    return toAjax(eduFacilityService.deleteEduFacilityByIds(ids));
}

    @RequiresPermissions("system:facility:clean")
    @Log(title = "设施数据", businessType = BusinessType.CLEAN)
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
        return toAjax(eduFacilityService.deleteEduFacilityByUserId(userId));
    }

    @Log(title = "设施导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:facility:import")
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
        ExcelUtil<EduFacility> util = new ExcelUtil<>(EduFacility.class);
        List<EduFacility> userList = util.importExcel(file.getInputStream());
        String message = eduFacilityService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:facility:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<EduFacility> util = new ExcelUtil<EduFacility>(EduFacility.class);
        return util.importTemplateExcel("设施数据");
    }
}
