package com.ruoyi.web.controller.glc;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.system.domain.BlogContent;
import com.ruoyi.network.service.IBlogContentService;
import com.ruoyi.common.core.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
@Controller
public class BlogContentController extends BaseController
{


    @Autowired
    private IBlogContentService blogContentService;

    @GetMapping("/account/blog/open/post")
    @ResponseBody
    public HashMap<String,Object> open(HttpServletRequest req){
        BlogContent contentDO =blogContentService.selectBlogContentById(Long.parseLong(req.getParameter("id")));
        HashMap<String,Object> pm1 = new HashMap<>();
        pm1.put("content",contentDO.getContent());
        return pm1;
    }

    @GetMapping("/account/blog/open/list")
    @ResponseBody
    public List<BlogContent> getList(){
        BlogContent blogContent = new BlogContent();
        blogContent.setDel(1);
        return blogContentService.selectList(blogContent);
    }

    @PostMapping("/account/blog/eidt")
    @ResponseBody
    public AjaxResult saveBlog(HttpServletRequest request){
            BlogContent contentDO = new BlogContent();
            contentDO.setTitle(request.getParameter("title"));
            contentDO.setContent(request.getParameter("content"));
            contentDO.setType((long) 1);
            contentDO.setDel(1);
            blogContentService.insertBlogContent(contentDO);
        return AjaxResult.success("提交成功");
    }
}
