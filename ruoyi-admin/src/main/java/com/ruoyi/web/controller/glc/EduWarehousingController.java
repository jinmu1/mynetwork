package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduDelivery;
import com.ruoyi.system.domain.EduReceiving;
import com.ruoyi.system.service.IEduDeliveryService;
import com.ruoyi.system.service.IEduReceivingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 入库分析
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/warehousing")
public class EduWarehousingController extends BaseController
{
    private String prefix = "system/warehousing";

    @Autowired
    private IEduDeliveryService deliveryService;

    @Autowired
    private IEduReceivingService receivingService;

    @RequiresPermissions("system:warehousing:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/warehousing";
    }

    @RequiresPermissions("system:warehousing:list")
    @PostMapping("/warehousing/bar1")
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
        List<EduReceiving> list = receivingService.getReceivingTime(userId);
        return getDataTable(list);
    }

    @RequiresPermissions("system:warehousing:list")
    @PostMapping("/warehousing/bar2")
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
        List<EduReceiving> list = receivingService.getReceiving(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:warehousing:list")
    @PostMapping("/warehousing/bar3")
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
        HashMap<String,Object> pm = new HashMap<String, Object>();
        pm.put("batch_id",userId);

        List<EduReceiving> list = receivingService.getReceivingCar(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:warehousing:list")
    @PostMapping("/warehousing/bar4")
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
        HashMap<String,Object> pm = new HashMap<String, Object>();
        pm.put("batch_id",userId);
        List<EduReceiving> list = receivingService.getReceivingCar1(userId);
        return getDataTable(list);
    }
}
