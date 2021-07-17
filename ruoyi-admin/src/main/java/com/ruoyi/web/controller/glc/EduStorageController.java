package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.network.domain.EduReplenishment;
import com.ruoyi.network.service.IEduDeliveryService;
import com.ruoyi.network.service.IEduReceivingService;
import com.ruoyi.network.service.IEduReplenishmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 存储分析
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/storage")
public class EduStorageController extends BaseController
{
    private String prefix = "system/storage";

    @Autowired
    private IEduDeliveryService deliveryService;

    @Autowired
    private IEduReceivingService receivingService;
    @Autowired
    private IEduReplenishmentService eduReplenishmentService;
    @RequiresPermissions("system:warehousing:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/storage";
    }

    @RequiresPermissions("system:storage:list")
    @PostMapping("/storage/bar1")
    @ResponseBody
    public  TableDataInfo list( HttpServletRequest request)
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
        List<EduReplenishment> list = eduReplenishmentService.getTime(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:storage:list")
    @PostMapping("/storage/bar2")
    @ResponseBody
    public  TableDataInfo bar2( HttpServletRequest request)
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
        List<EduReplenishment> list = eduReplenishmentService.getDays(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:storage:list")
    @PostMapping("/storage/bar3")
    @ResponseBody
    public  TableDataInfo bar3( HttpServletRequest request)
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
        List<EduReplenishment> list = eduReplenishmentService.getGoodsNum(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:storage:list")
    @PostMapping("/storage/bar4")
    @ResponseBody
    public  TableDataInfo bar4( HttpServletRequest request)
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
        List<EduReplenishment> list = eduReplenishmentService.getGoodsType(userId);
        return getDataTable(list);
    }
}
