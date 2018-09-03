package org.biz;

import java.io.Serializable;

/**
 * 服务基类接口定义
 */
public interface BaseService<T, ID extends Serializable> {

	int insert(T record);
		
	int update(T record);
	
	int delete(ID id);
	
	T findByPrimary(ID id);
		
}
