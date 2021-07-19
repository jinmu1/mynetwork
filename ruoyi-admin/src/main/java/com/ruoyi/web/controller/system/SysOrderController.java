package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ruoyi.system.utils.GlobalConfig;
import com.ruoyi.system.utils.TenpayUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.network.service.ISysOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信支付订单Controller
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
@Controller
@RequestMapping("/system/wxorder")
public class SysOrderController extends BaseController {
    private String prefix = "system/wxorder";

    @Autowired
    private ISysOrderService sysOrderService;

    @RequiresPermissions("system:wxorder:view")
    @GetMapping()
    public String order() {
        return prefix + "/order";
    }

    /**
     * 查询微信支付订单列表
     */
    @RequiresPermissions("system:wxorder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysOrder sysOrder) {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出微信支付订单列表
     */
    @RequiresPermissions("system:wxorder:export")
    @Log(title = "微信支付订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysOrder sysOrder) {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增微信支付订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存微信支付订单
     */
    @RequiresPermissions("system:wxorder:add")
    @Log(title = "微信支付订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysOrder sysOrder) {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改微信支付订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SysOrder sysOrder = sysOrderService.selectSysOrderById(id);
        mmap.put("sysOrder", sysOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信支付订单
     */
    @RequiresPermissions("system:wxorder:edit")
    @Log(title = "微信支付订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysOrder sysOrder) {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除微信支付订单
     */
    @RequiresPermissions("system:wxorder:remove")
    @Log(title = "微信支付订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysOrderService.deleteSysOrderByIds(ids));
    }


    /**
     * 新增保存微信支付订单
     */
    @Log(title = "微信支付订单", businessType = BusinessType.INSERT)
    @GetMapping("/addOrder")
    @ResponseBody
    public String addOrder(HttpServletRequest request) throws JDOMException, IOException {

        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = TenpayUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;

        String body = "vip1";
        String out_trade_no ="215";
        String order_price = request.getParameter("price");
        String spbill_create_ip = request.getRemoteAddr();
        String notify_url = GlobalConfig.URL;

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", GlobalConfig.APPID);
        packageParams.put("mch_id", GlobalConfig.MCH_ID);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", "100");
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", GlobalConfig.TRADE_TYPE);

        String code_url = sysOrderService.getUrlCode(packageParams);

        if (code_url.equals(""))
            System.err.println(sysOrderService.getResponseMessage());

        return code_url;
    }


}

