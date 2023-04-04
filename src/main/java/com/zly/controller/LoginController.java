package com.zly.controller;

import com.zly.Service.UserService;
import com.zly.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        String password = httpServletRequest.getParameter("password");
        if (username==null||password==null){
            return "login";
        }else {
            User user = userService.findByUsername(username);
            if (user.getPassword().equals(password)){
                //验证密码成功
                httpSession.setAttribute("token","loginSuccess");
                Thread.sleep(1000);
                return "index";
            }else {
                return "/";
            }
        }
    }
    @PostMapping("/addUser")
    public String addUser(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);
        return "redirect:/login";
    }
}
