package com.ruoyi.production.process;

import com.ruoyi.production.queue.Order;
import com.ruoyi.production.utils.DateUtils;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 中间仓往线边仓配送
 */
public class Distribution {

    public double work( List<Order> outorders,double meterDouble,double workTime){
        Map<String, List<Order>> orderMaps = outorders.stream().collect(Collectors.groupingBy(Order::getOrderCode));//出库单
        double transportCost = 0;
        for (String ordercode : orderMaps.keySet()) {
            double orderCost = 0.0;
            List<Order> orderList = orderMaps.get(ordercode);
            double transportDate;

            /**
             * 配送运输成本计算
             */
            for (Order order : orderList) {
                        orderCost += order.getGoodsNum() * order.getVolume() * Math.ceil(meterDouble)  * (1 + 0.11);//运输体积与运输量以及运输费率得到运输成本
                        transportDate = meterDouble / workTime; //运输周期
                        transportCost += orderCost;
                    }
                }
            return transportCost;
            }




}
