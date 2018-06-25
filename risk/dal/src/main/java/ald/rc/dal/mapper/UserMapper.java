package ald.rc.dal.mapper;

import java.util.List;
import java.util.Map;

import ald.rc.dal.BaseMapper;
import ald.rc.dal.MyBatisDao;
import ald.rc.dal.bo.User;

/**
 * 用户表dao
 * 
 * @author shi
 * @date 2017-07-19
 */
@MyBatisDao
public interface UserMapper extends BaseMapper<User,Long> {

	List<User> listSelective(Map<String,String> map);
    
}
