package com.zly.controller;

import com.zly.Service.UserService;
import com.zly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String home(HttpSession httpSession){
        Object token = httpSession.getAttribute("token");
        if (token==null){
            return "login";
        }
        if (token.equals("loginSuccess")){
            return "index";
        }else {
            return "login";
        }
    }
    @RequestMapping("/login")
    public String login(HttpServletRequest httpServletRequest,HttpSession httpSession) throws InterruptedException {
        String username = httpServletRequest.getParameter("username");
        System.out.println("username = " + username);
        String password = httpServletRequest.getParameter("password");
        System.out.println("password = " + password);
        if (!Objects.equals(username, "") && !Objects.equals(password, "")&&username!=null){
            User user = userService.findByUsername(username);
            if (user.getPassword().equals(password)){
                //验证密码成功
                httpSession.setAttribute("token","loginSuccess");
                Thread.sleep(1000);
                return "redirect:/";
            }else {
                return "redirect:/";
            }
        }else {
            return "login";
        }
    }
    @PostMapping("/addUser")
    public String addUser(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        if(username!=null&&password!=null){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.addUser(user);
            return "redirect:/login";
        }else {
            return "redirect:/login";
        }
    }
}
