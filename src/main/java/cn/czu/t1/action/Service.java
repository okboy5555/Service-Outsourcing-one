package cn.czu.t1.action;

import cn.czu.t1.controler.AdminController;
import cn.czu.t1.controler.QueueController;
import cn.czu.t1.module.Customer;
import cn.czu.t1.module.QueueItem;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletRequest;

public class Service implements Action {
    @Override
    public String execute() throws Exception {
        ActionContext acx = ActionContext.getContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpParameters parameters = acx.getParameters();
        String serviceId = parameters.get("serviceId").getValue();
        Customer customer = AdminController.getCustomerById(serviceId.substring(0,32));
        QueueItem queueItem = new QueueItem(serviceId, request.getSession(), customer);
        QueueController.put(queueItem);
        return null;
    }
}
