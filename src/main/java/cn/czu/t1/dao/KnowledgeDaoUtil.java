package cn.czu.t1.dao;

import cn.czu.t1.module.KnowledgeItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class KnowledgeDaoUtil implements DaoUtil<KnowledgeItem>{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<KnowledgeItem> query(String strSearch) {
        Session s = sessionFactory.openSession();
        try {
            strSearch = "%" + strSearch + "%";
            Query query = s.createQuery("from KnowledgeItem where keyword like :keyword or subject like :subject")
                    .setParameter("keyword", strSearch).setParameter("subject", strSearch);
            return query.getResultList();
        }catch (Exception ex) {
            return null;
        }finally {
            s.close();
        }
    }

    @Override
    public List<KnowledgeItem> query(KnowledgeItem obj) {
        return null;
    }

    @Override
    public int insert(List<KnowledgeItem> obj) {
        return 0;
    }

    @Override
    public int update(List<KnowledgeItem> obj) {
        return 0;
    }

    @Override
    public int delete(List<KnowledgeItem> obj) {
        return 0;
    }
}
