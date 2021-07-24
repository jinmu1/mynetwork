package com.ruoyi.network.utils;

import java.util.HashMap;
import java.util.Map;

public class ZvalueUtil {
    private static final Map<String, Double> Z_VALUE = new HashMap<String, Double>();
    static {
        Z_VALUE.put("0.50",0.00);
        Z_VALUE.put("0.51",0.03);
        Z_VALUE.put("0.52",0.05);
        Z_VALUE.put("0.53",0.08);
        Z_VALUE.put("0.54",0.10);
        Z_VALUE.put("0.55",0.13);
        Z_VALUE.put("0.56",0.15);
        Z_VALUE.put("0.57",0.18);
        Z_VALUE.put("0.58",0.20);
        Z_VALUE.put("0.59",0.23);
        Z_VALUE.put("0.60",0.25);
        Z_VALUE.put("0.61",0.28);
        Z_VALUE.put("0.62",0.31);
        Z_VALUE.put("0.63",0.33);
        Z_VALUE.put("0.64",0.36);
        Z_VALUE.put("0.65",0.39);
        Z_VALUE.put("0.66",0.41);
        Z_VALUE.put("0.67",0.44);
        Z_VALUE.put("0.68",0.47);
        Z_VALUE.put("0.69",0.50);
        Z_VALUE.put("0.70",0.52);
        Z_VALUE.put("0.71",0.55);
        Z_VALUE.put("0.72",0.58);
        Z_VALUE.put("0.73",0.61);
        Z_VALUE.put("0.74",0.64);
        Z_VALUE.put("0.75",0.67);
        Z_VALUE.put("0.76",0.71);
        Z_VALUE.put("0.77",0.74);
        Z_VALUE.put("0.78",0.77);
        Z_VALUE.put("0.79",0.81);
        Z_VALUE.put("0.80",0.84);
        Z_VALUE.put("0.81",0.88);
        Z_VALUE.put("0.82",0.92);
        Z_VALUE.put("0.83",0.95);
        Z_VALUE.put("0.84",0.99);
        Z_VALUE.put("0.85",1.04);
        Z_VALUE.put("0.86",1.08);
        Z_VALUE.put("0.87",1.13);
        Z_VALUE.put("0.88",1.17);
        Z_VALUE.put("0.89",1.23);
        Z_VALUE.put("0.90",1.28);
        Z_VALUE.put("0.91",1.34);
        Z_VALUE.put("0.92",1.41);
        Z_VALUE.put("0.93",1.48);
        Z_VALUE.put("0.94",1.55);
        Z_VALUE.put("0.95",1.64);
        Z_VALUE.put("0.96",1.75);
        Z_VALUE.put("0.97",1.88);
        Z_VALUE.put("0.98",2.05);
        Z_VALUE.put("0.99",2.33);
        Z_VALUE.put("1.00",2.50);
    }
    public static double getZ(String str) {
        double z=1;
        if(Double.valueOf(str)>0.5&&Double.valueOf(str)<=1){
            z=Z_VALUE.get(str);
        }
        return z;
    }
}
