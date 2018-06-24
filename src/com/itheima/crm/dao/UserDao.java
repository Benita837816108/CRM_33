package com.itheima.crm.dao;

import com.itheima.crm.domain.User;
import org.hibernate.criterion.DetachedCriteria;

public interface UserDao extends BaseDao<User>{
    User login(DetachedCriteria criteria);
}
