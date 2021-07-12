package com.ruoyi.system.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.domain.mian.Car;
import com.ruoyi.system.domain.mian.GlcPoint;
import com.ruoyi.system.domain.network.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;


/**
 * 省内网络规划数据
 */
public class NetworkUtils {

    private static Map<Character, Character> map = new HashMap<Character, Character>();
    private static final String PATH = "/resources/xml/codeRegular.xml";
    private static String threePart[] = null; //第二部分
    private static String fourPart[] = null; //第三部分
    private static Map<String, String> twoPart = new HashMap<String, String>();  //第一部分
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    /***
     * 通过网络参数输出网络结果数据
     * carType:车辆类型
     * transport
     * @return
     */

    public static double getNetWorkData(String carType,double  transportNum, List<GlcPoint> list){
        double gdp =  0.0;
        for (GlcPoint city:list){
            gdp+= Double.parseDouble(city.getGdp());
        }
        List<City> rdcPoint = new ArrayList<>();//rdc生成池
        List<City> point = new ArrayList<>();
        Map<String, List<GlcPoint>> listMap = list.stream().collect(Collectors.groupingBy(GlcPoint::getCity));//结果集合
        for (String center:listMap.keySet()){
            GlcPoint glcPoint = listMap.get(center).get(0);            //需求点
            point.add(new City(glcPoint.getCity(),glcPoint.getLat(),glcPoint.getLng(), glcPoint.getGdp()));//备选带你
        }
        double max = Double.MAX_VALUE;
        int number = 0;
        for(int i=1;i<list.size();i++) {
            List<List<City>> combinations = combinations(point, new Random(list.size()).nextInt());//已经遍历了该RDC数量下的所有RDC组合 k为备选点数量
            List<City> cities = new ArrayList<>();
            for (List<City> cityList : combinations) {
                rdcPoint = new ArrayList<>();
                for (City city : cityList) {
                    rdcPoint.add(new City(city.getCity())); //将选取的备选点当做新增的RDC
                }
                // 开始计算
                cities = chooseCity(rdcPoint, point);//选择城市
                double transportCost = 0.0;
                for (City city : cities) {
                    double carCos = Double.parseDouble(Car.valueOf(carType).getCode()); //计算车辆类型
                    double CarNum = Double.parseDouble(city.getGdp()) / gdp * transportNum * 1.2 * 1 * 1.5 / carCos;//计算车辆数量(总计)
                    transportCost += Double.parseDouble(city.getDistance()) * 0.89 * 1.09 * carCos * transportNum * 1.2 * 1 * 1.5;//计算运输成本
                }
                if (transportCost<max){
                    max = transportCost;
                    number = i;
                }

            }
        }
            return max;
    }

    /**
     * 为每个物料分配物料名称
     * @param
     * @param rangeNum
     * @return
     */
    public static List<Material> createGoods(double rangeNum){
        List<Material> materialList = new ArrayList<>();
        Random random =new Random();
        for (int i=0;i<rangeNum;i++){
             Material material = new Material();
                 material.setCode(RandomUtil.toFixdLengthString(random.nextInt(1000000),8));
                 materialList.add(material);
        }
        return materialList;

    }

    /**
     * 初始化物料体积
     * @param materialList
     * @param b
     * @param m
     * @param s
     * @return
     */
    public static List<Material> initMaterialVolume(List<Material> materialList,double b,double m,double s){
        double b_volume = 5;//5立方
        double m_volume = 0.5;//0.5立方
        double s_volume = 0.05;//0.05立方
        double all = b + m + s;
        Random random = new Random();
        for (Material material : materialList){
             int i = random.nextInt((int)all*100);
             if (i<=s*100){
                 material.setVolume(Math.abs(NormalDistribution(1,(float) 500))/10000);
             }else if (i<=(m+s)*100){
                 material.setVolume(Math.abs(NormalDistribution(1,(float) 500))/1000);
             }else if (i<=(all*100)){
                 material.setVolume(Math.abs(NormalDistribution(1,(float) 500))/100);
             }
        }
         return materialList;
    }

