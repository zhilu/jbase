package ald.rc.dal;

@MyBatisDao
public interface BaseMapper<T, D> {
    int insert(T t);
    int update(T t);
    T findByPrimary(D d);
    int delete(D d);
}
