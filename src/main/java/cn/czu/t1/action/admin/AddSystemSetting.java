package cn.czu.t1.action.admin;

import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.dao.SystemSettingDaoUtil;
import cn.czu.t1.module.SystemSettingItem;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddSystemSetting  implements Action {

    @Override
    public String execute() throws Exception {
        ActionContext acx = ActionContext.getContext();
        HttpParameters parameters = acx.getParameters();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String key = parameters.get("key").getValue();
        String value = parameters.get("value").getValue();
        SystemSettingItem item = new SystemSettingItem();
        item.setName(key);
        item.setValue(value);
        SystemSettingDaoUtil systemSettingDaoUtil = (SystemSettingDaoUtil) SpringContextUtil.getBean("SystemSettingDaoUtil");
        List<SystemSettingItem> list = new ArrayList<>();
        list.add(item);
        int t = systemSettingDaoUtil.insert(list);
        if( t == 0 ){
            response.getWriter().println("OK");
        }else{
            response.getWriter().println("Fail");
        }
        return null;
    }
}
