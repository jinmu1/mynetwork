package com.ruoyi.production.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.production.form.City;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public final class JuLiUtils {
    /**
     * 调用百度api接口，返回距离
     */

    public static Double twoJuLi(City city, City city1) {
        double juli = 0.0;
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.select(3);
        Boolean exists = jedis.exists(city.getCity() + ":" + city1.getCity());
        if (exists) {
            juli = Double.parseDouble(jedis.get(city.getCity() + ":" + city1.getCity()));

        } else if (jedis.exists(city1.getCity() + ":" + city.getCity())) {
            juli = Double.parseDouble(jedis.get(city1.getCity() + ":" + city.getCity()));
        } else {
            StringBuilder originsParam = new StringBuilder();
            BigDecimal zuobiaoY1 = BigDecimal.valueOf(Double.valueOf(city.getLat()));
            BigDecimal zuobiaoX1 = BigDecimal.valueOf(Double.valueOf(city.getLng()));
            StringBuilder destinationsParam = new StringBuilder();
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
            CloseableHttpClient client = HttpClients.createDefault();
            try {
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = client.execute(httpGet);
                int status = 200;
                if (httpResponse.getStatusLine().getStatusCode() == status) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String text = EntityUtils.toString(httpEntity);//取出应答字符串
                    JSONObject json = JSONObject.parseObject(text);
                    String i1 = null;
                    JSONArray array = json.getJSONArray("result");
                    JSONObject distance = array.getJSONObject(0);
                    //   distance.put("node", destinations.get(i));
                    //   result.add(distance);
                    juli = Double.parseDouble(distance.getJSONObject("distance").getString("value"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            jedis.select(3);
            jedis.set(city.getCity() + ":" + city1.getCity(), JSON.toJSON(juli).toString());

        }
        jedis.close();
        return juli;

    }

    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {

        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }
}
