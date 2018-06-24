package com.itheima.crm.service;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    PageBean getpageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);


    Customer findCustomerById(Long cust_id);

    void deleteCustomerById(Customer c);


    List<Customer> findAll();

    void update(Customer customer);
}
