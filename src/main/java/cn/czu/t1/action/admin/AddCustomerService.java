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

public class AddCustomerService implements Action{
    @Override
    public String execute() throws Exception {

        return null;
    }
}
