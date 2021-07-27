package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduABC;
import com.ruoyi.system.domain.EduPCB;
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
@RequestMapping("/system/pcb")
public class EduPCBController extends BaseController
{
    private String prefix = "system/pcb";

    @Autowired
    private IGlcDeliveryService glcDeliveryService;

    @RequiresPermissions("system:pcb:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/pcb";
    }

    @RequiresPermissions("system:pcb:list")
    @PostMapping("/pcb/bar1")
    @ResponseBody
    public  TableDataInfo bar1(HttpServletRequest request)
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
    @RequiresPermissions("system:pcb:list")
    @PostMapping("/pcb/bar2")
    @ResponseBody
    public  TableDataInfo bar2(HttpServletRequest request)
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
        String goods_name = request.getParameter("goods_name");
         EduPCB eduPCB = glcDeliveryService.selectReceivingListByGoodsName(goods_name,userId);
         List<EduPCB> list = new ArrayList<>();
         list.add(eduPCB);
        return getDataTable(list);
    }
    @RequiresPermissions("system:pcb:list")
    @PostMapping("/pcb/bar3")
    @ResponseBody
    public  TableDataInfo bar3(HttpServletRequest httpRequest)
    {
        startPage();
        Long userId = 0L;
        Cookie[]cookies = httpRequest.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        String goods_name =httpRequest.getParameter("goods_name");
        EduPCB eduPCBDemo = new EduPCB();
        eduPCBDemo.setGoods_name(goods_name);
        List<EduPCB> eduPCBDemos1 = glcDeliveryService.selectDeliveryListByGoodsName(eduPCBDemo.getGoods_name(),userId);
//            if (eduPCBDemos1.size()>0&&eduPCBDemos1!=null&&eduPCBDemos1.get(0)!=null&&eduPCBDemos1.get(0).getResult1()!=null) {
//                eduPCBDemo.setResult1(eduPCBDemos1.get(0).getResult1());
//            }else {
//                eduPCBDemo.setResult1("c");
//            }
        if (eduPCBDemos1.size()>0&&eduPCBDemos1!=null&&eduPCBDemos1.get(0)!=null&&eduPCBDemos1.get(0).getResult2()!=null) {
            eduPCBDemo.setResult2(eduPCBDemos1.get(0).getResult2());
        }else {
            eduPCBDemo.setResult2("C");
        }
        List<EduPCB> list = new ArrayList<>();
        list.add(eduPCBDemo);
        return getDataTable(list);
    }
}
