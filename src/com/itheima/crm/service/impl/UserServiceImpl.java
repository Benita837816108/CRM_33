package com.itheima.crm.service.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.itheima.crm.utils.MD5Utils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void save(User user) {
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        user.setUser_state('1');
        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User login(User user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        criteria.add(Restrictions.eq("user_code",user.getUser_code()));
        criteria.add(Restrictions.eq("user_password",user.getUser_password()));
        criteria.add(Restrictions.eq("user_state",'1'));
        user = userDao.login(criteria);
        return user;
    }
}
