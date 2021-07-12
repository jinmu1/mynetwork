package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.quartz.util.EOQUtil;
import com.ruoyi.system.domain.EduABC;
import com.ruoyi.system.domain.EduEOQ;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/eoq")
public class EduEOQController extends BaseController
{
    private String prefix = "system/eoq";

    @Autowired
    private IGlcDeliveryService glcDeliveryService;

    @RequiresPermissions("system:eoq:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/eoq";
    }

    @RequiresPermissions("system:eoq:list")
    @PostMapping("/eoq/bar1")
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
        List<EduABC> list = glcDeliveryService.getEOQCount(userId);
        return getDataTable(list);
    }
    @RequiresPermissions("system:eoq:list")
    @PostMapping("/eoq/basic/bar3")
    @ResponseBody
    public  TableDataInfo basicbar1(HttpServletRequest httpRequest)
    {
        String Single_order_cost = httpRequest.getParameter("cost");
        String total_delivery = httpRequest.getParameter("total");
        String stock_price = httpRequest.getParameter("price");
        String unit_storage_cost = httpRequest.getParameter("unitCost");
        String EOQ = EOQUtil.getEOQ1(unit_storage_cost,Single_order_cost,total_delivery);
        String total_cost1 = EOQUtil.getTotal_cost(unit_storage_cost,Single_order_cost,stock_price,total_delivery,EOQ);
        String frequency = EOQUtil.getN1(total_delivery,EOQ);
        String cycle = EOQUtil.getCycle(frequency);
        EduEOQ eduEOQ =new EduEOQ();
        eduEOQ.setEOQ(EOQ);
        eduEOQ.setTotal_cost(total_cost1);
        eduEOQ.setFrequency(frequency);
        eduEOQ.setCycle(cycle);
        ArrayList<EduEOQ> list = new ArrayList<>();
        list.add(eduEOQ);
        return getDataTable(list);
    }
    @RequiresPermissions("system:eoq:list")
    @PostMapping("/eoq/noPrice/bar3")
    @ResponseBody
    public  TableDataInfo noPrice(HttpServletRequest httpRequest)
    {
        String Single_order_cost = httpRequest.getParameter("cost");
        String total_delivery = httpRequest.getParameter("total");
        String stock_price = httpRequest.getParameter("price");
        String unit_storage_cost = httpRequest.getParameter("unitCost");
        String discount_factor1 = httpRequest.getParameter("m1");
        String discount_factor2 = httpRequest.getParameter("m2");
        String discount_point1 = httpRequest.getParameter("n1");
        String discount_point2 = httpRequest.getParameter("n2");
        String EOQ = EOQUtil.getEOQ1(unit_storage_cost,Single_order_cost,total_delivery);
        String EOQ1 =null;
        String EOQ2 = null;
        if (Double.parseDouble(discount_point1)<Double.parseDouble(discount_point2)){
            EOQ1 = (new BigDecimal(total_delivery)).multiply(new BigDecimal(discount_point1)).setScale(0,BigDecimal.ROUND_UP).toString();
            EOQ2 = (new BigDecimal(total_delivery)).multiply(new BigDecimal(discount_point2)).setScale(0,BigDecimal.ROUND_UP).toString();
        }else {
            EOQ1 = (new BigDecimal(total_delivery)).multiply(new BigDecimal(discount_point2)).setScale(0,BigDecimal.ROUND_UP).toString();
            EOQ2 = (new BigDecimal(total_delivery)).multiply(new BigDecimal(discount_point1)).setScale(0,BigDecimal.ROUND_UP).toString();
        }
        if (Double.parseDouble(EOQ)<(Double.parseDouble(discount_point1)*Double.parseDouble(total_delivery))){
            stock_price =stock_price;
        }else if ((Double.parseDouble(discount_point1)*Double.parseDouble(total_delivery))<Double.parseDouble(EOQ)&&Double.parseDouble(EOQ)<(Double.parseDouble(discount_point2)*Double.parseDouble(total_delivery))){

            stock_price =(new BigDecimal(discount_factor1)).multiply(new BigDecimal(stock_price)).toString();
        }else if (Double.parseDouble(EOQ)>(Double.parseDouble(discount_point2)*Double.parseDouble(total_delivery))){
            stock_price =(new BigDecimal(discount_factor2)).multiply(new BigDecimal(stock_price)).toString();
        }

        String total_cost = EOQUtil.getTotal_cost(unit_storage_cost,Single_order_cost,stock_price,total_delivery,EOQ);
        String total_cost1 = EOQUtil.getTotal_cost(unit_storage_cost,Single_order_cost,stock_price,total_delivery,EOQ1);
        String total_cost2 = EOQUtil.getTotal_cost(unit_storage_cost,Single_order_cost,stock_price,total_delivery,EOQ2);
        String frequency = EOQUtil.getN1(total_delivery,EOQ);
        String frequency1 = EOQUtil.getN1(total_delivery,EOQ1);
        String frequency2 = EOQUtil.getN1(total_delivery,EOQ2);
        String cycle = EOQUtil.getCycle(frequency);
        String cycle1 = EOQUtil.getCycle(frequency1);
        String cycle2 = EOQUtil.getCycle(frequency2);
        EduEOQ eduEOQ =new EduEOQ();
        eduEOQ.setEOQ(EOQ);
        eduEOQ.setEOQ1(EOQ1);
        eduEOQ.setEOQ2(EOQ2);
        eduEOQ.setTotal_cost(total_cost);
        eduEOQ.setTotal_cost_Q1(total_cost1);
        eduEOQ.setTotal_cost_Q2(total_cost2);
        eduEOQ.setFrequency(frequency);
        eduEOQ.setFrequency1(frequency1);
        eduEOQ.setFrequency2(frequency2);
        eduEOQ.setCycle(cycle);
        eduEOQ.setCycle1(cycle1);
        eduEOQ.setCycle2(cycle2);
        List<EduEOQ> list = new ArrayList<>();
        list.add(eduEOQ);

        return getDataTable(list);
    }
}
