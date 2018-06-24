package com.itheima.crm.web.action;


import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.itheima.crm.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Queue;


public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    @Autowired
    private CustomerService customerService;
    private LinkMan linkMan = new LinkMan();
    @Autowired
    private LinkManService linkManService;
    private Integer currentPage;
    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    public String saveUI() {
        initCustomer();
        return "saveUI";
    }
    public String save() {
        linkManService.save(linkMan);
        return "save";
    }

    public String fingByPage() {
     DetachedCriteria dc=DetachedCriteria.forClass(LinkMan.class);
       PageBean<LinkMan> pageBean = linkManService.fingByPage(dc,currentPage,pageSize);
       ActionContext.getContext().getValueStack().push(pageBean);
        return "findBypage";
    }
    public String delete() {

        LinkMan lm = linkManService.findLinkManById(linkMan.getLkm_id());

        //删除数据库
        linkManService.delete(lm);
        return "delete";
    }


        public void initCustomer(){
            // 同步 ：查询所有的客户，将客户信息显示到页面的select中
            List<Customer> list = customerService.findAll();
            ServletActionContext.getContext().getValueStack().set("list", list); // 存root栈
        }




}
