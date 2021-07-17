package com.ruoyi.network.utils;

import java.math.BigDecimal;

public final class ABCUtils {


    /**
     *  计算平均库存
     * @param total
     * @param times
     * @return
     */
    public static String getAvg(String total,String times){
        BigDecimal bigDecimal1 = new BigDecimal(total);
        BigDecimal bigDecimal2 = new BigDecimal(times);
        String result = bigDecimal1.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_UP).toString();
        return result;
    }


    /**
     * 求平均资金占用额
     * @param stock_price
     * @param Avg
     * @return
     */
   public static String getMoney_occupied(String stock_price,String Avg){
        BigDecimal bigDecimal = new BigDecimal(stock_price);
        BigDecimal bigDecimal1 = new BigDecimal(Avg);
        String result = bigDecimal.multiply(bigDecimal1).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
        return result;
   }
    /**
     * 计算累计品目数百分比
     * @param accumulated_item
     * @param accumulated_item_max
     * @return
     */
   public static String getPercentage_item(String accumulated_item,String accumulated_item_max){
       BigDecimal bigDecimal = new BigDecimal(accumulated_item);
       BigDecimal bigDecimal1 = new BigDecimal(accumulated_item_max);
       String result = bigDecimal.multiply(new BigDecimal("100")).divide(bigDecimal1).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
       return result;
   }




}
