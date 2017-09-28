package cn.czu.t1.action;

import cn.czu.t1.Util.JWTUtil;
import cn.czu.t1.Util.JsonUtil;
import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.Util.UUIDUtil;
import cn.czu.t1.controler.Constant;
import cn.czu.t1.controler.QueueController;
import cn.czu.t1.dao.CustomerDaoUtil;
import cn.czu.t1.module.Customer;
import cn.czu.t1.module.QueueItem;
import cn.czu.t1.module.ReturnData;
import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.czu.t1.controler.AdminController.checkLogin;

public class GetServiceURL implements Action {
    @Override
    public String execute() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        String token = request.getHeader("token");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        ReturnData rtData = new ReturnData();
        if(!checkLogin()){
            rtData.setStatus("0");
            rtData.setMessage("登陆超时，请重新登录");
            response.getWriter().println(JsonUtil.objToJsonString(rtData));
            return null;
        }
        CustomerDaoUtil customerDaoUtil = (CustomerDaoUtil) SpringContextUtil.getBean("CustomerDaoUtil");
        Customer customer = new Customer();
        customer.setId(JWTUtil.getIdFromJWT(token));
        customer = customerDaoUtil.query(customer).get(0);
        String serviceId = customer.getId() + UUIDUtil.getUUID().toLowerCase();
        rtData.setStatus("1");
        rtData.setMessage("/ws/serviceId="+serviceId);
        rtData.setToken(JWTUtil.createJWT(customer.getId(),"statusUpdate", Constant.CUSTOMER_TTL));
        response.getWriter().println(JsonUtil.objToJsonString(rtData));
        QueueItem queueItem = new QueueItem(serviceId, request.getSession(), customer);
        QueueController.put(queueItem);
        return null;
    }
}
