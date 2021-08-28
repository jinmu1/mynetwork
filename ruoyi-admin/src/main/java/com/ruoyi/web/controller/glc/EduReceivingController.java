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
import com.ruoyi.system.domain.EduReceiving;
import com.ruoyi.system.service.IEduReceivingService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 入库Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/receiving")
public class EduReceivingController extends BaseController
{
    private String prefix = "system/receiving";

    @Autowired
    private IEduReceivingService eduReceivingService;

    @RequiresPermissions("system:receiving:view")
    @GetMapping()
    public String receiving()
    {
        return prefix + "/receiving";
    }

    /**
     * 查询入库列表
     */
    @RequiresPermissions("system:receiving:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduReceiving eduReceiving, HttpServletRequest request)
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
        eduReceiving.setBatchId(userId);
        List<EduReceiving> list = eduReceivingService.selectEduReceivingList(eduReceiving);

        return getDataTable(list);
    }
    @Log(title = "入库导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:receiving:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, HttpServletRequest request) throws Exception
    {
        ExcelUtil<EduReceiving> util = new ExcelUtil<>(EduReceiving.class);
        List<EduReceiving> userList = util.importExcel(file.getInputStream());
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        String message = eduReceivingService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    /**
     * 导出入库列表
     */
    @RequiresPermissions("system:receiving:export")
    @Log(title = "入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduReceiving eduReceiving, HttpServletRequest request)
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
        eduReceiving.setBatchId(userId);
        List<EduReceiving> list = eduReceivingService.selectEduReceivingList(eduReceiving);
        ExcelUtil<EduReceiving> util = new ExcelUtil<EduReceiving>(EduReceiving.class);
        return util.exportExcel(list, "receiving");
    }

    /**
     * 新增入库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存入库
     */
    @RequiresPermissions("system:receiving:add")
    @Log(title = "入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduReceiving eduReceiving)
    {
        return toAjax(eduReceivingService.insertEduReceiving(eduReceiving));
    }

    /**
     * 修改入库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EduReceiving eduReceiving = eduReceivingService.selectEduReceivingById(id);
        mmap.put("eduReceiving", eduReceiving);
        return prefix + "/edit";
    }

    /**
     * 修改保存入库
     */
    @RequiresPermissions("system:receiving:edit")
    @Log(title = "入库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduReceiving eduReceiving)
    {
        return toAjax(eduReceivingService.updateEduReceiving(eduReceiving));
    }
    @RequiresPermissions("system:receiving:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<EduReceiving> util = new ExcelUtil<EduReceiving>(EduReceiving.class);
        return util.importTemplateExcel("入库数据");
    }
    /**
     * 删除入库
     */
    @RequiresPermissions("system:receiving:remove")
    @Log(title = "入库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eduReceivingService.deleteEduReceivingByIds(ids));
    }

    @RequiresPermissions("system:receiving:clean")
    @Log(title = "入库", businessType = BusinessType.CLEAN)
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

        return toAjax(eduReceivingService.deleteEduReceivingByUserId(userId));
    }
}
