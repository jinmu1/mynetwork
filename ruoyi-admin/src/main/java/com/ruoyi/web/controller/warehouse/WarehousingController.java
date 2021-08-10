package com.ruoyi.web.controller.warehouse;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.network.form.GlcPoint;
import com.ruoyi.network.node.*;
import com.ruoyi.network.utils.NetWorkPlanUtils;
import com.ruoyi.network.utils.NetworkUtils;
import com.ruoyi.system.service.IGlcPointService;
import com.ruoyi.warehousing.action.Action;
import com.ruoyi.warehousing.form.Cargo;
import com.ruoyi.warehousing.form.Goods;
import com.ruoyi.warehousing.process.Delivery;
import com.ruoyi.warehousing.process.Putaway;
import com.ruoyi.warehousing.process.Sorting;
import com.ruoyi.warehousing.process.Upload;
import com.ruoyi.warehousing.queue.Point;
import com.ruoyi.warehousing.resource.equipment.Elevator;
import com.ruoyi.warehousing.resource.equipment.LightStorage;
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
import org.springframework.web.bind.annotation.GetMapping;
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

    private String prefix = "system/warehouse";
    @GetMapping("/warehouse")
    public String point()
    {
        return prefix + "/index";
    }
    @GetMapping("/warehousePlan")
    public String warehousePlan()
    {
        return prefix + "/index1";
    }
    @PostMapping("/getWarehouse")
    @ResponseBody
    public TableDataInfo upload(HttpServletRequest req) {
        String provinces = req.getParameter("provinces");
        String carLength = req.getParameter("carLength");
        double transportNum = 2370 * 365;
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
        int goods_num = 6000;
        if (req.getParameter("goods_num") != null && !req.getParameter("goods_num").equals("")) {
            goods_num = Integer.parseInt(req.getParameter("goods_num"));
        }
        double b_goods_size = 20;
        if (req.getParameter("b_goods_size") != null && !req.getParameter("b_goods_size").equals("")) {
            b_goods_size = Integer.parseInt(req.getParameter("b_goods_size"));
        }
        double m_goods_size = 20;
        if (req.getParameter("m_goods_size") != null && !req.getParameter("m_goods_size").equals("")) {
            m_goods_size = Double.parseDouble(req.getParameter("m_goods_size"));
        }
        double s_goods_size = 60;
        if (req.getParameter("s_goods_size") != null && !req.getParameter("s_goods_size").equals("")) {
            s_goods_size = Double.parseDouble(req.getParameter("s_goods_size"));
        }
        double orderLine = 60;
        if (req.getParameter("orderLine") != null && !req.getParameter("orderLine").equals("")) {
            orderLine = Double.parseDouble(req.getParameter("orderLine"));
        }
        int customerNum = 200;
        if (req.getParameter("customerNum") != null && !req.getParameter("customerNum").equals("")) {
            customerNum = Integer.parseInt(req.getParameter("customerNum"));
        }
        int supplier = 100;
        if (req.getParameter("supplier") != null && !req.getParameter("supplier").equals("")) {
            supplier = Integer.parseInt(req.getParameter("supplier"));
        }
        double orderTime= 72;
        if (req.getParameter("orderTime") != null && !req.getParameter("orderTime").equals("")) {
            orderTime = Integer.parseInt(req.getParameter("orderTime"));
        }

        //订单数量 供应商- 客户  订单完成时效
        List<Result> listr = Action.runAction(transportNum,customerNum,carLength,goods_num,supplier,b_goods_size,m_goods_size,s_goods_size);



        return getDataTable(listr);
    }

    @PostMapping("/getUpload1")
    @ResponseBody
    public TableDataInfo dsde1(HttpServletRequest req) {
        String carLength = req.getParameter("carlength");
        double transportNum = 2370;
        if (req.getParameter("transportNum") != null && !req.getParameter("transportNum").equals("")) {
            transportNum = Double.parseDouble(req.getParameter("transportNum"));
        }
        double unloading_time = 2370;
        if (req.getParameter("unloading_time") != null && !req.getParameter("unloading_time").equals("")) {
            unloading_time = Double.parseDouble(req.getParameter("unloading_time"));
        }
        double everyDay_unloading_time = 2370;
        if (req.getParameter("everyDay_unloading_time") != null && !req.getParameter("everyDay_unloading_time").equals("")) {
            everyDay_unloading_time = Double.parseDouble(req.getParameter("everyDay_unloading_time"));
        }
        double uploadEmpCapacity = 2370;
        if (req.getParameter("uploadEmpCapacity") != null && !req.getParameter("uploadEmpCapacity").equals("")) {
            uploadEmpCapacity = Double.parseDouble(req.getParameter("uploadEmpCapacity"));
        }
        double platform_length = 3;
        if (req.getParameter("platform_length") != null && !req.getParameter("platform_length").equals("")) {
            platform_length = Double.parseDouble(req.getParameter("platform_length"));
        }
        int supplier = 3;
        if (req.getParameter("supplier") != null && !req.getParameter("supplier").equals("")) {
            supplier = Integer.parseInt(req.getParameter("supplier"));
        }
        int goods_num = 1000;
        if (req.getParameter("goods_num") != null && !req.getParameter("goods_num").equals("")) {
            goods_num = Integer.parseInt(req.getParameter("goods_num"));
        }
        List<Result> list = new ArrayList<>();

        Result result = new Result(1);
        Platform platform = AreaUtils.getPlatform1(transportNum,carLength,uploadEmpCapacity,unloading_time,everyDay_unloading_time,platform_length);
        Tally tally = AreaUtils.getTally(transportNum,carLength);
        list = Action.runPlatform(platform,tally,transportNum,supplier,goods_num,carLength,platform_length);
        platform.setEmp((int)(platform.getEmp()/result.getUploadEmp()/0.8));
        list = Action.runPlatform(platform,tally,transportNum,supplier,goods_num,carLength,platform_length);

        result.setPlatformArea(platform.getPlatform_area());
        result.setUploadEmp(platform.getEmp());
        result.setPlatformNum(platform.getPlatform_num());
        result.setForklift(platform.getForklift());
        result.setEmpCost(platform.getEmpCost());
        result.setForkliftCost(platform.getForkliftCost());
        list.add(result);
        return getDataTable(list);
    }


    @PostMapping("/getTally")
    @ResponseBody
    public TableDataInfo getTally(HttpServletRequest req) {
        String carLength  = "小车4米6";
        if (req.getParameter("carLength") != null && !req.getParameter("carLength").equals("")) {
            carLength = req.getParameter("carLength");
        }

        double transportNum = 2370;
        if (req.getParameter("transportNum") != null && !req.getParameter("transportNum").equals("")) {
            transportNum = Double.parseDouble(req.getParameter("transportNum"));
        }
        double batch = 3;
        if (req.getParameter("batch") != null && !req.getParameter("batch").equals("")) {
            batch = Double.parseDouble(req.getParameter("batch"));
        }
        double tallyEmpCapacity = 2370;
        if (req.getParameter("tallyEmpCapacity") != null && !req.getParameter("tallyEmpCapacity").equals("")) {
            tallyEmpCapacity = Double.parseDouble(req.getParameter("tallyEmpCapacity"));
        }
        double tally_channel = 3;
        if (req.getParameter("tally_channel") != null && !req.getParameter("tally_channel").equals("")) {
            tally_channel = Double.parseDouble(req.getParameter("tally_channel"));
        }
        double tray_clearance = 0.2;
        if (req.getParameter("tray_clearance") != null && !req.getParameter("tray_clearance").equals("")) {
            tray_clearance = Double.parseDouble(req.getParameter("tray_clearance"));
        }

        List<Result> list = new ArrayList<>();
        Tally tally = AreaUtils.getTally1(transportNum,carLength,batch,tallyEmpCapacity,tally_channel,tray_clearance);
        Result result = new Result(1);
        result.setTallyArea(tally.getArea());
        result.setTallyEmp(tally.getEmp());
        result.setEmpCost(tally.getEmpCost());
        list.add(result);
        return getDataTable(list);
    }

    @PostMapping("/getStorage")
    @ResponseBody
    public TableDataInfo getStorage(HttpServletRequest req) {

        double transportNum2 = 2370;
        if (req.getParameter("transportNum2") != null && !req.getParameter("transportNum2").equals("")) {
            transportNum2 = Double.parseDouble(req.getParameter("transportNum2"));
        }
        double transportNum3 = 2370;
        if (req.getParameter("transportNum3") != null && !req.getParameter("transportNum3").equals("")) {
            transportNum3 = Double.parseDouble(req.getParameter("transportNum3"));
        }
        double transportNum4 = 2370;
        if (req.getParameter("transportNum4") != null && !req.getParameter("transportNum4").equals("")) {
            transportNum4 = Double.parseDouble(req.getParameter("transportNum4"));
        }
        double height = 10;
        if (req.getParameter("height") != null && !req.getParameter("height").equals("")) {
            height = Double.parseDouble(req.getParameter("height"));
        }
        double forklift_channel = 2370;
        if (req.getParameter("forklift_channel") != null && !req.getParameter("forklift_channel").equals("")) {
            forklift_channel = Double.parseDouble(req.getParameter("forklift_channel"));
        }
        double shelf_space = 3;
        if (req.getParameter("shelf_space") != null && !req.getParameter("shelf_space").equals("")) {
            shelf_space = Double.parseDouble(req.getParameter("shelf_space"));
        }
        double putaway = 0.2;
        if (req.getParameter("putaway") != null && !req.getParameter("putaway").equals("")) {
            putaway = Double.parseDouble(req.getParameter("putaway"));
        }
        double sorting = 0.2;
        if (req.getParameter("sorting") != null && !req.getParameter("sorting").equals("")) {
            sorting = Double.parseDouble(req.getParameter("sorting"));
        }
        double shelf_height  = 1.65;
        if (req.getParameter("shelf_height") != null && !req.getParameter("shelf_height").equals("")) {
            shelf_height = Double.parseDouble(req.getParameter("shelf_height"));
        }
        List<Result> list = new ArrayList<>();
        LightStorage storage = AreaUtils.getHightStorage1(transportNum4,transportNum2,transportNum3,putaway,sorting,height,forklift_channel,shelf_space,shelf_height);
        Result result =new Result(1);
        result.setStorageArea(storage.getArea());
        result.setCargo(storage.getCargo());
        result.setPutawayEmp(storage.getPutawayemp());
        result.setSortingEmp(storage.getSortingemp());
        result.setCost(storage.getPrice()*storage.getCargo());

        list.add(result);
        return getDataTable(list);
    }
    private static double plat = 1.1 * 1.25 * 1.5;

    public static List<Cargo> initCargo(List<Order> list,double total) {
        LinkedList<Goods> materials = new LinkedList<>();
        Map<String, List<Order>> outOrdersList = list.stream().collect(Collectors.groupingBy(Order::getGoodsCode));//出库单
         total = 0.0;//总托数
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

    @PostMapping("/getStoragePoint")
    @ResponseBody
    public TableDataInfo getStoragePoint(HttpServletRequest req) {

        double transportNum2 = 2370;
        if (req.getParameter("transportNum2") != null && !req.getParameter("transportNum2").equals("")) {
            transportNum2 = Double.parseDouble(req.getParameter("transportNum2"));
        }
        double transportNum3 = 2370;
        if (req.getParameter("transportNum3") != null && !req.getParameter("transportNum3").equals("")) {
            transportNum3 = Double.parseDouble(req.getParameter("transportNum3"));
        }
        double transportNum4 = 2370;
        if (req.getParameter("transportNum4") != null && !req.getParameter("transportNum4").equals("")) {
            transportNum4 = Double.parseDouble(req.getParameter("transportNum4"));
        }
        double height = 10;
        if (req.getParameter("height") != null && !req.getParameter("height").equals("")) {
            height = Double.parseDouble(req.getParameter("height"));
        }
        double forklift_channel = 2370;
        if (req.getParameter("forklift_channel") != null && !req.getParameter("forklift_channel").equals("")) {
            forklift_channel = Double.parseDouble(req.getParameter("forklift_channel"));
        }
        double shelf_space = 3;
        if (req.getParameter("shelf_space") != null && !req.getParameter("shelf_space").equals("")) {
            shelf_space = Double.parseDouble(req.getParameter("shelf_space"));
        }
        double putaway = 150;
        if (req.getParameter("putaway") != null && !req.getParameter("putaway").equals("")) {
            putaway = Double.parseDouble(req.getParameter("putaway"));
        }
        double sorting =100;
        if (req.getParameter("sorting") != null && !req.getParameter("sorting").equals("")) {
            sorting = Double.parseDouble(req.getParameter("sorting"));
        }
        double shelf_height  = 1.65;
        if (req.getParameter("shelf_height") != null && !req.getParameter("shelf_height").equals("")) {
            shelf_height = Double.parseDouble(req.getParameter("shelf_height"));
        }
        String sort_type = req.getParameter("sort_type");
        double goods_num= 2000;
        if (req.getParameter("goods_num") != null && !req.getParameter("goods_num").equals("")) {
            goods_num = Integer.parseInt(req.getParameter("goods_num"));
        }

        List<Result> list = new ArrayList<>();
        LightStorage storage = AreaUtils.getHightStorage1(transportNum4,transportNum2,transportNum3,putaway,sorting,height,forklift_channel,shelf_space,shelf_height);
        Result result = Action.action(goods_num,sort_type,transportNum2,transportNum3,storage);

        result.setStorageArea(storage.getArea());
        result.setCargo(storage.getCargo());
        result.setPutawayEmp(storage.getPutawayemp());
        result.setSortingEmp(storage.getSortingemp());
        result.setCost(storage.getPrice()*storage.getCargo());
        list.add(result);
        return getDataTable(list);
    }
    @PostMapping("/getUpload")
    @ResponseBody
    public TableDataInfo dsde(HttpServletRequest req) {
        String carLength = req.getParameter("carlength");
        double transportNum = 2370;
        if (req.getParameter("transportNum") != null && !req.getParameter("transportNum").equals("")) {
            transportNum = Double.parseDouble(req.getParameter("transportNum"));
        }
        double unloading_time = 2370;
        if (req.getParameter("unloading_time") != null && !req.getParameter("unloading_time").equals("")) {
            unloading_time = Double.parseDouble(req.getParameter("unloading_time"));
        }
        double everyDay_unloading_time = 2370;
        if (req.getParameter("everyDay_unloading_time") != null && !req.getParameter("everyDay_unloading_time").equals("")) {
            everyDay_unloading_time = Double.parseDouble(req.getParameter("everyDay_unloading_time"));
        }
        double uploadEmpCapacity = 2370;
        if (req.getParameter("uploadEmpCapacity") != null && !req.getParameter("uploadEmpCapacity").equals("")) {
            uploadEmpCapacity = Double.parseDouble(req.getParameter("uploadEmpCapacity"));
        }
        double platform_length = 3;
        if (req.getParameter("platform_length") != null && !req.getParameter("platform_length").equals("")) {
            platform_length = Double.parseDouble(req.getParameter("platform_length"));
        }
        List<Result> list = new ArrayList<>();
        Platform platform = AreaUtils.getPlatform1(transportNum,carLength,uploadEmpCapacity,unloading_time,everyDay_unloading_time,platform_length);
        Result result = new Result(1);
        result.setPlatformArea(platform.getPlatform_area());
        result.setUploadEmp(platform.getEmp());
        result.setPlatformNum(platform.getPlatform_num());
        result.setForklift(platform.getForklift());
        result.setEmpCost(platform.getEmpCost());
        result.setForkliftCost(platform.getForkliftCost());
        list.add(result);
        return getDataTable(list);
    }

    @PostMapping("/getStoragePoint1")
    @ResponseBody
    public TableDataInfo getStoragePoint1(HttpServletRequest req) {

        double transportNum2 = 2370;
        if (req.getParameter("transportNum2") != null && !req.getParameter("transportNum2").equals("")) {
            transportNum2 = Double.parseDouble(req.getParameter("transportNum2"));
        }
        double transportNum3 = 2370;
        if (req.getParameter("transportNum3") != null && !req.getParameter("transportNum3").equals("")) {
            transportNum3 = Double.parseDouble(req.getParameter("transportNum3"));
        }
        double transportNum4 = 2370;
        if (req.getParameter("transportNum4") != null && !req.getParameter("transportNum4").equals("")) {
            transportNum4 = Double.parseDouble(req.getParameter("transportNum4"));
        }
        double height = 10;
        if (req.getParameter("height") != null && !req.getParameter("height").equals("")) {
            height = Double.parseDouble(req.getParameter("height"));
        }
        double forklift_channel = 2370;
        if (req.getParameter("forklift_channel") != null && !req.getParameter("forklift_channel").equals("")) {
            forklift_channel = Double.parseDouble(req.getParameter("forklift_channel"));
        }
        double shelf_space = 3;
        if (req.getParameter("shelf_space") != null && !req.getParameter("shelf_space").equals("")) {
            shelf_space = Double.parseDouble(req.getParameter("shelf_space"));
        }
        double putaway = 150;
        if (req.getParameter("putaway") != null && !req.getParameter("putaway").equals("")) {
            putaway = Double.parseDouble(req.getParameter("putaway"));
        }
        double sorting =68;
        if (req.getParameter("sorting") != null && !req.getParameter("sorting").equals("")) {
            sorting = Double.parseDouble(req.getParameter("sorting"));
        }
        double shelf_height  = 1.65;
        if (req.getParameter("shelf_height") != null && !req.getParameter("shelf_height").equals("")) {
            shelf_height = Double.parseDouble(req.getParameter("shelf_height"));
        }
        String sort_type = req.getParameter("sort_type");
        double goods_num= 2000;
        if (req.getParameter("goods_num") != null && !req.getParameter("goods_num").equals("")) {
            goods_num = Integer.parseInt(req.getParameter("goods_num"));
        }

        List<Result> list = new ArrayList<>();
        LightStorage storage = AreaUtils.getHightStorage1(transportNum4,transportNum2,transportNum3,putaway,sorting,height,forklift_channel,shelf_space,shelf_height);
        Result result = Action.action(goods_num,sort_type,transportNum2,transportNum3,storage);

        result.setStorageArea(storage.getArea());
        result.setCargo(storage.getCargo());
        result.setPutawayEmp(storage.getPutawayemp());
        result.setSortingEmp(storage.getSortingemp());
        result.setCost(storage.getPrice()*storage.getCargo());
        list.add(result);
        return getDataTable(list);
    }
    @PostMapping("/getStoragePoint2")
    @ResponseBody
    public TableDataInfo getStoragePoint2(HttpServletRequest req) {

        double transportNum2 = 2370;
        if (req.getParameter("transportNum2") != null && !req.getParameter("transportNum2").equals("")) {
            transportNum2 = Double.parseDouble(req.getParameter("transportNum2"));
        }
        double transportNum3 = 2370;
        if (req.getParameter("transportNum3") != null && !req.getParameter("transportNum3").equals("")) {
            transportNum3 = Double.parseDouble(req.getParameter("transportNum3"));
        }
        double transportNum4 = 2370;
        if (req.getParameter("transportNum4") != null && !req.getParameter("transportNum4").equals("")) {
            transportNum4 = Double.parseDouble(req.getParameter("transportNum4"));
        }
        double height = 10;
        if (req.getParameter("height") != null && !req.getParameter("height").equals("")) {
            height = Double.parseDouble(req.getParameter("height"));
        }
        double forklift_channel = 2370;
        if (req.getParameter("forklift_channel") != null && !req.getParameter("forklift_channel").equals("")) {
            forklift_channel = Double.parseDouble(req.getParameter("forklift_channel"));
        }
        double shelf_space = 3;
        if (req.getParameter("shelf_space") != null && !req.getParameter("shelf_space").equals("")) {
            shelf_space = Double.parseDouble(req.getParameter("shelf_space"));
        }
        double putaway = 150;
        if (req.getParameter("putaway") != null && !req.getParameter("putaway").equals("")) {
            putaway = Double.parseDouble(req.getParameter("putaway"));
        }
        double sorting =68;
        if (req.getParameter("sorting") != null && !req.getParameter("sorting").equals("")) {
            sorting = Double.parseDouble(req.getParameter("sorting"));
        }
        double shelf_height  = 1.65;
        if (req.getParameter("shelf_height") != null && !req.getParameter("shelf_height").equals("")) {
            shelf_height = Double.parseDouble(req.getParameter("shelf_height"));
        }
        String sort_type = req.getParameter("sort_type");
        double goods_num= 2000;
        if (req.getParameter("goods_num") != null && !req.getParameter("goods_num").equals("")) {
            goods_num = Integer.parseInt(req.getParameter("goods_num"));
        }

        List<Result> list = new ArrayList<>();
        LightStorage storage = AreaUtils.getHightStorage1(transportNum4,transportNum2,transportNum3,putaway,sorting,height,forklift_channel,shelf_space,shelf_height);
        Result result = Action.action1(goods_num,sort_type,transportNum2,transportNum3,storage);

        result.setStorageArea(storage.getArea());
        result.setCargo(storage.getCargo());
        result.setPutawayEmp(storage.getPutawayemp());
        result.setSortingEmp(storage.getSortingemp());
        result.setCost(storage.getPrice()*storage.getCargo());
        list.add(result);
        return getDataTable(list);
    }
}
