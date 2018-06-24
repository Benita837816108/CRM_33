package com.itheima.crm.service.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public PageBean getpageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //封装结果集
        Integer totalCount=customerDao.getTotalCount(dc);
        PageBean<Customer> pageBean=new PageBean<>(currentPage,totalCount,pageSize);
        pageBean.setList(customerDao.getPageList1(dc,pageBean.getStart(),pageBean.getPageSize()));
        return pageBean;
    }

    @Override
    public Customer findCustomerById(Long cust_id) {
        Customer c = customerDao.getById(cust_id);
        return c;
    }

    @Override
    public void deleteCustomerById(Customer c) {
        customerDao.delete(c);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list=customerDao.findAll();
        return list;
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }


}
