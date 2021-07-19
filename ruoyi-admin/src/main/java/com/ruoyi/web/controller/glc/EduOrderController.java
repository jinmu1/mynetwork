package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduEIQ;
import com.ruoyi.system.domain.EduReceiving;
import com.ruoyi.system.domain.MathCount;
import com.ruoyi.network.service.IEduReceivingService;
import com.ruoyi.network.service.IGlcDeliveryService;
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
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/order")
public class EduOrderController extends BaseController
{
    private String prefix = "system/order";

    @Autowired
    private IGlcDeliveryService deliveryService;

    @Autowired
    private IEduReceivingService receivingService;

    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/order";
    }

    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar1")
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
    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar2")
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
        List<EduReceiving> list = receivingService.getEveryTime(userId);
        return getDataTable(list);
    }

    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar3")
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
        List<EduEIQ> list = deliveryService.getEIQOrderE1(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar4")
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
        List<EduEIQ> list = deliveryService.getEIQOrderEN1(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar5")
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
        List<EduEIQ> list = deliveryService.getEIQOrderEN2(userId);
        return getDataTable(list);
    }

    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar6")
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
        List<EduEIQ> list = deliveryService.getEIQOrderENHist(userId);
        return getDataTable(list);
    }

    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar7")
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
        List<EduEIQ> list = deliveryService.getEIQOrderEQ1(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar8")
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
        List<EduEIQ> list = deliveryService.getEIQOrderEQHist(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar9")
    @ResponseBody
    public  TableDataInfo bar9( HttpServletRequest request)
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
        List<EduEIQ> list = deliveryService.getCustomerOrder(userId);
        return getDataTable(list);
    }

    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar10")
    @ResponseBody
    public  MathCount bar10( HttpServletRequest request)
    {
        startPage();
        String userId;
        userId = "";
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId =cookie.getValue();
                }
            }
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("action",request.getParameter("action"));
        MathCount list = deliveryService.getENS(map);
        return list;
    }

    @RequiresPermissions("system:order:list")
    @PostMapping("/order/bar11")
    @ResponseBody
    public  MathCount bar11( HttpServletRequest request)
    {
        startPage();
        String userId;
        userId = "";
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId =cookie.getValue();
                }
            }
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("action",request.getParameter("action"));
        MathCount list = deliveryService.getEQS(map);
        return list;
    }
}
