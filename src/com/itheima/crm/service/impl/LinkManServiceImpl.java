package com.itheima.crm.service.impl;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.service.LinkManService;
import com.itheima.crm.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LinkManServiceImpl implements LinkManService {
    @Autowired
    private LinkManDao linkManDao;


    @Override
    public LinkMan findLinkManById(Long lkm_id) {
        LinkMan linkMan = linkManDao.getById(lkm_id);
        return linkMan;
    }

    @Override
    public void delete(LinkMan lm) {
        linkManDao.delete(lm);
    }

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    @Override
    public PageBean<LinkMan> fingByPage(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        //封装结果集
        Integer totalCount=linkManDao.getTotalCount(dc);
        System.out.println(totalCount);
        PageBean<LinkMan> pageBean= new PageBean<>(currentPage,totalCount,pageSize);
        List<LinkMan> list = linkManDao.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }


}
