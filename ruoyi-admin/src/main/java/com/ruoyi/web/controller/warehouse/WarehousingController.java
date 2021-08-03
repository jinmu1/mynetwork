package com.ruoyi.web.controller.warehouse;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.network.form.GlcPoint;
import com.ruoyi.network.node.*;
import com.ruoyi.network.utils.NetWorkPlanUtils;
import com.ruoyi.network.utils.NetworkUtils;
import com.ruoyi.system.service.IGlcPointService;
import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.process.Delivery;
import com.ruoyi.warehousing.process.Putaway;
import com.ruoyi.warehousing.process.Sorting;
import com.ruoyi.warehousing.process.Upload;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Elevator;
import com.ruoyi.warehousing.resource.facilities.buffer.Tally;
import com.ruoyi.warehousing.resource.facilities.platform.Platform;
import com.ruoyi.warehousing.action.WarehousingUtil;
import com.ruoyi.warehousing.resource.personnel.Emp;
import com.ruoyi.warehousing.result.EmpLog;
import com.ruoyi.warehousing.result.Result;
import com.ruoyi.warehousing.utils.AreaUtils;
import com.ruoyi.warehousing.utils.DateUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2021-06-24
 */
@Controller
public class WarehousingController extends BaseController {
    private static Date runTime = DateUtils.convertString2Date("HH:mm:ss", "08:00:00");//当前时间
    private static Date startTime = new Date();//开始时间
    private static Date endTime = new Date();//工作结束时间
    @Autowired
    private IGlcPointService glcPointService;

