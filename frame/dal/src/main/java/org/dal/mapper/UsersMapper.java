package org.dal.mapper;

import org.dal.BaseMapper;
import org.dal.MyBatisDao;
import org.dal.bo.Users;

/**
 * DAO:
 * 
 * @author shi
 */
@MyBatisDao
public interface UsersMapper extends BaseMapper<Users, Long> {
}