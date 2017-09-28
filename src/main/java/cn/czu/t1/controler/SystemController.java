package cn.czu.t1.controler;

import cn.czu.t1.Util.SpringContextUtil;
import cn.czu.t1.dao.CustomerServiceDaoUtil;
import cn.czu.t1.dao.SystemSettingDaoUtil;
import cn.czu.t1.module.CustomerService;

import java.util.List;

public class SystemController {
    public static List<CustomerService> getAllCustomerServices(){
        CustomerServiceDaoUtil customerServiceDaoUtil = (CustomerServiceDaoUtil) SpringContextUtil.getBean("CustomerServiceDaoUtil");
        return customerServiceDaoUtil.getAllCustomerService();
    }

    public static int getMaxCustomerServiceNum(){
        SystemSettingDaoUtil systemSettingDaoUtil = (SystemSettingDaoUtil) SpringContextUtil.getBean("SystemSettingDaoUtil");
        return systemSettingDaoUtil.getMaxCustomerServiceNum();
    }


}
