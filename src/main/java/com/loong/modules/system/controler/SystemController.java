package com.loong.modules.system.controler;

import com.loong.commons.security.Digest;
import com.loong.modules.system.entity.User;
import com.loong.modules.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    UserService userService;

    @RequestMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "ttl");
        return "hello";
    }


    @RequestMapping("list")
    @ResponseBody
    public List<User> list() {
        return userService.getAll();
    }

    /**
     * @Description: 登陆功能
     * @Param: [username, password]
     * @return: java.lang.String
     * @Author: tlw
     * @Created: 2019/6/2-14:14
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        ShiroHttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "redirect:login.jsp";
            }
        }
        javax.servlet.http.HttpSession session = request.getSession();
        User user = userService.getUserByLoginName(username);
        session.setAttribute("currentUser",user);
        System.out.println("用户：" + username + "---" + "密码：" + password);
        return "redirect:list.jsp";
    }

    /**
     * 修改密码页面
     * @return
     */
    @RequestMapping("toModifiedPwd")
    public String toModifiedPwd(){
        return "system/modifiedPwd";
    }

    @RequestMapping("modifiedPwd")
    public String modifiedPwd(@RequestParam("password") String password, HttpServletRequest request){
        System.out.println("新密码为："+password);
        String pwd = Digest.generatePwd(password);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        System.out.println("加密密码为："+pwd);
        user.setPassword(pwd);
        userService.savePassword(user);
        return "redirect:list.jsp";
    }
}
