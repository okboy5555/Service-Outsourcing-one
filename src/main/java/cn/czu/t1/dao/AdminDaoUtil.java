package cn.czu.t1.dao;

import cn.czu.t1.module.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDaoUtil implements DaoUtil<Admin> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Admin> query(Admin obj) {
        Session s = sessionFactory.openSession();
        try {
            Query query = s.createQuery("from Admin where id=:id or username=:username")
                    .setParameter("id",obj.getId()).setParameter("username",obj.getUsername());
            return query.getResultList();
        }catch (Exception ex) {
            return null;
        }finally {
            s.close();
        }
    }

    public int insert(List<Admin> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(Admin admin:obj){
                s.save(admin);
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

    public int update(List<Admin> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(Admin admin:obj){
                s.update(admin);
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

    public int delete(List<Admin> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(Admin admin:obj){
                s.delete(admin);
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
