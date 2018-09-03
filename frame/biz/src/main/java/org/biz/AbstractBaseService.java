package org.biz;

import java.io.Serializable;

import javax.annotation.Resource;

import org.dal.BaseMapper;

public abstract class AbstractBaseService<T, ID extends Serializable> implements
		BaseService<T, ID> {
	@Resource
	private BaseMapper<T, ID> baseMapper;
	
	@Override
	public int insert(T record) {
		return getMapper().insert(record);
	}
	@Override
	public T findByPrimary(ID id) {
		return getMapper().findByPrimary(id);
	}
	@Override
	public int update(T record) {
		return getMapper().update(record);
	}
	@Override
	public int delete(ID id){
		return getMapper().delete(id);
	}

	public abstract BaseMapper<T, ID> getMapper();
	
}
