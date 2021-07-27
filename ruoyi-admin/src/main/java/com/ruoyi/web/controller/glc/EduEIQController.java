package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduEIQ;
import com.ruoyi.system.service.IGlcDeliveryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/eiq")
public class EduEIQController extends BaseController
{
    private String prefix = "system/eiq";

    @Autowired
    private IGlcDeliveryService glcDeliveryService;

    @RequiresPermissions("system:eiq:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/eiq";
    }

    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/i")
    @ResponseBody
    public  TableDataInfo list(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderI(userId);
        return getDataTable(list);
    }
    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/ei")
    @ResponseBody
    public  TableDataInfo ei(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderEI(userId);
        return getDataTable(list);
    }

    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/e")
    @ResponseBody
    public  TableDataInfo e(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderE(userId);
        return getDataTable(list);
    }
    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/n")
    @ResponseBody
    public  TableDataInfo n(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderN(userId);
        return getDataTable(list);
    }
    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/en")
    @ResponseBody
    public  TableDataInfo en(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderEN(userId);
        return getDataTable(list);
    }
    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/q")
    @ResponseBody
    public  TableDataInfo q(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderQ(userId);
        return getDataTable(list);
    }
    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/eq")
    @ResponseBody
    public  TableDataInfo eq(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderEQ(userId);
        return getDataTable(list);
    }

    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/ik")
    @ResponseBody
    public  TableDataInfo ik(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderIK(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:eiq:list")
    @PostMapping("/eiq/iq")
    @ResponseBody
    public  TableDataInfo iq(HttpServletRequest request)
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
        List<EduEIQ> list = glcDeliveryService.getEIQOrderIQ(userId);
        return getDataTable(list);
    }
}
