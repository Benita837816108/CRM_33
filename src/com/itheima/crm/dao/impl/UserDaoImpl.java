package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {


    @Override
    public User login(DetachedCriteria criteria) {
        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(criteria);

        return list.isEmpty()?null:list.get(0);

    }


}
