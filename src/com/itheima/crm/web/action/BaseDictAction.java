package com.itheima.crm.web.action;


import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    private BaseDict baseDict =new BaseDict();
    @Autowired
    private BaseDictService baseDictService;
   
   public String  execute() throws Exception {
       String dict_type_code = baseDict.getDict_type_code();
       //调用service根据typecode获取数据u字典对象的list
      List<BaseDict> list=baseDictService.getListByTypeCode(dict_type_code);
      //将list转换成json格式
       String json = JSONArray.fromObject(list).toString();
       //将json发送给浏览器
       //解决中文乱码
       ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
       ServletActionContext.getResponse().getWriter().write(json);
      return null;//告诉struts不需要进行结果处理
  }

    @Override
    public BaseDict getModel() {
        return baseDict;
    }
}
