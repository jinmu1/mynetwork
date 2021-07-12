package com.ruoyi.system.domain;


/***
 * 分拣：
 * by ：jinmu
 */
public class MathCount {

    private  String average_value;//平均值
      private  String min_value;//最小值
      private  String max_value;//最大值
      private  String range;//极差
    private  String median;//中位数
    private  String  modes1;//众数
    private  String kurtosis;//峰度
    private  String skewness;//偏度
    private String standard_deviation;//标准差
    private String standard_error;//标准误差
    private String Censored_Average;//截尾平均数

    public String getAverage_value() {
        return average_value;
    }

    public void setAverage_value(String average_value) {
        this.average_value = average_value;
    }

    public String getMin_value() {
        return min_value;
    }

    public void setMin_value(String min_value) {
        this.min_value = min_value;
    }

    public String getMax_value() {
        return max_value;
    }

    public void setMax_value(String max_value) {
        this.max_value = max_value;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median;
    }

    public String getModes1() {
        return modes1;
    }

    public void setModes1(String modes1) {
        this.modes1 = modes1;
    }

    public String getKurtosis() {
        return kurtosis;
    }

    public void setKurtosis(String kurtosis) {
        this.kurtosis = kurtosis;
    }

    public String getSkewness() {
        return skewness;
    }

    public void setSkewness(String skewness) {
        this.skewness = skewness;
    }

    public String getStandard_deviation() {
        return standard_deviation;
    }

    public void setStandard_deviation(String standard_deviation) {
        this.standard_deviation = standard_deviation;
    }

    public String getStandard_error() {
        return standard_error;
    }

    public void setStandard_error(String standard_error) {
        this.standard_error = standard_error;
    }

    public String getCensored_Average() {
        return Censored_Average;
    }

    public void setCensored_Average(String censored_Average) {
        Censored_Average = censored_Average;
    }
}
