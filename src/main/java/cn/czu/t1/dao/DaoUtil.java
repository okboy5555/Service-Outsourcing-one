package cn.czu.t1.dao;

import java.util.List;

/**
 * Created by chenyi on 2017/7/18.
 */
public interface DaoUtil<T> {
    public List<T> query(T obj);
    public int insert(List<T> obj);
    public int update(List<T> obj);
    public int delete(List<T> obj);

}
