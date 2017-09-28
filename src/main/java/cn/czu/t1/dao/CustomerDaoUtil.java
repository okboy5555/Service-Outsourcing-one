package cn.czu.t1.dao;

import cn.czu.t1.module.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoUtil implements DaoUtil<Customer>{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Customer> query(Customer obj) {
        Session s = sessionFactory.openSession();
        try {
            Query query = s.createQuery("from Customer where id=:id or username=:username or phone=:phone or mail=:mail")
                    .setParameter("id",obj.getId()).setParameter("username",obj.getUsername())
                    .setParameter("phone", obj.getPhone()).setParameter("mail",obj.getMail());
            return query.getResultList();
        }catch (Exception ex) {
            return null;
        }finally {
            s.close();
        }
    }

    public int insert(List<Customer> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(Customer customer:obj){
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

    public int update(List<Customer> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(Customer customer:obj){
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

    public int delete(List<Customer> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(Customer customer:obj){
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
}
