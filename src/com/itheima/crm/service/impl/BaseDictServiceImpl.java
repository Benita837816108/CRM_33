package com.itheima.crm.service.impl;


import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictDao baseDictDao;
    @Override

    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        return baseDictDao.getListByTypeCode(dict_type_code);
    }
}
