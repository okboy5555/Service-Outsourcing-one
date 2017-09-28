package cn.czu.t1.module;

import javax.servlet.http.HttpSession;

public class QueueItem {
    private String id;
    private HttpSession session;
    private Customer customer;

    public QueueItem(String id, HttpSession session, Customer customer) {
        this.id = id;
        this.session = session;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }
    public HttpSession getSession() {
        return session;
    }

    public Customer getCustomer() {
        return customer;
    }

}
