package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduDelivery;
import com.ruoyi.network.service.IEduDeliveryService;
import com.ruoyi.network.service.IEduReceivingService;
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
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/customer")
public class EduCustomerController extends BaseController
{
    private String prefix = "system/customer";

    @Autowired
    private IEduDeliveryService deliveryService;

    @Autowired
    private IEduReceivingService receivingService;

    @RequiresPermissions("system:customer:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/customer";
    }

    @RequiresPermissions("system:customer:list")
    @PostMapping("/customer/bar1")
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
        List<EduDelivery> list = deliveryService.getCustomers(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:customer:list")
    @PostMapping("/customer/bar2")
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
        List<EduDelivery> list = deliveryService.customersType(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:customer:list")
    @PostMapping("/customer/bar3")
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
        List<EduDelivery> list = deliveryService.getCustomersGoods(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:customer:list")
    @PostMapping("/customer/bar4")
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
        List<EduDelivery> list = deliveryService.getCustomersOrders(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:customer:list")
    @PostMapping("/customer/bar5")
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
