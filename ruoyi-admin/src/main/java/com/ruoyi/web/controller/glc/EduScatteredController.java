package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduABC;
import com.ruoyi.network.service.IGlcDeliveryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/scattered")
public class EduScatteredController extends BaseController
{
    private String prefix = "system/scattered";

    @Autowired
    private IGlcDeliveryService glcDeliveryService;

    @RequiresPermissions("system:scattered:view")
    @GetMapping()
    public String eiq()
    {
        return prefix + "/scattered";
    }

    @RequiresPermissions("system:scattered:list")
    @PostMapping("/scattered/bar1")
    @ResponseBody
    public  TableDataInfo bar1(HttpServletRequest request)
    {
        startPage();
        Long userId = 0L;
        Cookie[]cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("prjId")){
                    userId = Long.parseLong(cookie.getValue());
                }
            }
        }
        List<EduABC> list = glcDeliveryService.getGoodsNameList(userId);
        return getDataTable(list);
    }

}
