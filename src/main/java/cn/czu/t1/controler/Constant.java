package cn.czu.t1.controler;

public final class Constant {
    public static final String JWT_SECRET = "XX#$%()(#*!()!KL<><MQL32NQN(#_8jsd+w32234545fdf>?N<:{LWaPW";
    public static int MAX_CUSTOMERSERVICE_NUM;
    public static int CUSTOMER_TTL;
    public static int CUSTOMERSERVICE_TTL;
    public static int ADMIN_TTL;
    public static String ADMIN_PWD_SOLT="pwd_Admin";
    public static String CUSTOMER_PWD_SOLT = "pwd_Customer";
    public static String CUSTOMER_SERVICE_PWD_SOLT = "pwd_CustomerService";
    public Constant() {
        MAX_CUSTOMERSERVICE_NUM = SystemController.getMaxCustomerServiceNum();
        CUSTOMER_TTL = 1000*60*60; //1h
        CUSTOMERSERVICE_TTL = 1000*60*10; //10min
        ADMIN_TTL = 1000*60*10; //10min
    }
}