package org.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BaseMapper<T, ID extends Serializable> {

	/**
	 * 插入数据
	 */
	int insert(T e);

	/**
	 * 根据主键更新数据
	 * 
	 * @param e
	 *            更新数据及条件
	 * @return 影响行数
	 */
	int update(T e);

	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	int delete(ID id);
	/**
	 * 根据主键查询数据
	 * 
	 * @param primary
	 *            主键值
	 * @return 数据结果
	 */
	T findByPrimary(ID primary);
	
	/**
	 * 获取一条记录
	 * 
	 * @param param
	 *            查询条件
	 * @return 查询结果
	 */
	T findSelective(Map<String, Object> param);

	/**
	 * 数据查询
	 * 
	 * @param params
	 *            查询条件
	 * @return 结果集
	 */
	List<T> listSelective(Map<String, Object> param);
}