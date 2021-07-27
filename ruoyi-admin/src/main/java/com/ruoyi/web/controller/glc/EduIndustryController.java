package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduReceiving;
import com.ruoyi.system.service.IEduDeliveryService;
import com.ruoyi.system.service.IEduReceivingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/industry")
public class EduIndustryController extends BaseController
{
    private String prefix = "system/industry";

    @Autowired
    private IEduDeliveryService deliveryService;

    @Autowired
    private IEduReceivingService receivingService;

    @RequiresPermissions("system:industry:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/industry";
    }

    @RequiresPermissions("system:industry:list")
    @PostMapping("/bar1")
    @ResponseBody
    public  TableDataInfo list( HttpServletRequest request)
    {
        startPage();
        List<EduReceiving> list = receivingService.getIndustry();
        return getDataTable(list);
    }


}
