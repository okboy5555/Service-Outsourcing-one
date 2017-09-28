package cn.czu.t1.action.admin;

import cn.czu.t1.controler.QueueController;
import cn.czu.t1.module.QueueItem;
import com.opensymphony.xwork2.Action;
//import com.opensymphony.xwork2.ActionContext;
import com.sun.deploy.panel.ITreeNode;
import org.apache.commons.collections.IterableMap;
import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletResponse;

public class ConnectCustomer implements Action{
    @Override
    public String execute() throws Exception {
        QueueItem item;
        while ((item = QueueController.poll()) == null);
//        while (item.)
//        ActionContext acx = ActionContext.getContext();
//        HttpParameters parameters = acx.getParameters();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().println(item.getId());
        return null;
    }
}
