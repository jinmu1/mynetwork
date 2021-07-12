package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.system.domain.EduDelivery;
import com.ruoyi.system.domain.EduReport;
import com.ruoyi.system.service.IEduDeliveryService;
import com.ruoyi.system.service.IEduReceivingService;
import com.ruoyi.system.service.IEduReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/productivity")
public class EduProductivityController extends BaseController
{
    private String prefix = "system/productivity";

    @Autowired
    private IEduReportService eduReportService;


    @RequiresPermissions("system:productivity:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/productivity";
    }

    @RequiresPermissions("system:productivity:list")
    @PostMapping("/productivity/bar1")
    @ResponseBody
    public  TableDataInfo list( HttpServletRequest request)
    {
        startPage();
        String batchId = "";
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    batchId = cookie.getValue();
                }
            }
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", String.valueOf(ShiroUtils.getSysUser().getUserId()));
        map.put("batchId",batchId);
        List<EduReport> list = eduReportService.getReports(map);
        if (list.size()==0) {
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("batchId", batchId);
            list = eduReportService.getReports1(map);
        }
        return getDataTable(list);
    }

}
