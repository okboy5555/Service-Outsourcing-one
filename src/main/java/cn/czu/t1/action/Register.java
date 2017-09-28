package cn.czu.t1.action;

import cn.czu.t1.Util.JsonUtil;
import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.controler.Constant;
import cn.czu.t1.dao.CustomerDaoUtil;
import cn.czu.t1.module.Customer;
import cn.czu.t1.module.ReturnData;
import cn.opom.crypt.AesCrypt;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Register implements Action {
    @Override
    public String execute() throws Exception {
        ActionContext acx = ActionContext.getContext();
        HttpParameters parameters = acx.getParameters();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        String password = parameters.get("password").getValue();
        String username = parameters.get("username").getValue();
        String phone = parameters.get("phone").getValue();
        String mail = parameters.get("mail").getValue();

        Customer customer = new Customer();
        customer.setPassword(AesCrypt.aesEncrypt(password, Constant.CUSTOMER_PWD_SOLT, AesCrypt.KEYLEN256));
        customer.setUsername(username);
        customer.setPhone(phone);
        customer.setMail(mail);
        customer.setLevel(1);
        customer.setNickname(username);

        CustomerDaoUtil customerDaoUtil = (CustomerDaoUtil) SpringContextUtil.getBean("CustomerDaoUtil");
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        ReturnData rtData = new ReturnData();
        int t = customerDaoUtil.insert(list);
        if( t == 0 ){
            rtData.setStatus("1");
            rtData.setMessage("OK");
        }else{
            rtData.setStatus("0");
            rtData.setMessage("Fail");
        }
        response.getWriter().println(JsonUtil.objToJsonString(rtData));
        return null;
    }
}
