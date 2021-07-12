package com.ruoyi.quartz.util;



import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ROUND_HALF_UP;

public final class EOQUtil {
    /**
     *  计算基本EOQ模型下求经济订货批量
     * @param C1 库存保管费用
     * @param C3 订购费
     * @param R 总需求
     * @return 得出EOQ
     */
    public  static String getEOQ1(String C1,String C3,String R){
         BigDecimal bigDecimal1 = new BigDecimal(C1);
         BigDecimal bigDecimal2 = new BigDecimal(C3);
         BigDecimal bigDecimal3 = new BigDecimal(R);
         String result = sqrt(bigDecimal2.multiply(new BigDecimal("2")).multiply(bigDecimal3).divide(bigDecimal1,0, ROUND_HALF_UP)).setScale(0,BigDecimal.ROUND_UP ).toString();
         return result;
    }

    /**
     * 计算订货次数
     * @param R 单位内总需求
     * @param EOQ Eoq
     * @return
     */
    public  static String getN1(String R,String EOQ){
        BigDecimal bigDecimal1 = new BigDecimal(R);
        BigDecimal bigDecimal2 = new BigDecimal(EOQ);
        String  result = bigDecimal1.divide(bigDecimal2,0,ROUND_HALF_UP).setScale(2,BigDecimal.ROUND_UP).toString();
        return result;
    }

    /**
     * 计算基本的EOQ模型的最小订货成本
     * @param C1 库存费
     * @param C3 单次订货成本
     * @param R 需求
     * @return
     */

    public static String getTotal_cost1(String C1,String C3,String R){
        BigDecimal bigDecimal1 = new BigDecimal(C1);
        BigDecimal bigDecimal2 = new BigDecimal(C3);
        BigDecimal bigDecimal3 = new BigDecimal(R);
        String result = bigDecimal1.multiply(new BigDecimal("2")).multiply(bigDecimal2).multiply(bigDecimal3).setScale(2, ROUND_HALF_UP).toString();
        return result;
    }

    /**
     *
     * @param C1 库存费
     * @param C3 单次订货成本
     * @param K
     * @param R 需求
     * @param EOQ
     * @return
     */
    public static String getTotal_cost(String C1, String C3,String K,String R,String EOQ){
        BigDecimal bigDecimal1 = new BigDecimal(C1);
        BigDecimal bigDecimal2 = new BigDecimal(C3);
        BigDecimal bigDecimal3 = new BigDecimal(R);
        BigDecimal bigDecimal4 = new BigDecimal(K);
        BigDecimal bigDecimal5 = new BigDecimal(EOQ);
        String result = bigDecimal1.multiply(bigDecimal5).divide(new BigDecimal("2")).add(bigDecimal3.multiply(bigDecimal4)).add(bigDecimal2.multiply(bigDecimal3).divide(bigDecimal5,0, ROUND_HALF_UP)).setScale(2,BigDecimal.ROUND_UP).toString();
        return result;
    }


    /**
     * 允许缺货下的EOQ计算EOQ
     * @param C1 订购费
     * @param C2 缺货成本
     * @param C3 单次订货成本
     * @param R  总需求
     * @return
     */

    public static String getEOQ2(String C1,String C2,String C3,String R){
        BigDecimal bigDecimal1 = new BigDecimal(C1);
        BigDecimal bigDecimal2 = new BigDecimal(C2);
        BigDecimal bigDecimal3 = new BigDecimal(C3);
        BigDecimal bigDecimal4 = new BigDecimal(R);
        String result = sqrt(bigDecimal3.multiply(new BigDecimal("2")).multiply(bigDecimal4).multiply(bigDecimal1.add(bigDecimal2)).divide(bigDecimal1.multiply(bigDecimal2),0, ROUND_HALF_UP)).setScale(0, ROUND_HALF_UP).toString();
        return result;
    }

    /**
     * 缺货成本下的最大库存量
     * @param C1 订购费
     * @param C2 缺货成本
     * @param C3 单次订货成本
     * @param R  总需求
     * @return
     */

    public static String getInventory1(String C1,String C2,String C3,String R){
        BigDecimal bigDecimal1 = new BigDecimal(C1);
        BigDecimal bigDecimal2 = new BigDecimal(C2);
        BigDecimal bigDecimal3 = new BigDecimal(C3);
        BigDecimal bigDecimal4 = new BigDecimal(R);
        String result = sqrt(bigDecimal3.multiply(new BigDecimal("2")).multiply(bigDecimal4).multiply(bigDecimal2).divide(bigDecimal1.multiply(bigDecimal1.add(bigDecimal2)),0, ROUND_HALF_UP)).setScale(0, ROUND_HALF_UP).toString();
        return result;
    }

