package cn.czu.t1.action.admin;

import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.dao.AdminDaoUtil;
import cn.czu.t1.module.Admin;
import cn.opom.crypt.AesCrypt;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class AddAdmin implements Action{

    @Override
    public String execute() throws Exception {
        ActionContext acx = ActionContext.getContext();
        HttpParameters parameters = acx.getParameters();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String username;
        String password;
        String type;
        username = parameters.get("username").getValue();
        password = parameters.get("password").getValue();
        type = parameters.get("type").getValue();
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(AesCrypt.aesEncrypt(password, "pwd_Admin",AesCrypt.KEYLEN256));
        admin.setLevel(type);
        AdminDaoUtil adminDaoUtil = (AdminDaoUtil) SpringContextUtil.getBean("AdminDaoUtil");
        List<Admin> list = new ArrayList<>();
        list.add(admin);
        int t = adminDaoUtil.insert(list);
        if( t == 0 ){
            response.getWriter().println("OK");
        }else{
            response.getWriter().println("Fail");
        }
        return null;
    }
}
