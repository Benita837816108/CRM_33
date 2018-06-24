package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class clazz;

    public BaseDaoImpl() {
        //带有泛型类型的父类
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
       clazz= (Class) parameterizedType.getActualTypeArguments()[0];
    }
    @Autowired
    public void setDi(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory); // 初始化HibernateDaoSupport中的SessionFactory
    }
    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
    getHibernateTemplate().update(t);
    }

    @Override
    public void delete(T t) {
    getHibernateTemplate().delete(t);
    }

    @Override
    public void delete(Serializable id) {
        T t = getById(id);//先取再删
        getHibernateTemplate().delete(t);
    }

    @Override
    public T getById(Serializable id) {
        T t = (T) getHibernateTemplate().get(clazz, id);
        return t;
    }

    @Override
    public Integer getTotalCount(DetachedCriteria dc) {
        //设置查询的聚合函数,总记录数
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
        dc.setProjection(null);
        if(list!=null&&list.size()>0){
            Long count = list.get(0);
            return count.intValue();
        }else {
            return null;
        }
    }

    @Override
    public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSiz) {
        dc.setProjection(null);
        List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSiz);
        return list;
    }
    @Override
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
    }
}