    @PostMapping("/getWarehuse")
    @ResponseBody
    public TableDataInfo upload(HttpServletRequest req) {
        String provinces = req.getParameter("provinces");
        String carLength = req.getParameter("carLength");
        double transportNum = 2370;
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

        List<GlcPoint> cityList = glcPointService.selectGlcPointList(new GlcPoint(provinces));  //获取省份内城市
        List<Material> list = NetworkUtils.createGoods(goods_num);
        List<Supplier> suppliers = NetworkUtils.initSupplier(list.size(), cityList);
        list = NetworkUtils.initMaterialPrice(list, h_price, m_price, l_price);
        list = NetworkUtils.initMaterialVolume(list, b_goods_size, m_goods_size, s_goods_size);
        list = NetworkUtils.initMaterialSupplier(suppliers, list);
        List<Order> orderList = NetworkUtils.initOrders(list, transportNum);
        List<Customer> customerList = NetworkUtils.initCustomer(cityList, 200);

        orderList = NetworkUtils.initOrdersCustomerList(orderList, customerList);
        WarehousingUtil.initTime(runTime, startTime, endTime);//初始化时间
        List<Emp> list1 = WarehousingUtil.initEmp(10);//卸货人员
        List<Emp> list2 = WarehousingUtil.initEmp(10);//上架人员、
        List<Emp> list3 = WarehousingUtil.initEmp(10);//分拣人员
        List<Emp> list4 = WarehousingUtil.initEmp(10);//装车人员

        List<Platform> platforms = WarehousingUtil.initPlat(10);
        List<Point> doors = WarehousingUtil.initDoor();
        List<Point> elevatorPark = WarehousingUtil.initElevatorPark();
        List<Order> inOrder = NetWorkPlanUtils.getReplenishment(orderList, list);
        List<Elevator> elevators = WarehousingUtil.initElevator(5, 1);
        List<Cargo> cargos = initCargo(orderList);
        Tally tally = WarehousingUtil.initTally();
        Tally tally1 = WarehousingUtil.initTally1();
        List<Result> listr = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            List<String> batch = DateUtils.getIntervalTimeList("8:00:00", "18:00:00", 600 / i);
            int j = 0;
            List<com.ruoyi.warehousing.queue.Order> outOrderList = new ArrayList<>();

            for (Order order1 : orderList) {
                if (order1.getDeliveryDate().getTime() > DateUtils.convertString2Date("HH:mm:ss", batch.get(j)).getTime()
                        && order1.getDeliveryDate().getTime() < DateUtils.convertString2Date("HH:mm:ss", batch.get(j + 1)).getTime()) {
                    outOrderList.add(new com.ruoyi.warehousing.queue.Order(order1.getOrderCode(),order1.getGoodsCode(),order1.getOrderDate(),order1.getDeliveryDate(),order1.getGoodsNum(),order1.getVolume()));
                }
            }
            Map<Date, List<com.ruoyi.warehousing.queue.Order>> pm = outOrderList.stream().collect(groupingBy(com.ruoyi.warehousing.queue.Order::getCreateDate));
            List<EmpLog>  empLogs1 =new ArrayList<>();
            List<EmpLog>  empLogs2 =new ArrayList<>();
            List<EmpLog>  empLogs3 =new ArrayList<>();
            List<EmpLog>  empLogs4 =new ArrayList<>();

            while (runTime.getTime() < DateUtils.convertString2Date("HH:mm:ss", batch.get(j + 1)).getTime()) {
                List<com.ruoyi.warehousing.queue.Order> orders = pm.get(runTime);
                List<List<com.ruoyi.warehousing.queue.Order>> sprateList = ListUtils.partition(orders, orders.size() / list1.size());
                for (int s = 0; s < list1.size(); s++) {
                    list1.get(s).getOrders().addAll(sprateList.get(s));
                }

                EmpLog empLog1 = Upload.move(list1, platforms, doors, tally);
                EmpLog empLog2 = Putaway.work(list2, tally, tally1, elevatorPark, elevators, cargos);

                EmpLog empLog3 = Sorting.move(list3, cargos, tally1);
                EmpLog empLog4 = Delivery.move(list4, platforms, doors, tally1);


                Calendar calendar = Calendar.getInstance();
                calendar.setTime(runTime);
                calendar.add(Calendar.SECOND, 1);
                runTime = calendar.getTime();
                if (DateUtils.convertDate2String("HH:mm:ss", runTime).equals(batch.get(j))) {
                    j++;
                }
                empLogs1.add(empLog1);
                empLogs2.add(empLog2);
                empLogs3.add(empLog3);
                empLogs4.add(empLog4);
            }
            double empRate = 0.0;
            double areaRate = 0.0;
            for (EmpLog empLog:empLogs1){
                empRate += empLog.getComPlut();
                areaRate += empLog.getAllPlut();
            }
            double empRate1 = 0.0;
            double areaRate1= 0.0;
            for (EmpLog empLog:empLogs2){
                empRate1 += empLog.getComPlut();
                areaRate1 += empLog.getAllPlut();
            }
            double empRate2 = 0.0;
            double areaRate2 = 0.0;
            for (EmpLog empLog:empLogs3){
                empRate2 += empLog.getComPlut();
                areaRate2 += empLog.getAllPlut();
            }
            double empRate3 = 0.0;
            double areaRate3 = 0.0;
            for (EmpLog empLog:empLogs4){
                empRate3 += empLog.getComPlut();
                areaRate3 += empLog.getAllPlut();
            }
            Result result = new Result(i);
            result.setUploadEmp((int)Math.round(empRate/list1.size()/60/10/0.85));
            result.setPutawayEmp((int)Math.round(empRate/list2.size()/60/10/0.85));
            result.setSortingEmp((int)Math.round(empRate/list3.size()/60/10/0.85));
            result.setDeliveryEmp((int)Math.round(empRate/list4.size()/60/10/0.85));
            listr.add(result);
        }


        return getDataTable(listr);
    }



    private static double plat = 1.1 * 1.25 * 1.5;

    public static List<Cargo> initCargo(List<Order> list) {
        LinkedList<Goods> materials = new LinkedList<>();
        Map<String, List<Order>> outOrdersList = list.stream().collect(Collectors.groupingBy(Order::getGoodsCode));//出库单
        double total = 0.0;//总托数
        for (String goodsCode : outOrdersList.keySet()) {
            List<Order> orders = outOrdersList.get(goodsCode);
            double safeInventory = NetWorkPlanUtils.getSafeInventory(orders, 95); //获取安全库存
            double orderNum = NetWorkPlanUtils.getOrderNum(orders);
            double inventory1 = (safeInventory + orderNum / 2) * orders.get(0).getVolume() / plat;
            total += inventory1;
            for (int i = 0; i < inventory1; i++) {
                materials.add(new Goods(orders.get(0).getGoodsCode(), inventory1));
            }
        }


        List<Cargo> cargos = new ArrayList<>();
        for (int i = 1; i <= AreaUtils.getHightStorage(total, total).getLayer(); i++) {
            for (int j = 1; j <= AreaUtils.getHightStorage(total, total).getLine(); j++) {
                for (int k = 1; k <= AreaUtils.getHightStorage(total, total).getRow(); k++) {
                    Goods goods1 = materials.poll();
                    Point point = new Point(i, j, k);
                    cargos.add(new Cargo(point, goods1));
                }
            }
        }
        return cargos;
    }


}
