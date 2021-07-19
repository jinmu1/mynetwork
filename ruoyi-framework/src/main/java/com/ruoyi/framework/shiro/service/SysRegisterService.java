package com.ruoyi.framework.shiro.service;


import com.ruoyi.system.utils.WeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.network.service.ISysUserService;

import java.util.Map;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    private String appid = "wxf6c9659d0d77eb14";
    private String secret = "381bf9a88326f0c90a5f7184d57878bf";
    private String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String wxInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 注册
     */
    public String register(SysUser user)
    {
        String msg = "", loginName = user.getLoginName(), password = user.getPassword(),email = user.getEmail(),company=user.getCompany();

        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "验证码错误";
        }
        else if (StringUtils.isEmpty(loginName))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (StringUtils.isEmpty(email))
        {
            msg = "用户密码不能为空";
        }
        else if (StringUtils.isEmpty(company))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(loginName)))
        {
            msg = "保存用户'" + loginName + "'失败，注册账号已存在";
        }
        else
        {
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setUserName(loginName);
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
            boolean regFlag = userService.registerUser(user);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    public String add(SysUser user) {
        String msg = "", loginName = user.getLoginName(), password = user.getPassword(),email = user.getEmail(),company = user.getCompany();
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            msg = "验证码错误";
        }
        else if (StringUtils.isEmpty(loginName))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(company)){
            msg = "公司名称不能为空";
        }
        else if (StringUtils.isEmpty(email))
        {
            msg = "用户密码不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(loginName)))
        {
            msg = "保存用户'" + loginName + "'失败，注册账号已存在";
        }
        else
        {
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setUserName(loginName);
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
            user.setUserType(UserConstants.REGISTER_USER_TYPE);
            boolean regFlag = userService.registerUser(user);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.REGISTER, MessageUtils.message("user.register.success")));
                msg = "success";
            }
        }
        return msg;
    }

    public SysUser wxLogin(String code) {
        SysUser user = null;
        String msg = "";
        //1.根据code获取access_token和openId
        String atUtl = accessTokenUrl + "?code="+code+"&appid="+appid+"&secret="+secret+"&grant_type=authorization_code";
        System.out.println(atUtl);
        Map<String, Object> map1 = null;
        try {
            map1 = WeUtil.sendGet(atUtl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object access_token = map1.get("access_token");
        Object openid = map1.get("openid").toString();
        if(access_token == null && openid == null) {
            user.setOpenid(openid.toString());
            return user;
        }
        //2.根据openId判断用户是否存在
        user = userService.findByOpenid(openid.toString());

        if(user != null) {
            //3.如果用户存在返回用户信息
            return user;
        }else{
            //4.如果用户不存在，根据access_token和openId获取微信用户信息
            String wxurl = wxInfoUrl + "?access_token=" + access_token +"&openid="+openid;
            Map<String, Object> map2 = null;
            try {
                map2 = WeUtil.sendGet(wxurl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object nickname = map2.get("nickname");
            Object headimgurl = map2.get("headimgurl");
            if(nickname == null || headimgurl == null) {
                return user;
            }
            //5.将微信用户信息保存到数据库，返回用户数据
            user = new SysUser();
            user.setOpenid(openid.toString());//用户openid
            user.setLoginName(nickname.toString());//用户名
            user.setAvatar(headimgurl.toString());//用户头像
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setUserName(nickname.toString());
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), openid.toString(), user.getSalt()));
            boolean regFlag = userService.registerUser(user);
            return user;
        }

    }

    public  SysUser getUserByOpenid(String openid) {
        return userService.findByOpenid(openid.toString());
    }
}
