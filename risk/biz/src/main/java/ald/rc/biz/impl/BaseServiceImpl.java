package ald.rc.biz.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ald.rc.biz.BaseService;
import ald.rc.dal.BaseMapper;

@Service
public class BaseServiceImpl<T,D extends Serializable> implements BaseService<T, D> {

	@Resource
	private BaseMapper<T, D> baseMapper;

	
	public BaseMapper<T, D> getBaseMapper() {
		return baseMapper;
	}

	@Override
	public int insert(T t) {
		return getBaseMapper().insert(t);
	}

	@Override
	public int deleteById(D d) {
		return getBaseMapper().delete(d);
	}

	@Override
	public int updateById(T t) {
		return getBaseMapper().update(t);
	}

	@Override
	public T findById(D d) {
		return getBaseMapper().findByPrimary(d);
	}
}
