package com.ruoyi.web.controller.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模态窗口
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/demo/modal")
public class DemoDialogController
{
    private String prefix = "demo/modal";

    /**
     * 模态窗口
     */
    @GetMapping("/dialog")
    public String dialog()
    {
        return prefix + "/dialog";
    }

    /**
     * 弹层组件
     */
    @GetMapping("/layer")
    public String layer()
    {
        return prefix + "/layer";
    }

    /**
     * 表单
     */
    @GetMapping("/form")
    public String form()
    {
        return prefix + "/form";
    }

    /**
     * 表格
     */
    @GetMapping("/table")
    public String table()
    {
        return prefix + "/table";
    }

    /**
     * 表格check
     */
    @GetMapping("/check")
    public String check()
    {
        return prefix + "/table/check";
    }

    /**
     * 表格radio
     */
    @GetMapping("/radio")
    public String radio()
    {
        return prefix + "/table/radio";
    }

    /**
     * 表格radio
     */
    @GetMapping("/demo1")
    public String demo1()
    {
        return prefix + "/table/demo1";
    }

    /**
     * 表格radio
     */
    @GetMapping("/demo2")
    public String demo2()
    {
        return prefix + "/table/demo2";
    }

    /**
     * 表格radio
     */
    @GetMapping("/demo3")
    public String demo3()
    {
        return prefix + "/table/demo3";
    }

    /**
     * 表格radio
     */
    @GetMapping("/demo4")
    public String demo4()
    {
        return prefix + "/table/demo4";
    }

    /**
     * 表格radio
     */
    @GetMapping("/demo5")
    public String demo5()
    {
        return prefix + "/table/demo5";
    }

    /**
     * 表格radio
     */
    @GetMapping("/demo6")
    public String demo6()
    {
        return prefix + "/table/demo6";
    }

    /**
     * 表格回传父窗体
     */
    @GetMapping("/parent")
    public String parent()
    {
        return prefix + "/table/parent";
    }
}
