package com.itheima.crm.service;

import com.itheima.crm.domain.BaseDict;

import java.util.List;

public interface BaseDictService {
    //根据数据字典类型获得数据字典对象
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