    /**
     * 二八原则分配物料订单量
     * @param list
     * @param total
     * @return
     */
    public static List<Material> initMaterialNeedNum(List<Material> list,double total) {
        double b = total*0.8;
        double m = total*0.2;
        Random random = new Random();
        for (Material material:list){
            material.setNeedNum(Math.abs(NormalDistribution(1,(float) total*8)));
            material.setFrequency(Math.abs(NormalDistribution(1,(float) total*8)));
        }
        return list;
    }
    /**
     * 初始化物料价格
     * @param materialList
     * @param b
     * @param m
     * @param s
     * @return
     */
    public static List<Material> initMaterialPrice(List<Material> materialList,double b,double m,double s){
        double b_price = 1500;//5立方
        double m_price = 500;//0.5立方
        double s_price = 50;//0.05立方
        double all = b + m + s;
        for (Material material : materialList){
            Random random = new Random();
            int i = random.nextInt((int)all*100);
            if (i<=s*100){
                material.setPrice(Math.abs(NormalDistribution(1,(float) 5000))/100);
            }else if (i<=(m+s)*100){
                material.setPrice(Math.abs(NormalDistribution(1,(float) 5000))/10);
            }else if (i<=(all*100)){
                material.setPrice(Math.abs(NormalDistribution(1,(float) 5000))/3.33);
            }
        }
        return materialList;

    }


    /**
     * 补货
     * @param rdcPoint
     * @param netPoint
     * @param
     * @param
     * @return
     */
    private static List<City> chooseCity(List<City> rdcPoint, List<City> netPoint) {

        List<City> combination = new ArrayList<>();
        City city1 = new City();
        double distance = Integer.MAX_VALUE;
        for (City city:rdcPoint){
            double min = 0.0;
            for (City netPoints :netPoint){
                double num = Double.parseDouble(twoJuLi(city,netPoints))/1000;
                min += num;
            }
            if (min<distance){
                city1 = city;
                distance = min;
            }
            combination.add(city1);
        }
        for (City netPoints :netPoint){
            double max = Integer.MAX_VALUE;
            for (City city:rdcPoint){
                double num = Double.parseDouble(twoJuLi(city,netPoints))/1000;
                if (num<max){
                    netPoints.setCity1(city.getCity());
                    netPoints.setDistance(String.valueOf(num));
                    max = num;
                }
            }
        }
        return combination;
    }



