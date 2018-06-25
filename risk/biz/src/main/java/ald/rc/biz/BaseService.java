package ald.rc.biz;

import java.io.Serializable;

public interface BaseService<T, D extends Serializable> {
    int insert(T t);
    int deleteById(D d);
    int updateById(T t);
    T findById(D d);
}
