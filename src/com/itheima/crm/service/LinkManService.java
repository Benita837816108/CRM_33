package com.itheima.crm.service;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {



    void delete(LinkMan lm);


    void save(LinkMan linkMan);

    PageBean<LinkMan> fingByPage(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    LinkMan findLinkManById(Long lkm_id);
}
