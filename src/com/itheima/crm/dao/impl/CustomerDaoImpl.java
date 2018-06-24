package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;


public class CustomerDaoImpl  extends BaseDaoImpl<Customer> implements CustomerDao {

    public List<Customer> getPageList1(DetachedCriteria dc, Integer start, Integer pageSiz) {
        dc.setProjection(null);
        List<Customer> list = (List<Customer>) getHibernateTemplate().findByCriteria(dc, start, pageSiz);
        return list;
    }

}
