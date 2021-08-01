package com.ruoyi.web.controller.warehouse;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.network.form.GlcPoint;
import com.ruoyi.network.node.*;
import com.ruoyi.network.utils.NetWorkPlanUtils;
import com.ruoyi.network.utils.NetworkUtils;
import com.ruoyi.system.service.IGlcPointService;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.action.WarehousingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-06-24
 */
@Controller
public class WarehousingController extends BaseController
{
    private static Date runTime = new Date();//当前时间
    private static Date startTime = new Date();//开始时间
    private static Date endTime = new Date();//工作结束时间
    @Autowired
    private IGlcPointService glcPointService;
    @PostMapping("/upload")
    @ResponseBody
    public TableDataInfo  upload(HttpServletRequest req){
        String provinces = req.getParameter("provinces");
        String carLength = req.getParameter("carLength");
        double transportNum = 80000;
        if (req.getParameter("transportNum")!=null&&!req.getParameter("transportNum").equals("")){
            transportNum = Double.parseDouble(req.getParameter("transportNum"));
        }
        double warehousing = 80000;
        if (req.getParameter("warehousing")!=null&&!req.getParameter("warehousing").equals("")) {
            warehousing = Double.parseDouble(req.getParameter("warehousing"));
        }
        double h_price = 15;
        if(req.getParameter("h_price")!=null&&!req.getParameter("h_price").equals("")) {
            h_price = Double.parseDouble(req.getParameter("h_price"));
        }
        double m_price = 15;
        if(req.getParameter("m_price")!=null&&!req.getParameter("m_price").equals("")) {
            m_price = Double.parseDouble(req.getParameter("m_price"));
        }
        double l_price = 60;
        if(req.getParameter("s_price")!=null&&!req.getParameter("s_price").equals("")) {
            l_price = Double.parseDouble(req.getParameter("s_price"));
        }
        double inventory_loss = 0.5;
        if(req.getParameter("inventory_loss")!=null&&!req.getParameter("inventory_loss").equals("")) {
            inventory_loss = Double.parseDouble(req.getParameter("inventory_loss"));
        }
        double managementFee = 0;
        if(req.getParameter("managementFee")!=null&&!req.getParameter("managementFee").equals("")) {
            managementFee = Double.parseDouble(req.getParameter("managementFee"));
        }
        double times = 72;
        if(req.getParameter("times")!=null&&!req.getParameter("times").equals("")) {
            times = Integer.parseInt(req.getParameter("times"));
        }
        double order = 95;
        if(req.getParameter("order")!=null&&!req.getParameter("order").equals("")) {
            order = Integer.parseInt(req.getParameter("order"));
        }
        double goods_num = 6000;
        if(req.getParameter("goods_num")!=null&&!req.getParameter("goods_num").equals("")) {
            goods_num = Integer.parseInt(req.getParameter("goods_num"));
        }
        double b_goods_size = 20;
        if(req.getParameter("b_goods_size")!=null&&!req.getParameter("b_goods_size").equals("")) {
            b_goods_size = Double.parseDouble(req.getParameter("b_goods_size"));
        }
        double m_goods_size = 20;
        if(req.getParameter("m_goods_size")!=null&&!req.getParameter("m_goods_size").equals("")) {
            m_goods_size = Double.parseDouble(req.getParameter("m_goods_size"));
        }
        double s_goods_size = 60;
        if(req.getParameter("s_goods_size")!=null&&!req.getParameter("s_goods_size").equals("")) {
            s_goods_size = Double.parseDouble(req.getParameter("s_goods_size"));
        }

        List<GlcPoint> cityList = glcPointService.selectGlcPointList(new GlcPoint(provinces));  //获取省份内城市
        List<Material> list = NetworkUtils.createGoods(goods_num);
        List<Supplier> suppliers = NetworkUtils.initSupplier(list.size(),cityList);
        list = NetworkUtils.initMaterialPrice(list, h_price, m_price, l_price);
        list = NetworkUtils.initMaterialVolume(list, b_goods_size, m_goods_size, s_goods_size);
        list = NetworkUtils.initMaterialSupplier(suppliers,list);
        List<Order> orderList = NetworkUtils.initOrders(list, transportNum);
        List<Customer> customerList = NetworkUtils.initCustomer(cityList, 200);

        orderList = NetworkUtils.initOrdersCustomerList(orderList, customerList);
        WarehousingUtil.initTime(runTime,startTime,endTime);//初始化时间
        List<Platform> platforms = WarehousingUtil.initPlat(10);
        List<Order> inOrder = NetWorkPlanUtils.getReplenishment(orderList,list);
        while (runTime.getTime()<endTime.getTime()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(runTime);
            calendar.add(Calendar.SECOND,1);

            runTime = calendar.getTime();
        }
        return getDataTable(new ArrayList<>());
    }



}
