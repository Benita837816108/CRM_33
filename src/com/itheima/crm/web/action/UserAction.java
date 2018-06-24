package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    @Autowired
    private UserService userService;
    private User user= new User();
    @Override
    public User getModel() {
        return user;
    }
    public String save(){
        userService.save(user);
         return "save";
    }
    public String login(){
        User existUser=userService.login(user);
        if (existUser==null){
           this.addActionError("您输入的用户名或密码有误");
            return "error";
        }else{
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return "login";
        }

    }
}
