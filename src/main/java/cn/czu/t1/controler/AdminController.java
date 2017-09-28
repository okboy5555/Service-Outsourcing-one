package cn.czu.t1.controler;

import cn.czu.t1.Util.JWTUtil;
import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.dao.CustomerDaoUtil;
import cn.czu.t1.module.Customer;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletRequest;

public class AdminController {

    public static boolean checkLogin(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String token = request.getHeader("token");
        if(token == "" || token  == null){
            ActionContext acx = ActionContext.getContext();
            HttpParameters parameters = acx.getParameters();
            token = parameters.get("token").getValue();
        }
        int i = JWTUtil.parseJWT(token);
        if (i == 1){
            return true;
        }else{
            return false;
        }
    }

    public static Customer getCustomerById(String id){
        CustomerDaoUtil customerDaoUtil = (CustomerDaoUtil) SpringContextUtil.getBean("CustomerDaoUtil");
        Customer customer = new Customer();
        customer.setId(id);
        return customerDaoUtil.query(customer).get(0);
    }
}
