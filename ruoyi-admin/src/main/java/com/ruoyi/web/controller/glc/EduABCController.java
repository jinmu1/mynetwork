package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduABC;
import com.ruoyi.system.service.IGlcDeliveryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/abc")
public class EduABCController extends BaseController
{
    private String prefix = "system/abc";

    @Autowired
    private IGlcDeliveryService glcDeliveryService;

    @RequiresPermissions("system:abc:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/abc";
    }

    /**
     * 查询出库单列表
     */
    @RequiresPermissions("system:abc:list")
    @PostMapping("/abc/bar1")
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
        List<EduABC> list = glcDeliveryService.getABCCount(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:abc:list")
    @PostMapping("/abc/bar2")
    @ResponseBody
    public  TableDataInfo bar2()
    {
        startPage();
        List<EduABC> eduABCS =new ArrayList<EduABC>();
        EduABC eduABC = new EduABC();
        eduABC.setGoods_name("检查");
        eduABC.setDelivery_quantity("经常检查");
        eduABC.setAccumulate_quantity("一般检查");
        eduABC.setPercentage("按月或季度检查");
        eduABCS.add(eduABC);
        EduABC eduABC1 = new EduABC();
        eduABC1.setGoods_name("统计");
        eduABC1.setDelivery_quantity("详细统计");
        eduABC1.setAccumulate_quantity("一般统计");
        eduABC1.setPercentage("按总金额统计");
        eduABCS.add(eduABC1);
        EduABC eduABC2 = new EduABC();
        eduABC2.setGoods_name("控制");
        eduABC2.setDelivery_quantity("严格控制");
        eduABC2.setAccumulate_quantity("一般控制");
        eduABC2.setPercentage("按总金额控制");
        eduABCS.add(eduABC2);
        EduABC eduABC3 = new EduABC();
        eduABC3.setGoods_name("安全库存");
        eduABC3.setDelivery_quantity("较小");
        eduABC3.setAccumulate_quantity("较大");
        eduABC3.setPercentage("允许较大");
        eduABCS.add(eduABC3);
        return getDataTable(eduABCS);
    }
    @RequiresPermissions("system:abc:list")
    @PostMapping("/abc/bar3")
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
        List<EduABC> list = glcDeliveryService.getABCCount1(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:abc:list")
    @PostMapping("/abc/bar4")
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
        List<EduABC> list = glcDeliveryService.getGoodsNameList(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:abc:list")
    @PostMapping("/abc/bar5")
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
        List<EduABC> list = glcDeliveryService.getABCCount2(userId);
        return getDataTable(list);
    }
}