    /**
     * 生成订单
     * @param
     * @return
     */
    public static List<Order> initOrders(List<Material> list,int orderLine) {
        List<Order> orderList = new ArrayList<>();
        Random random = new Random();
        for (int i=0;i<10000;i++){
                String OrderCode = RandomUtil.toFixdLengthString(random.nextInt(1000000),11);
                String orderDate = sdf1.format(randomDate("2021-01-01 08:00:00","2021-12-31 18:00:00"));
                int n =  (int)random(1,list.size());
                for (int l=0;l<n;l++) {
                    int m =  (int)random(1,list.size());
                    if (m <= orderLine) {
                        Order order = new Order();
                        order.setOrderCode(OrderCode);
                        order.setOrderDate(orderDate);
                        int z = random.nextInt(list.size());
                        order.setGoodsCode(list.get(z).getCode());
                        order.setGoodsNum((int) list.get(z).getNeedNum() / list.get(z).getFrequency() / list.get(z).getVolume());
                        orderList.add(order);
                    }
                }

        }
            return orderList;
    }
    private static Date randomDate(String beginDate, String endDate){
        try {
            Date start = sdf.parse(beginDate);
            Date end = sdf.parse(endDate);
            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
    /**
     * 生成客户
     * @param cities
     * @return
     */

    public static List<Customer> initCustomer(List<GlcPoint>cities,int customerNum) {
        Random random = new Random();
        List<Customer> customerList = new ArrayList<>();
        Double gdp = 0.0;
        for (GlcPoint glcPoint:cities){
            gdp += Double.parseDouble(glcPoint.getGdp());
        }
        for (GlcPoint glcPoint:cities){
            for (int i=0;i<=customerNum*Double.parseDouble(glcPoint.getGdp())/gdp;i++){
                Customer customer = new Customer();
                customer.setCustomerCode(RandomUtil.toFixdLengthString(random.nextInt(10000000),8));
                customer.setCity(glcPoint.getCity());
                customerList.add(customer);
            }
        }


        return customerList;
    }


    //普通正态随机分布
//参数 u 均值
//参数 v 方差
    public static double NormalDistribution(float u,float v){
        java.util.Random random = new java.util.Random();
        return Math.sqrt(v)*random.nextGaussian()+u;
    }

    /**
     * 为订单生产客户
     * @param orderList
     * @param customerList
     * @return
     */
    public static List<Order> initOrdersCustomerList(List<Order> orderList, List<Customer> customerList) {
        Random random = new Random();
        for (Order order:orderList){
            int i = random.nextInt(customerList.size());
            order.setCustomerCity(customerList.get(i).getCustomerCode());
            order.setCustomerCity(customerList.get(i).getCity());

        }
        return  orderList;
    }

    public static List<Result> run(List<Order> orders, List<Customer> customerList, List<GlcPoint> pointList) {
        List<Result> list = new ArrayList<>();


        return list;
    }






    /***
     * 计算运输成本
     * @param list
     * @param orders
     * @return
     */
    private double calculateTransportationCost(List<City> list, List<Order> orders) {
        double transportationCost = 0.0;
        Map<Date, List<Order>> orderList = orders.stream().collect(Collectors.groupingBy(Order::getDeliveryDate));
        for (Date date:orderList.keySet() ){
            List<Order> nowOrder = orderList.get(date);
            Map<String, List<Order>> customers = nowOrder.stream().collect(Collectors.groupingBy(Order::getCustomerCode));
            for(String customerCode:customers.keySet()){
                List<Order> customerOrder = customers.get(customerCode);
                double  customerGoodsNum = 0.0;
                for (Order order:customerOrder){
                    customerGoodsNum+=order.getGoodsNum();
                }
                double distance = 0.0;
                for (City city:list){
                    String code = city.getCode().trim();
                    if (customerCode.equals(code)){
                        distance = Double.parseDouble(city.getDistance());
                    }
                }
                transportationCost += (customerGoodsNum/60)*distance*0.213;
            }

        }
        return transportationCost;
    }


    /**
     * 从集合中取组合
     * @param list
     * @param k
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> combinations(List<T> list, int k) {
        if (k == 0 || list.isEmpty()) {//去除K大于list.size的情况。即取出长度不足K时清除此list
            return Collections.emptyList();
        }
        if (k == 1) {//递归调用最后分成的都是1个1个的，从这里面取出元素
            return list.stream().map(e -> Stream.of(e).collect(toList())).collect(toList());
        }
        Map<Boolean, List<T>> headAndTail = split(list, 1);
        List<T> head = headAndTail.get(true);
        List<T> tail = headAndTail.get(false);
        List<List<T>> c1 = combinations(tail, (k - 1)).stream().map(e -> {
            List<T> l = new ArrayList<>();
            l.addAll(head);
            l.addAll(e);
            return l;
        }).collect(toList());
        List<List<T>> c2 = combinations(tail, k);
        c1.addAll(c2);
        return c1;
    }

    /**
     *根据n将集合分成两组
     **/
    public static <T> Map<Boolean, List<T>> split(List<T> list, int n) {
        return IntStream
                .range(0, list.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, list.get(i)))
                .collect(partitioningBy(entry -> entry.getKey() < n, mapping(AbstractMap.SimpleEntry::getValue, toList())));
    }


    /**
     * 调用百度api接口，返回距离
     */

    public  static String twoJuLi(City city,City city1){
        StringBuilder originsParam=new StringBuilder();
        BigDecimal zuobiaoY1 = BigDecimal.valueOf(Double.valueOf(city.getLat()));
        BigDecimal zuobiaoX1 = BigDecimal.valueOf(Double.valueOf(city.getLng()));
        StringBuilder destinationsParam=new StringBuilder();
        originsParam.append(zuobiaoX1.setScale(8, BigDecimal.ROUND_HALF_UP));
        originsParam.append(",");
        originsParam.append(zuobiaoY1.setScale(8, BigDecimal.ROUND_HALF_UP));
        destinationsParam.append(BigDecimal.valueOf(Double.valueOf(city1.getLng())).setScale(8, BigDecimal.ROUND_HALF_UP));
        destinationsParam.append(",");
        destinationsParam.append(BigDecimal.valueOf(Double.valueOf(city1.getLat())).setScale(8, BigDecimal.ROUND_HALF_UP));

        //调用百度地图api
        List<JSONObject> result = new ArrayList<JSONObject>();
        //   destinationsParam.deleteCharAt(destinationsParam.lastIndexOf("|"));
        String url = String.format("http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=%s&destinations=%s&ak=%s", URLEncoder.encode(originsParam.toString()), URLEncoder.encode(destinationsParam.toString()), "NURx5GUAQ1IBZVBMCQSpYppRsDdTpbxG");
        String juli=null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = client.execute(httpGet);
            int status = 200;
            if (httpResponse.getStatusLine().getStatusCode() == status) {
                HttpEntity httpEntity = httpResponse.getEntity();
                String text = EntityUtils.toString(httpEntity);//取出应答字符串
                JSONObject json = JSONObject.parseObject(text);

                String i1=null;
                JSONArray array = json.getJSONArray("result");

                JSONObject distance = array.getJSONObject(0);
                //   distance.put("node", destinations.get(i));
                //   result.add(distance);

                juli=distance.getJSONObject("distance").getString("value");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return juli;

    }
}
