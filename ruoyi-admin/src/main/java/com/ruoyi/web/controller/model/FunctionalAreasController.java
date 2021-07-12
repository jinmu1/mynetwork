package com.ruoyi.web.controller.model;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.EduABC;
import com.ruoyi.system.service.IGlcDeliveryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 各功能区面积Controller
 * 
 * @author 金木
 * @date 2021-06-05
 */
@Controller
@RequestMapping("/system/areas")
public class FunctionalAreasController extends BaseController
{


    @Autowired
    private IGlcDeliveryService glcDeliveryService;


    /**
     * 生产各功能区数据
     */
//    @RequiresPermissions("system:areas:list")
    @PostMapping("/areas/bar1")
    @ResponseBody
    public  void list( HttpServletRequest request)
    {


    }

}
