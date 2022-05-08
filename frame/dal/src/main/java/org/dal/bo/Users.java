package org.dal.bo;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 实体:用户表
 * 
 * @author shi
 */
public class Users{

    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 是否被锁
     */
    private Integer locked;


    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getSalt(){
        return salt;
    }
    
    public void setSalt(String salt){
        this.salt = salt;
    }
    
    public Integer getLocked(){
        return locked;
    }
    
    public void setLocked(Integer locked){
        this.locked = locked;
    }
    
 
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}