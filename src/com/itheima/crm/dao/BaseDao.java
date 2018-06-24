package com.itheima.crm.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    //增
    void save(T t);
    //改
    void update(T t);
    //删
    void delete(T t);
    //删
    void delete(Serializable id);
    //查 通过id查
    T getById(Serializable id);
    //查  符合条件的总记录数
    Integer getTotalCount(DetachedCriteria dc);
    //查 分页列表
    List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSiz);
    //查询所有
    public List<T> findAll();

}
