package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.network.domain.EduReceiving;
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
@RequestMapping("/system/basic")
public class EduBasicController extends BaseController
{
    private String prefix = "system/basic";

    @Autowired
    private IEduDeliveryService deliveryService;

    @Autowired
    private IEduReceivingService receivingService;

    @RequiresPermissions("system:basic:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/basic";
    }

    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar1")
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
        List<EduReceiving> list = receivingService.getEveryDays(userId);
        return getDataTable(list);
    }

    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar2")
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
        List<EduReceiving> list = receivingService.getEveryDays1(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar3")
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
        List<EduReceiving> list = receivingService.getShipper(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar4")
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
        List<EduReceiving> list = receivingService.getCustomer(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar5")
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
        List<EduReceiving> list = receivingService.getShip_to_party(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar6")
    @ResponseBody
    public  TableDataInfo bar6( HttpServletRequest request)
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
        List<EduReceiving> list = receivingService.getGoods_num(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar7")
    @ResponseBody
    public  TableDataInfo bar7( HttpServletRequest request)
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
        List<EduReceiving> list = receivingService.getGoods_num1(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:basic:list")
    @PostMapping("/basic/bar8")
    @ResponseBody
    public  TableDataInfo bar8( HttpServletRequest request)
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
        List<EduReceiving> list = receivingService.getTatol_num(userId);
        return getDataTable(list);
    }
}
