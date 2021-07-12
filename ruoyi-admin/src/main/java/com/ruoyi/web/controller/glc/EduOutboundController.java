package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduDelivery;
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
import java.math.BigDecimal;
import java.util.List;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/outbound")
public class EduOutboundController extends BaseController
{
    private String prefix = "system/outbound";

    @Autowired
    private IEduDeliveryService deliveryService;

    @Autowired
    private IEduReceivingService receivingService;

    @RequiresPermissions("system:outbound:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/outbound";
    }

    @RequiresPermissions("system:outbound:list")
    @PostMapping("/outbound/bar1")
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
        List<EduDelivery> list = deliveryService.getDeliveryNum(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:outbound:list")
    @PostMapping("/outbound/bar2")
    @ResponseBody
    public  TableDataInfo bar2 (HttpServletRequest request)
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
        List<EduDelivery> list = deliveryService.getDeliveryDay(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:outbound:list")
    @PostMapping("/outbound/bar3")
    @ResponseBody
    public  TableDataInfo bar3 (HttpServletRequest request)
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
        List<EduDelivery> list = deliveryService.getPart(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:outbound:list")
    @PostMapping("/outbound/bar4")
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
        List<EduDelivery> list = deliveryService.getSort(userId);

        return getDataTable(list);
    }
    @RequiresPermissions("system:outbound:list")
    @PostMapping("/outbound/bar5")
    @ResponseBody
    public  TableDataInfo bar5( HttpServletRequest request)
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
        List<EduDelivery> list = deliveryService.getCustomersTimes(userId);
        return getDataTable(list);
    }
}
