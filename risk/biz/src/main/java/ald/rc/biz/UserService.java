package ald.rc.biz;


import com.github.pagehelper.PageInfo;

import ald.rc.dal.bo.User;

public interface UserService extends BaseService<User, Long> {

	PageInfo<User>  page(int start,int end);
}
