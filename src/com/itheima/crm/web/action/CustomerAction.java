package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.PageBean;
import com.itheima.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Customer customer = new Customer();
    private Integer currentPage;//当前页
    private Integer pageSize;//当前业最多显示的记录数

    //使用属性驱动获取上传文件\上传文件名\上传文件类型
    private File upload;//属性名
    private String uploadFileName;//属性名
    private String uploadContentType;//文件类型
    private String fileName;//文件名
    private String contentType;//文件名

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Autowired
    private CustomerService customerService;

    @Override
    public Customer getModel() {
        return customer;
    }

    //查找客户列表
    public String findBypage() {
        //封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        //判断并封装参数
        if (StringUtils.isNotBlank(customer.getCust_name())) {
            dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        //客户级别(数字字典id)
        if (customer.getCust_level() != null && StringUtils.isNotBlank(customer.getCust_level().getDict_id())) {
            dc.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
        }
        //客户来源
        if (customer.getCust_source() != null && StringUtils.isNotBlank(customer.getCust_source().getDict_id())) {
            dc.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
        }
        //客户所属行业
        if (customer.getCust_industry() != null && StringUtils.isNotBlank(customer.getCust_industry().getDict_id())) {
            dc.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
        }

        //1 调用Service查询分页数据(PageBean)
        PageBean pageBean = customerService.getpageBean(dc, currentPage, pageSize);
        //2 将PageBean放入request域,转发到列表页面显示
        ActionContext.getContext().getValueStack().push(pageBean);

        return "findBypage";
    }

    //保存客户信息
    public String save() throws IOException {
        //存在文件
        if (upload != null) {
            String basePath = "D:\\upload\\";
            //获取文件名(uuid)
            String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
            //获取文件存储的路径(使用hascode的形式分路径/时间戳)
            String path = UploadUtils.getPath(uuidFileName);
            //组织存放的真是路径
            File pathFile = new File(basePath + path);
            if (!pathFile.exists()) {
                pathFile.mkdirs();//创建文件夹
            }
            //上传的文件
            File destFile = new File(basePath + path + "/" + uuidFileName);
            FileUtils.copyFile(upload, destFile);//将源文件拷贝一份,复制到目标文件
            customer.setCust_image(destFile.toString());
            customer.setCust_realimage(uploadFileName);
        }
        customerService.save(customer);
        return "save";
    }

    //跳转页面/jsp/customer/add.jsp
    public String saveUI() {
        return "saveUI";
    }

    public String delete() {

        Customer c = customerService.findCustomerById(customer.getCust_id());

        //如果文件存在 完成删除
        String path = c.getCust_image();
        if (StringUtils.isNotBlank(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
        //删除数据库
        customerService.deleteCustomerById(c);
        return "delete";
    }

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String downloadpic() throws IOException {
        Customer c = customerService.findCustomerById(customer.getCust_id());
        String path = c.getCust_image();
        fileName = c.getCust_realimage();
         contentType = ServletActionContext.getServletContext().getMimeType(fileName);
        if (StringUtils.isNotBlank(path)) {
            File file = new File(path);
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            }
        }

        return  "downloadpic";
    }
    public String updateUI() throws IOException {
        //使用主键查询客户的信息
        Long cust_id = customer.getCust_id();
        Customer c = customerService.findCustomerById(cust_id);
        //把对象放置栈顶
        ActionContext.getContext().getValueStack().push(c);
        return  "updateUI";
    }
    public String edit() throws IOException {
        //存在上传文件


                //存在文件
                if (upload != null) {
                    String cust_image = customer.getCust_image();
                    if(StringUtils.isNotBlank(cust_image)){
                        File file=new File(cust_image);
                        if(file.exists()){
                            file.delete();
                        }
                    String basePath = "D:\\upload\\";
                    //获取文件名(uuid)
                    String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
                    //获取文件存储的路径(使用hascode的形式分路径/时间戳)
                    String path = UploadUtils.getPath(uuidFileName);
                    //组织存放的真是路径
                    File pathFile = new File(basePath + path);
                    if (!pathFile.exists()) {
                        pathFile.mkdirs();//创建文件夹
                    }
                    //上传的文件
                    File destFile = new File(basePath + path + "/" + uuidFileName);
                    FileUtils.copyFile(upload, destFile);//将源文件拷贝一份,复制到目标文件
                    customer.setCust_image(destFile.toString());
                    customer.setCust_realimage(uploadFileName);
                }

        }
        customerService.update(customer);


        return  "edit";
    }
}
