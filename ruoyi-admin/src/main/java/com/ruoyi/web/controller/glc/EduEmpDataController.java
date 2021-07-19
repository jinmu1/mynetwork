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
import com.ruoyi.system.domain.EduEmpData;
import com.ruoyi.network.service.IEduEmpDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 人员数据导入Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/data")
public class EduEmpDataController extends BaseController
{
    private String prefix = "system/data";

    @Autowired
    private IEduEmpDataService eduEmpDataService;

    @RequiresPermissions("system:data:view")
    @GetMapping()
    public String data()
    {
        return prefix + "/data";
    }

    /**
     * 查询人员数据导入列表
     */
    @RequiresPermissions("system:data:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduEmpData eduEmpData, HttpServletRequest request)
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
        eduEmpData.setBatchId(userId);
        List<EduEmpData> list = eduEmpDataService.selectEduEmpDataList(eduEmpData);
        return getDataTable(list);
    }

    /**
     * 导出人员数据导入列表
     */
    @RequiresPermissions("system:data:export")
    @Log(title = "人员数据导入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduEmpData eduEmpData, HttpServletRequest request)
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
        eduEmpData.setBatchId(userId);
        List<EduEmpData> list = eduEmpDataService.selectEduEmpDataList(eduEmpData);
        ExcelUtil<EduEmpData> util = new ExcelUtil<EduEmpData>(EduEmpData.class);
        return util.exportExcel(list, "data");
    }

    /**
     * 新增人员数据导入
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存人员数据导入
     */
    @RequiresPermissions("system:data:add")
    @Log(title = "人员数据导入", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduEmpData eduEmpData)
    {
        return toAjax(eduEmpDataService.insertEduEmpData(eduEmpData));
    }

    /**
     * 修改人员数据导入
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EduEmpData eduEmpData = eduEmpDataService.selectEduEmpDataById(id);
        mmap.put("eduEmpData", eduEmpData);
        return prefix + "/edit";
    }

    /**
     * 修改保存人员数据导入
     */
    @RequiresPermissions("system:data:edit")
    @Log(title = "人员数据导入", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduEmpData eduEmpData)
    {
        return toAjax(eduEmpDataService.updateEduEmpData(eduEmpData));
    }

    /**
     * 删除人员数据导入
     */
    @RequiresPermissions("system:data:remove")
    @Log(title = "人员数据删除", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eduEmpDataService.deleteEduEmpDataByIds(ids));
    }

    /**
     * 删除人员数据导入
     */
    @RequiresPermissions("system:data:clean")
    @Log(title = "人员数据导入", businessType = BusinessType.DELETE)
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
        return toAjax(eduEmpDataService.deleteEduEmpDataByUserId(userId));
    }

    @Log(title = "人员数据导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:data:import")
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
        ExcelUtil<EduEmpData> util = new ExcelUtil<>(EduEmpData.class);
        List<EduEmpData> userList = util.importExcel(file.getInputStream());
        String message = eduEmpDataService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:data:view")
    @Log(title = "运输属性数据导出", businessType = BusinessType.IMPORT)
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<EduEmpData> util = new ExcelUtil<EduEmpData>(EduEmpData.class);
        return util.importTemplateExcel("人员数据");
    }
}
