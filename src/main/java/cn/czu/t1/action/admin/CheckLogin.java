package cn.czu.t1.action.admin;

import cn.czu.t1.Util.JWTUtil;
import cn.czu.t1.Util.JsonUtil;
import cn.czu.t1.controler.Constant;
import cn.czu.t1.module.ReturnData;
import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLogin implements Action{
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String token = request.getHeader("token");
        ReturnData returnData = new ReturnData();
        int i = JWTUtil.parseJWT(token);
        if (i == 1){ // OK
            String id = JWTUtil.getIdFromJWT(token);
            returnData.setStatus("1");
            returnData.setToken(JWTUtil.createJWT(id,"updateStatus", Constant.ADMIN_TTL));
            returnData.setMessage("success");
        }else if(i == 4){ // Time out
            returnData.setStatus("4");
            returnData.setToken("");
            returnData.setMessage("login time out");
        }else{
            returnData.setStatus("0");
            returnData.setToken("");
            returnData.setMessage("failure");
        }
        response.getWriter().println(JsonUtil.objToJsonString(returnData));
        return null;
    }
}
