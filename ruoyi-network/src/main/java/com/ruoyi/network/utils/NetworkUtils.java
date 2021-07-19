package com.ruoyi.network.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.network.enumType.Car;
import com.ruoyi.network.form.GlcPoint;
import com.ruoyi.network.node.*;
import com.ruoyi.network.result.Result;
import com.ruoyi.network.result.ResultMsg;
import com.ruoyi.network.utils.MathUtils;
import com.ruoyi.network.utils.RandomUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
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
        double sale_accout = 0.0;
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
                    transportCost += Double.parseDouble(city.getDistance()) * 0.89 * 1.09 * transportNum * 1.2 * 1 * 1.5;//计算运输成本
                }
                if (transportCost<max){
                    max = transportCost;
                    number = i;
                }

            }
        }

            return max;
    }

    public static double getSaleAccount(double h_price,double m_price,double l_price,double transportNUm){
        List<Material> list  = NetworkUtils.createGoods(6000); //创建物料
        List<Material> list1 = NetworkUtils.initMaterialVolume(list,10,50,40);//创建体积
        List<Material> list2 = NetworkUtils.initMaterialPrice(list1,h_price,m_price,l_price);//创建价值
        List<Material> list3 = NetworkUtils.initMaterialNeedNum(list2,transportNUm);

        double saleAccount = 0.0;
        for (Material material:list3){
            saleAccount +=material.getPrice() * material.getNeedNum();
        }
        return saleAccount;
    }

    public static double getNetWorkData1(String carType,double  transportNum, List<GlcPoint> list){
        double sale_accout = 0.0;
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
        return sale_accout;
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
        double all = b + m + s;
        Random random = new Random();
        materialList =  materialList.stream().sorted(Comparator.comparing(Material::getPrice)).collect(Collectors.toList());
        double num3 = 0.0;
        int i = 0;
        for (Material material : materialList){
             if (i<=s/all*materialList.size()){
                 material.setVolume(initNormalDistribution1(100,0.05)[(int)NetworkUtils.random(0,99)]);
                 num3 += material.getVolume();
             }else if (i<=(m+s)/all*materialList.size()){
                 material.setVolume(initNormalDistribution1(100,0.45)[(int)NetworkUtils.random(0,99)]+0.05);
                 num3 += material.getVolume();
             }else if (i<=materialList.size()){
                 material.setVolume(initNormalDistribution1(100,4.5)[(int)NetworkUtils.random(0,99)]+0.5);
                 num3 += material.getVolume();
             }
             i++;
        }
         System.out.println(num3);
         return materialList;
    }

    /**
     * 二八原则分配物料订单量
     * @param list
     * @param total
     * @return
     */
    public static List<Material> initMaterialNeedNum(List<Material> list,double total) {
        for (Material material:list){
            material.setNeedNum(initNormalDistribution(10000,total)[0]);
            material.setOrderNum(material.getNeedNum()/material.getVolume());
            material.setFrequency(initNormalDistribution(10000,material.getNeedNum())[0]);
        }
        return list;
    }

    public static double[] initNormalDistribution(int length,double max){
        double[] num = new double[length];
        for (int i = 0;i<length;i+=1){
            num[i] = Math.abs(NetworkUtils.NormalDistribution(50,(float)1000));
        }
        double avg = MathUtils.sum(num);
        for (int i = 0; i<num.length;i++){
            num[i] = num[i]*max/avg;
        }
        return num;
    }
    public static double[] initNormalDistribution1(int length,double max){
        double[] num = new double[length];
        for (int i = 0;i<length;i+=1){
            num[i] = Math.abs(NetworkUtils.NormalDistribution(50,(float)1000));
        }
        double avg = MathUtils.sum(num)/num.length;
        for (int i = 0; i<num.length;i++){
            num[i] = num[i]*max/avg;
        }
        return num;
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
                material.setPrice(initNormalDistribution1(10000,50)[0]);
            }else if (i<=(m+s)*100){
                material.setPrice(initNormalDistribution1(10000,450)[0]+50);
            }else if (i<=(all*100)){
                material.setPrice(initNormalDistribution1(10000,1000)[0]+500);
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
    public static List<Order> initOrders(List<Material> list, double transportNum)  {
        List<Order> orderList = new ArrayList<>();
        Random random = new Random();
        list = list.stream().sorted(Comparator.comparing(Material::getVolume)).collect(Collectors.toList());
        double[] nums = initNormalDistribution(list.size(),transportNum);
        for (int i = 0;i<list.size();i++){
            list.get(i).setNeedNum(nums[i]);
        }
        double num3 = 0.0;
        for (Material material:list) {
            double num = material.getNeedNum();
            for (int i = 0; i < material.getNeedNum()*10; i+=(int)random.nextInt(5) ) {
                long numd =  (long)((num*1.1*1.25*1.5)/material.getVolume())/365;
                long num2 = (long)((num*1.1*1.25*1.5)/material.getVolume());

                if(num>0&&num2>1) {
                    double num1 = NetworkUtils.random(numd,num2);
                    num =  num - num1*material.getVolume()/(1.1*1.25*1.5);
                    num3 += num1*material.getVolume()/(1.1*1.25*1.5);
                    orderList.add(new Order(material.getCode(),num1,material.getVolume(),material.getPrice()));
                }else if (num>0&&num2==1){
                    num = 0;
                    orderList.add(new Order(material.getCode(),num2,material.getVolume(),material.getPrice()));
                }


            }
        }
        System.out.println(num3);
        for (Order order:orderList) {
            String OrderCode = RandomUtil.toFixdLengthString(random.nextInt(1000),5);
            String orderDate = sdf1.format(randomDate("2021-01-01 08:00:00","2021-12-31 18:00:00"));

                order.setOrderCode(OrderCode);
                try {
                    order.setDeliveryDate(sdf1.parse(orderDate));
                } catch (ParseException e) {
                    e.printStackTrace();
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

    public static List<Customer> initCustomer(List<GlcPoint>cities, int customerNum) {
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
                customer.setCity(new City(glcPoint.getCity(),glcPoint.getLat(),glcPoint.getLng(),glcPoint.getGdp()));
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
            order.setCustomerCode(customerList.get(i).getCustomerCode());
            order.setCustomerCity(customerList.get(i).getCity());

        }
        return  orderList;
    }

    public static List<Result> run(List<Order> orders, List<Customer> customerList, List<GlcPoint> pointList) {
        List<Result> list = new ArrayList<>();


        return list;
    }

    public static List<Supplier> initSupplier() {
        return new ArrayList<>();
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