    /**
     * 缺货情况下的存货周期
     * N :订货次数
     * @return
     */
    public static String getCycle(String N){
        BigDecimal bigDecimal1 = new BigDecimal(N);
        String result =(new BigDecimal("30")).divide(bigDecimal1,0,BigDecimal.ROUND_UP).toString();
        return result;
    }

    /**
     * 缺货情况下求最小总成本
     * @param C1 订购费
     * @param C2 缺货成本
     * @param C3 单次订货成本
     * @param EOQ
     * @param S 最小总成本
     * @return
     */
    public static String getInventory2(String C1,String C2,String C3,String EOQ,String S) {
        BigDecimal bigDecimal1 = new BigDecimal(C1);
        BigDecimal bigDecimal2 = new BigDecimal(C2);
        BigDecimal bigDecimal3 = new BigDecimal(C3);
        BigDecimal bigDecimal4 = new BigDecimal(EOQ);
        BigDecimal bigDecimal5 = new BigDecimal(S);
        String result = bigDecimal1.multiply(bigDecimal5.multiply(bigDecimal5)).add(bigDecimal2.multiply(bigDecimal4.subtract(bigDecimal5)).multiply(bigDecimal4.subtract(bigDecimal5)).add(bigDecimal3.multiply(bigDecimal5).multiply(new BigDecimal("2")))).divide(bigDecimal5.multiply(new BigDecimal("2")),0, ROUND_HALF_UP).toString();
        return result;
    }

    /**
     * 连续增长率下的EOQ模型
     * @param C1 订购费
     * @param C3 单次订货成本
     * @param R 总需求
     * @param P 生产率
     * @return
     */
    public static String getEOQ3(String C1,String C3,String R,String P){
        BigDecimal bigDecimal1 = new BigDecimal(C1);
        BigDecimal bigDecimal2 = new BigDecimal(C3);
        BigDecimal bigDecimal3 = new BigDecimal(R);
        BigDecimal bigDecimal4 = new BigDecimal(P);
        String result = sqrt(bigDecimal2.multiply(bigDecimal3).multiply(new BigDecimal("2")).multiply(bigDecimal4).divide(bigDecimal1.multiply(bigDecimal4.subtract(bigDecimal3)),0,BigDecimal.ROUND_UP)).setScale(0,BigDecimal.ROUND_UP).toString();
        return result;
    }

    /**
     * 连续增长率下EOQ模型求最小总成本
     * @param C1 订购费
     * @param C3 单次订货成本
     * @param EOQ
     * @param P 生产率
     * @param R 总需求
     * @return
     */
    public static String getInventory3(String C1,String C3,String EOQ,String P,String R){
        BigDecimal bigDecimal1 = new BigDecimal(C1);
        BigDecimal bigDecimal2 = new BigDecimal(C3);
        BigDecimal bigDecimal3 = new BigDecimal(EOQ);
        BigDecimal bigDecimal4 = new BigDecimal(P);
        BigDecimal bigDecimal5 = new BigDecimal(R);

        String result = bigDecimal1.multiply(bigDecimal3).multiply(bigDecimal4.subtract(bigDecimal5)).multiply(new BigDecimal("0.5")).add(bigDecimal2.multiply(bigDecimal5).divide(bigDecimal3,0,BigDecimal.ROUND_UP)).setScale(2,BigDecimal.ROUND_UP).toString();
        return  result;
    }
     public static String getEOQ4(String C1,String C2,String C3,String R){
         BigDecimal bigDecimal1 = new BigDecimal(C1);
         BigDecimal bigDecimal2 = new BigDecimal(C2);
         BigDecimal bigDecimal3 = new BigDecimal(C3);
         BigDecimal bigDecimal4 = new BigDecimal(R);
         String result = sqrt(bigDecimal3.multiply(new BigDecimal("2")).multiply(bigDecimal4).multiply(bigDecimal1.add(bigDecimal2)).divide(bigDecimal1.multiply(bigDecimal2),0,BigDecimal.ROUND_UP)).setScale(0,BigDecimal.ROUND_UP).toString();
         return result;

     }





    /**
     * 开方运算
     * @param num
     * @return
     */

    public static BigDecimal sqrt(BigDecimal num) {
        if(num.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal x = num.divide(new BigDecimal("2"), MathContext.DECIMAL128);
        while(x.subtract(x = sqrtIteration(x, num)).abs().compareTo(new BigDecimal("0.0000000000000000000001")) > 0);
        return x;
    }

    private static BigDecimal sqrtIteration(BigDecimal x, BigDecimal n) {
        return x.add(n.divide(x, MathContext.DECIMAL128)).divide(new BigDecimal("2"), MathContext.DECIMAL128);
    }


}
