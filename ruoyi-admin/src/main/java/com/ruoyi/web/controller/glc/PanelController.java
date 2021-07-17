package com.ruoyi.web.controller.glc;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 出库单Controller
 * 
 * @author ruoyi
 * @date 2021-01-10
 */
@Controller
@RequestMapping("/system/panel")
public class PanelController extends BaseController
{
    private String prefix = "system/panel";


    @GetMapping("/demo1")
    public String demo1()
    {
        return prefix + "/demo1";
    }



    @GetMapping("/demo2")
    public String demo2()
    {
        return prefix + "/demo2";
    }


    @GetMapping("/demo3")
    public String demo3()
    {
        return prefix + "/demo3";
    }


    @GetMapping("/demo4")
    public String demo4()
    {
        return prefix + "/demo4";
    }


    @GetMapping("/demo5")
public String demo5()
{
    return prefix + "/demo5";
}

    @GetMapping("/demo6")
    public String demo6()
    {
        return prefix + "/demo6";
    }


    @GetMapping("/demo7")
    public String demo7()
    {
        return prefix + "/demo7";
    }


    @GetMapping("/demo8")
    public String demo8()
    {
        return prefix + "/demo8";
    }


    @GetMapping("/demo9")
    public String demo9()
    {
        return prefix + "/demo9";
    }

    @GetMapping("/demo10")
    public String demo10()
    {
        return prefix + "/demo10";
    }


}
