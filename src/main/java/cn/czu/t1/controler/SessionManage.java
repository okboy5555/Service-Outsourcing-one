package cn.czu.t1.controler;

import cn.czu.t1.module.*;

import java.util.List;

public class SessionManage {
    public static boolean isCustomerServiceIdle(){
        List<CustomerService> customerServices =  SystemController.getAllCustomerServices();
        for (CustomerService cs:customerServices){

        }
        return false;
    }
}
