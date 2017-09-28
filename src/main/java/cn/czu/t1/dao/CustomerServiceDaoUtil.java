package cn.czu.t1.dao;

import cn.czu.t1.module.CustomerService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerServiceDaoUtil implements DaoUtil<CustomerService>{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CustomerService> query(CustomerService obj) {
        Session s = sessionFactory.openSession();
        try {
            Query query = s.createQuery("from CustomerService where id=:id or number=:number")
                    .setParameter("id",obj.getId()).setParameter("number",obj.getNumber());
            return query.getResultList();
        }catch (Exception ex) {
            return null;
        }finally {
            s.close();
        }
    }

    public int insert(List<CustomerService> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(CustomerService customer:obj){
                s.save(customer);
            }
            tx.commit();
            return 0;
        }catch (Exception ex) {
            tx.rollback();
            return 1;
        }finally {
            s.close();
        }
    }

    public int update(List<CustomerService> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(CustomerService customer:obj){
                s.update(customer);
            }
            tx.commit();
            return 0;
        }catch (Exception ex){
            tx.rollback();
            return 1;
        }finally {
            s.close();
        }
    }

    public int delete(List<CustomerService> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(CustomerService customer:obj){
                s.delete(customer);
            }
            tx.commit();
            return 0;
        }catch (Exception ex){
            tx.rollback();
            return 1;
        }finally {
            s.close();
        }
    }

    public List getAllCustomerService() {
        Session s = sessionFactory.openSession();
        try {
            Query query = s.createQuery("from CustomerService");
            return query.getResultList();
        }catch (Exception ex) {
            return null;
        }finally {
            s.close();
        }
    }
}
