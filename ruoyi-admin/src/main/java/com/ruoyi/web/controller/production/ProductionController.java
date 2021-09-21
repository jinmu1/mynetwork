package com.ruoyi.web.controller.production;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.network.algorithm.kmeans.Cluster;
import com.ruoyi.network.form.GlcPoint;
import com.ruoyi.network.node.*;
import com.ruoyi.network.result.Result;
import com.ruoyi.network.result.ResultMsg;
import com.ruoyi.network.utils.NetWorkPlanUtils;
import com.ruoyi.network.utils.NetworkUtils;
import com.ruoyi.system.service.IGlcPointService;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2021-06-24
 */
@Controller
@RequestMapping("/system/production")
public class ProductionController extends BaseController {
    @Autowired
    private IGlcPointService glcPointService;

    @PostMapping("/production")
    @ResponseBody
    public TableDataInfo production(HttpServletRequest req) {

        int num1 = Integer.parseInt(req.getParameter("num"));
        String provinces = req.getParameter("provinces");
        String carLength = req.getParameter("carLength");

        double transportNum = 80000;
        if (req.getParameter("transportNum") != null && !req.getParameter("transportNum").equals("")) {
            transportNum = Double.parseDouble(req.getParameter("transportNum"));
        }
        double warehousing = 80000;
        if (req.getParameter("warehousing") != null && !req.getParameter("warehousing").equals("")) {
            warehousing = Double.parseDouble(req.getParameter("warehousing"));
        }
        double h_price = 15;
        if (req.getParameter("h_price") != null && !req.getParameter("h_price").equals("")) {
            h_price = Double.parseDouble(req.getParameter("h_price"));
        }
        double m_price = 15;
        if (req.getParameter("m_price") != null && !req.getParameter("m_price").equals("")) {
            m_price = Double.parseDouble(req.getParameter("m_price"));
        }
        double l_price = 60;
        if (req.getParameter("s_price") != null && !req.getParameter("s_price").equals("")) {
            l_price = Double.parseDouble(req.getParameter("s_price"));
        }
        double inventory_loss = 0.5;
        if (req.getParameter("inventory_loss") != null && !req.getParameter("inventory_loss").equals("")) {
            inventory_loss = Double.parseDouble(req.getParameter("inventory_loss"));
        }
        double managementFee = 0;
        if (req.getParameter("managementFee") != null && !req.getParameter("managementFee").equals("")) {
            managementFee = Double.parseDouble(req.getParameter("managementFee"));
        }
        double times = 72;
        if (req.getParameter("times") != null && !req.getParameter("times").equals("")) {
            times = Integer.parseInt(req.getParameter("times"));
        }
        double order = 95;
        if (req.getParameter("order") != null && !req.getParameter("order").equals("")) {
            order = Integer.parseInt(req.getParameter("order"));
        }
        double goods_num = 6000;
        if (req.getParameter("goods_num") != null && !req.getParameter("goods_num").equals("")) {
            goods_num = Integer.parseInt(req.getParameter("goods_num"));
        }
        double b_goods_size = 20;
        if (req.getParameter("b_goods_size") != null && !req.getParameter("b_goods_size").equals("")) {
            b_goods_size = Double.parseDouble(req.getParameter("b_goods_size"));
        }
        double m_goods_size = 20;
        if (req.getParameter("m_goods_size") != null && !req.getParameter("m_goods_size").equals("")) {
            m_goods_size = Double.parseDouble(req.getParameter("m_goods_size"));
        }
        double s_goods_size = 60;
        if (req.getParameter("s_goods_size") != null && !req.getParameter("s_goods_size").equals("")) {
            s_goods_size = Double.parseDouble(req.getParameter("s_goods_size"));
        }
        int emp_quantity = 50;//一天处理50托

        List<ResultMsg> resultMsgs = new ArrayList<>();
        List<JSONObject> listd;
        List<GlcPoint> cityList = glcPointService.selectGlcPointList(new GlcPoint(provinces));  //获取省份内城市
        List<City> points = new ArrayList<>();
        for (GlcPoint glcPoint : cityList) {//需求点
            points.add(new City(glcPoint.getCity(), glcPoint.getLat(), glcPoint.getLng(), glcPoint.getGdp()));//备选点
        }
        List<Material> list = NetworkUtils.createGoods(goods_num);
        List<Supplier> suppliers = NetworkUtils.initSupplier(list.size(), cityList);
        list = NetworkUtils.initMaterialPrice(list, h_price, m_price, l_price);
        list = NetworkUtils.initMaterialVolume(list, b_goods_size, m_goods_size, s_goods_size);
        list = NetworkUtils.initMaterialSupplier(suppliers, list);
        List<Order> orderList = NetworkUtils.initOrders(list, transportNum);
        List<Customer> customerList = NetworkUtils.initCustomer(cityList, 200);

        orderList = NetworkUtils.initOrdersCustomerList(orderList, customerList);
        Map<City, List<Order>> outOrdersList = orderList.stream().collect(Collectors.groupingBy(Order::getCustomerCity));//出库单


       return null;
    }


}
