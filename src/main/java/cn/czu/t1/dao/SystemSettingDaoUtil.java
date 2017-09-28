package cn.czu.t1.dao;

import cn.czu.t1.module.SystemSettingItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SystemSettingDaoUtil implements DaoUtil<SystemSettingItem>{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<SystemSettingItem> query(SystemSettingItem obj) {
        return null;
    }

    @Override
    public int insert(List<SystemSettingItem> obj) {
        Session s = sessionFactory.openSession();
        Transaction tx= s.beginTransaction();
        try{
            for(SystemSettingItem item:obj){
                s.save(item);
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

    @Override
    public int update(List<SystemSettingItem> obj) {
        return 0;
    }

    @Override
    public int delete(List<SystemSettingItem> obj) {
        return 0;
    }

    public int getMaxCustomerServiceNum(){
        Session s = sessionFactory.openSession();
        try {
            Query query = s.createQuery("from SystemSettingItem where name=:name")
                    .setParameter("name","");
            List<Integer> list = query.getResultList();
            return  list.get(0);
        }catch (Exception ex) {
            return -1;
        }finally {
            s.close();
        }
    }
}
