package cn.czu.t1.action;

import cn.czu.t1.Util.JWTUtil;
import cn.czu.t1.Util.JsonUtil;
import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.controler.Constant;
import cn.czu.t1.dao.AdminDaoUtil;
import cn.czu.t1.dao.CustomerDaoUtil;
import cn.czu.t1.dao.CustomerServiceDaoUtil;
import cn.czu.t1.module.Admin;
import cn.czu.t1.module.Customer;
import cn.czu.t1.module.CustomerService;
import cn.czu.t1.module.ReturnData;
import cn.opom.crypt.AesCrypt;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

public class Login implements Action {

    public String execute() throws Exception {
        ActionContext acx = ActionContext.getContext();
        HttpParameters parameters = acx.getParameters();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String strLogin;
        String password;
        String type;
        strLogin = parameters.get("strLogin").getValue();
        password = parameters.get("password").getValue();
        type = parameters.get("type").getValue();
        if(strLogin == null || password == null || type == null){
            return Action.ERROR; // 发送的数据格式不正确，返回登录页面
        }
        ReturnData rtData = new ReturnData();
        switch (type){
            case "1"://普通用户
                customerLogin(strLogin, password, response);
                break;
            case "2"://客服
                customerServiceLogin(strLogin, password, response);
                break;
            case "3"://管理员
                adminLogin(strLogin, password, response);
                break;
            default:
                rtData.setStatus("0");
                rtData.setMessage("登陆参数错误");
                rtData.setToken("");
                response.getWriter().println(JsonUtil.objToJsonString(rtData));
        }

        return Action.NONE;
    }

    public void adminLogin(String username, String password, HttpServletResponse res){
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(AesCrypt.aesEncrypt(password, "pwd_Admin",AesCrypt.KEYLEN256));
        AdminDaoUtil adminDaoUtil = (AdminDaoUtil)SpringContextUtil.getBean("AdminDaoUtil");
        List<Admin> list = adminDaoUtil.query(admin);
        try {
            ReturnData rtData = new ReturnData();
            if(!list.isEmpty()){ //查询到相关管理员
                if(admin.getPassword().equals(list.get(0).getPassword())){ //密码正确
                    rtData.setMessage("success");
                    rtData.setStatus("1");
                    rtData.setToken(JWTUtil.createJWT(list.get(0).getId(),"login",Constant.ADMIN_TTL));
                    res.getWriter().println(JsonUtil.objToJsonString(rtData));
                    return;
                }
            }
            rtData.setStatus("0");
            rtData.setMessage("用户名或者密码不正确");
            rtData.setToken("");
            res.getWriter().println(JsonUtil.objToJsonString(rtData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerServiceLogin(String number, String password, HttpServletResponse res){
        CustomerService cs = new CustomerService();
        cs.setNumber(number);
        cs.setPassword(AesCrypt.aesEncrypt(password, "pwd_CustomerService",AesCrypt.KEYLEN256));
        CustomerServiceDaoUtil customerServiceDaoUtil = (CustomerServiceDaoUtil)SpringContextUtil.getBean("CustomerServiceDaoUtil");
        List<CustomerService> list = customerServiceDaoUtil.query(cs);
        try {
            ReturnData rtData = new ReturnData();
            if(!list.isEmpty()){ //查询到相关客服人员
            if(cs.getPassword().equals(list.get(0).getPassword())){ //密码正确
                rtData.setMessage("success");
                rtData.setStatus("1");
                rtData.setToken(JWTUtil.createJWT(list.get(0).getId(),"login",Constant.CUSTOMERSERVICE_TTL));
                res.getWriter().println(JsonUtil.objToJsonString(rtData));
                return;
            }
        }
        rtData.setStatus("0");
        rtData.setMessage("用户名或者密码不正确");
        rtData.setToken("");
        res.getWriter().println(JsonUtil.objToJsonString(rtData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void customerLogin(String strLogin, String password, HttpServletResponse res){
        Customer customer = new Customer();
        customer.setMail(strLogin);
        customer.setPhone(strLogin);
        customer.setUsername(strLogin);
        customer.setPassword(AesCrypt.aesEncrypt(password, Constant.CUSTOMER_PWD_SOLT, AesCrypt.KEYLEN256));
        CustomerDaoUtil customerDaoUtil = (CustomerDaoUtil)SpringContextUtil.getBean("CustomerDaoUtil");
        List<Customer> list = customerDaoUtil.query(customer);
        try {
            ReturnData rtData = new ReturnData();
            if(!list.isEmpty()){ //查询到相关客服人员
                if(customer.getPassword().equals(list.get(0).getPassword())){ //密码正确
                    rtData.setMessage("success");
                    rtData.setStatus("1");
                    rtData.setToken(JWTUtil.createJWT(list.get(0).getId(),"login", Constant.CUSTOMER_TTL));
                    res.getWriter().println(JsonUtil.objToJsonString(rtData));
                    return;
                }
            }
            rtData.setStatus("0");
            rtData.setMessage("用户名或者密码不正确");
            rtData.setToken("");
            res.getWriter().println(JsonUtil.objToJsonString(rtData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
