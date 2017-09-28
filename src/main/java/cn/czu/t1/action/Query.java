package cn.czu.t1.action;

import cn.czu.t1.Util.JsonUtil;
import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.dao.KnowledgeDaoUtil;
import cn.czu.t1.module.ReturnData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

import javax.servlet.http.HttpServletResponse;

public class Query implements Action{
    @Override
    public String execute() throws Exception {
        ActionContext acx = ActionContext.getContext();
        HttpParameters parameters = acx.getParameters();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String strQuery = parameters.get("str").getValue();
        KnowledgeDaoUtil knowledgeDaoUtil = (KnowledgeDaoUtil) SpringContextUtil.getBean("KnowledgeDaoUtil");
        ReturnData rtData = new ReturnData();
        rtData.setStatus("1");
        rtData.setMessage(JsonUtil.arrayToJsonString(knowledgeDaoUtil.query(strQuery)));
        response.getWriter().println(JsonUtil.objToJsonString(rtData));
        return null;
    }
}
