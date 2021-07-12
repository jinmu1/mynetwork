package com.ruoyi.web.controller.glc;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.utils.ShiroUtils;
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
import com.ruoyi.system.domain.EduReport;
import com.ruoyi.system.service.IEduReportService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-01-22
 */
@Controller
@RequestMapping("/system/report")
public class EduReportController extends BaseController
{
    private String prefix = "system/report";

    @Autowired
    private IEduReportService eduReportService;

    @RequiresPermissions("system:report:view")
    @GetMapping()
    public String report()
    {
        return prefix + "/report";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:report:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduReport eduReport)
    {
        startPage();
        List<EduReport> list = eduReportService.selectEduReportList(eduReport);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:report:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduReport eduReport)
    {
        List<EduReport> list = eduReportService.selectEduReportList(eduReport);
        ExcelUtil<EduReport> util = new ExcelUtil<EduReport>(EduReport.class);
        return util.exportExcel(list, "report");
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
    @RequiresPermissions("system:report:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduReport eduReport)
    {
        return toAjax(eduReportService.insertEduReport(eduReport));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EduReport eduReport = eduReportService.selectEduReportById(id);
        mmap.put("eduReport", eduReport);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:report:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduReport eduReport)
    {
        return toAjax(eduReportService.updateEduReport(eduReport));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:report:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eduReportService.deleteEduReportByIds(ids));
    }


    @RequiresPermissions("system:report:list")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/getReport")
    @ResponseBody
    public TableDataInfo getReport(HttpServletRequest request)
    {
        startPage();
        String batchId = "";
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    batchId = cookie.getValue();
                }
            }
        }
        String title =  request.getParameter("title");
        HashMap<String,String> map = new HashMap<>();
        map.put("batchId",batchId);
        map.put("title",title);

        List<EduReport> list = eduReportService.selectEduReportByBatchId(map);
        return getDataTable(list);
    }
    @RequiresPermissions("system:report:list")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/insertData")
    @ResponseBody
    public AjaxResult insertData(HttpServletRequest request)
    {
        startPage();
        String batchId = "";
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    batchId = cookie.getValue();
                }
            }
        }
        HashMap<String, Object> pm = new HashMap<String, Object>();
        pm.put("batchId",batchId);
        pm.put("userId", ShiroUtils.getSysUser().getUserId());
        pm.put("title",request.getParameter("title"));
        pm.put("img",request.getParameter("img"));
        pm.put("type",request.getParameter("type"));
        pm.put("classes",request.getParameter("classes"));
        pm.put("feature",request.getParameter("feature"));
        pm.put("feature_property1",request.getParameter("feature_property1"));
        pm.put("feature_property2",request.getParameter("feature_property2"));
        pm.put("feature_property3",request.getParameter("feature_property3"));
        pm.put("feature_property4",request.getParameter("feature_property4"));
        pm.put("issue",request.getParameter("issue"));
        pm.put("issue_property1",request.getParameter("issue_property1"));
        pm.put("issue_property2",request.getParameter("issue_property2"));
        pm.put("issue_property3",request.getParameter("issue_property3"));
        pm.put("issue_property4",request.getParameter("issue_property4"));
        pm.put("variable",request.getParameter("variable"));
        pm.put("advice",request.getParameter("advice"));
        pm.put("advice_property1",request.getParameter("advice_property1"));
        pm.put("advice_property2",request.getParameter("advice_property2"));
        pm.put("advice_property3",request.getParameter("advice_property3"));
        pm.put("advice_property4",request.getParameter("advice_property4"));
        pm.put("strategy",request.getParameter("strategy"));
        pm.put("strategy_property1",request.getParameter("strategy_property1"));
        pm.put("strategy_property2",request.getParameter("strategy_property2"));
        pm.put("strategy_property3",request.getParameter("strategy_property3"));
        pm.put("strategy_property4",request.getParameter("strategy_property4"));
        EduReport  eduReport =  eduReportService.getReportByTitle(pm);
        if (eduReport==null) {
            eduReportService.inserReport(pm);
        }else {
            eduReportService.updateReport(pm);
        }
        return AjaxResult.success();
    }
}
