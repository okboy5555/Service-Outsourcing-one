package cn.czu.t1.action.admin;

import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.controler.Constant;
import cn.czu.t1.dao.CustomerServiceDaoUtil;
import cn.czu.t1.module.CustomerService;
import cn.opom.crypt.AesCrypt;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceOperation implements Action{
    @Override
    public String execute() throws Exception {
        return null;
    }

    public String add() throws Exception{
        ActionContext acx = ActionContext.getContext();
        HttpParameters parameters = acx.getParameters();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        String username = parameters.get("number").getValue();
        String password = parameters.get("password").getValue();
        CustomerService customerService = new CustomerService();
        customerService.setNumber(username);
        customerService.setPassword(AesCrypt.aesEncrypt(password, Constant.CUSTOMER_SERVICE_PWD_SOLT, AesCrypt.KEYLEN256));
        CustomerServiceDaoUtil customerServiceDaoUtil = (CustomerServiceDaoUtil) SpringContextUtil.getBean("CustomerServiceDaoUtil");
        List<CustomerService> list = new ArrayList<>();
        list.add(customerService);
        int t = customerServiceDaoUtil.insert(list);
        if( t == 0 ){
            response.getWriter().println("OK");
        }else{
            response.getWriter().println("Fail");
        }
        return null;
    }
}
