package com.ruoyi.production.process;

import com.ruoyi.production.form.City;
import com.ruoyi.production.queue.Order;
import com.ruoyi.production.utils.JuLiUtils;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 运输
 */
public class Transport {
    /**
     * 通过运输单计算运输成本
     *
     * @param outorders
     * @param cities
     * @return
     */

    public static void getTransportCost(City point, List<City> cities, List<Order> outorders, List<Order> inOrders,  double time) {
        double transportCost = 0.0;
        double replenishmentCost = 0.0;
        double transportDate = 0.0; //运输周期
        double times = 0.0;
        double all = 0.0;
        int i = 0;
        Map<String, List<Order>> orderMaps = outorders.stream().collect(Collectors.groupingBy(Order::getOrderCode));//出库单
        Map<String, List<Order>> inorderMaps = inOrders.stream().collect(Collectors.groupingBy(Order::getOrderCode));//入库单

        double[] transportfee = new double[orderMaps.keySet().size()];
        for (String orderCode : inorderMaps.keySet()) {
            double orderCost = 0.0;
            List<Order> orderList = inorderMaps.get(orderCode);
            for (Order order : orderList) {
                for (City city : cities) {
                    if (city.getCity().equals(order.getSupplier().getCity())) {
                        double meterDouble = JuLiUtils.twoJuLi(order.getSupplier().getCity(), city) / 1000;
                        orderCost += order.getGoodsNum() * order.getVolume() * Math.ceil(meterDouble) * order.getSupplier().getCity().getTransportfee() * (1 + 0.11);//运输体积与运输量以及运输费率得到运输成本
                    }
                }
            }
            replenishmentCost += orderCost;

        }

        for (String ordercode : orderMaps.keySet()) {
            double orderCost = 0.0;
            List<Order> orderList = orderMaps.get(ordercode);

            transportfee[i] = transportCost;
            i++;
        }
    }

}
