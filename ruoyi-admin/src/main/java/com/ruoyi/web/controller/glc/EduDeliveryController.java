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
import com.ruoyi.network.domain.EduDelivery;
import com.ruoyi.network.service.IEduDeliveryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/delivery")
public class EduDeliveryController extends BaseController
{
    private String prefix = "system/delivery";

    @Autowired
    private IEduDeliveryService eduDeliveryService;

    @RequiresPermissions("system:delivery:view")
    @GetMapping()
    public String delivery()
    {
        return prefix + "/delivery";
    }

    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:delivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduDelivery eduDelivery, HttpServletRequest request)
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
        eduDelivery.setBatchId(userId);
        List<EduDelivery> list = eduDeliveryService.selectEduDeliveryList(eduDelivery);
        return getDataTable(list);
    }

    /**
     * 导出出库单列表
     */
    @RequiresPermissions("system:delivery:export")
    @Log(title = "出库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EduDelivery eduDelivery,HttpServletRequest request)
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
        eduDelivery.setBatchId(userId);
        List<EduDelivery> list = eduDeliveryService.selectEduDeliveryList(eduDelivery);
        ExcelUtil<EduDelivery> util = new ExcelUtil<EduDelivery>(EduDelivery.class);
        return util.exportExcel(list, "delivery");
    }

    /**
     * 新增出库单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存出库单
     */
    @RequiresPermissions("system:delivery:add")
    @Log(title = "出库单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduDelivery eduDelivery,HttpServletRequest request)
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
        eduDelivery.setBatchId(userId);
        return toAjax(eduDeliveryService.insertEduDelivery(eduDelivery));
    }

    /**
     * 修改出库单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {

        EduDelivery eduDelivery = eduDeliveryService.selectEduDeliveryById(id);
        mmap.put("eduDelivery", eduDelivery);
        return prefix + "/edit";
    }

    /**
     * 修改保存出库单
     */
    @RequiresPermissions("system:delivery:edit")
    @Log(title = "出库单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduDelivery eduDelivery,HttpServletRequest request)
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
        eduDelivery.setBatchId(userId);
        return toAjax(eduDeliveryService.updateEduDelivery(eduDelivery));
    }

    /**
     * 删除某条数据
     */
    @RequiresPermissions("system:delivery:remove")
    @Log(title = "出库单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eduDeliveryService.deleteEduDeliveryByIds(ids));
    }

    /**
     * 删除所有数据
     */
    @RequiresPermissions("system:delivery:clean")
    @Log(title = "出库单", businessType = BusinessType.CLEAN)
    @PostMapping( "/clean")
    @ResponseBody
    public AjaxResult removeAll(HttpServletRequest request)
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


        return toAjax(eduDeliveryService.deleteEduDeliveryByUserId(userId));
    }


    @Log(title = "出库导入", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:delivery:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport,HttpServletRequest request) throws Exception
    {
        ExcelUtil<EduDelivery> util = new ExcelUtil<>(EduDelivery.class);
        List<EduDelivery> userList = util.importExcel(file.getInputStream());
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }

        String message = eduDeliveryService.importExcel(userList, updateSupport, userId);
        return AjaxResult.success(message);
    }
    @RequiresPermissions("system:delivery:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate(HttpServletRequest request)
    {
        ExcelUtil<EduDelivery> util = new ExcelUtil<EduDelivery>(EduDelivery.class);
        return util.importTemplateExcel("出库数据");
    }
}
