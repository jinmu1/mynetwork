package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.util.*;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.utils.GlobalConfig;
import com.ruoyi.system.utils.MD5Util;
import com.ruoyi.system.utils.XMLUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.network.service.ISysOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 微信支付订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService 
{
    @Autowired
    private SysOrderMapper sysOrderMapper;
    private String responseMessage;
    public static String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 查询微信支付订单
     * 
     * @param id 微信支付订单ID
     * @return 微信支付订单
     */
    @Override
    public SysOrder selectSysOrderById(Long id)
    {
        return sysOrderMapper.selectSysOrderById(id);
    }

    /**
     * 查询微信支付订单列表
     * 
     * @param sysOrder 微信支付订单
     * @return 微信支付订单
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder)
    {
        return sysOrderMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 新增微信支付订单
     * 
     * @param sysOrder 微信支付订单
     * @return 结果
     */
    @Override
    public int insertSysOrder(SysOrder sysOrder)
    {
        sysOrder.setCreateTime(DateUtils.getNowDate());
        return sysOrderMapper.insertSysOrder(sysOrder);
    }

    /**
     * 修改微信支付订单
     * 
     * @param sysOrder 微信支付订单
     * @return 结果
     */
    @Override
    public int updateSysOrder(SysOrder sysOrder)
    {
        return sysOrderMapper.updateSysOrder(sysOrder);
    }

    /**
     * 删除微信支付订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysOrderByIds(String ids)
    {
        return sysOrderMapper.deleteSysOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信支付订单信息
     * 
     * @param id 微信支付订单ID
     * @return 结果
     */
    @Override
    public int deleteSysOrderById(Long id)
    {
        return sysOrderMapper.deleteSysOrderById(id);
    }

    @Override
    public String getUrlCode(SortedMap<String, String> packageParams) throws JDOMException, IOException {
        String sign = createSign(packageParams);
        String xml = "<xml>" + "<appid>" + packageParams.get("appid") + "</appid>" + "<mch_id>"
                + packageParams.get("mch_id") + "</mch_id>" + "<nonce_str>" + packageParams.get("nonce_str")
                + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + packageParams.get("body")
                + "]]></body>" + "<out_trade_no>" + packageParams.get("out_trade_no") + "</out_trade_no>"
                + "<total_fee>" + packageParams.get("total_fee") + "</total_fee>" + "<spbill_create_ip>"
                + packageParams.get("spbill_create_ip") + "</spbill_create_ip>" + "<notify_url>"
                + packageParams.get("notify_url") + "</notify_url>" + "<trade_type>" + packageParams.get("trade_type")
                + "</trade_type>" + "</xml>";
        System.out.println(xml);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(xml, "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            responseMessage = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<?, ?> map = XMLUtil.doXMLParse(responseMessage);
        String codeUrl = map.get("code_url").toString();
        response.close();
        httpClient.close();
        return codeUrl;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public String createSign(SortedMap<String, String> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + GlobalConfig.KEY);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }
    @Override
    public String getResponseMessage() {
        return responseMessage;
    }
}
