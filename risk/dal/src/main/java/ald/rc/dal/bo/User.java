package ald.rc.dal.bo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 实体: 用户表
 * 
 * @author shi
 * @date 2017-07-19
 */
 
 public class User{
    /**
     * 主键Id
     */
    private Long id;
    /**
     * 消费端 -51反呗用户标识
     */
    private String consumerNo;
    /**
     * 借款端 -E都市钱包用户标识
     */
    private String borrowerNo;
    /**
     * 借款人用户真实姓名
     */
    private String realName;
    /**
     * 借款人手机号
     */
    private String phone;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 借款人身份证卡号
     */
    private String idNo;
    /**
     * 芝麻信用的open_id
     */
    private String openId;
    /**
     * 借款人支付宝账户
     */
    private String alipayNo;
    /**
     * 借款人邮箱
     */
    private String email;
    /**
     * qq号码
     */
    private String qq;
    /**
     * 户籍地址
     */
    private String censusRegister;
    /**
     * 用户所在地址
     */
    private String address;
    /**
     * 联系人数量
     */
    private Integer linkmanCount;
    /**
     * 通讯录号码大于等于8位的比例
     */
    private Double phoneLongRate;
    /**
     * 通讯录号码大于等于8位的数量
     */
    private Integer phoneLongCount;
    /**
     * 通讯录号码少于8位的比例
     */
    private Double phoneShortRate;
    /**
     * 通讯录号码少于8位的数量
     */
    private Integer phoneShortCount;
    /**
     * 用户状态 : 默认10-正常
     */
    private String state;
    /**
     * 渠道
     */
    private Integer channelId;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 扩展参数
     */
    private String reqExt;
    
    public Long getId(){
      return id;
    }
    
    public void setId(Long id){
      this.id = id;
    }
    
    public String getConsumerno(){
      return consumerNo;
    }
    
    public void setConsumerno(String consumerNo){
      this.consumerNo = consumerNo;
    }
    
    public String getBorrowerno(){
      return borrowerNo;
    }
    
    public void setBorrowerno(String borrowerNo){
      this.borrowerNo = borrowerNo;
    }
    
    public String getRealname(){
      return realName;
    }
    
    public void setRealname(String realName){
      this.realName = realName;
    }
    
    public String getPhone(){
      return phone;
    }
    
    public void setPhone(String phone){
      this.phone = phone;
    }
    
    public Integer getAge(){
      return age;
    }
    
    public void setAge(Integer age){
      this.age = age;
    }
    
    public String getIdno(){
      return idNo;
    }
    
    public void setIdno(String idNo){
      this.idNo = idNo;
    }
    
    public String getOpenid(){
      return openId;
    }
    
    public void setOpenid(String openId){
      this.openId = openId;
    }
    
    public String getAlipayno(){
      return alipayNo;
    }
    
    public void setAlipayno(String alipayNo){
      this.alipayNo = alipayNo;
    }
    
    public String getEmail(){
      return email;
    }
    
    public void setEmail(String email){
      this.email = email;
    }
    
    public String getQq(){
      return qq;
    }
    
    public void setQq(String qq){
      this.qq = qq;
    }
    
    public String getCensusregister(){
      return censusRegister;
    }
    
    public void setCensusregister(String censusRegister){
      this.censusRegister = censusRegister;
    }
    
    public String getAddress(){
      return address;
    }
    
    public void setAddress(String address){
      this.address = address;
    }
    
    public Integer getLinkmancount(){
      return linkmanCount;
    }
    
    public void setLinkmancount(Integer linkmanCount){
      this.linkmanCount = linkmanCount;
    }
    
    public Double getPhonelongrate(){
      return phoneLongRate;
    }
    
    public void setPhonelongrate(Double phoneLongRate){
      this.phoneLongRate = phoneLongRate;
    }
    
    public Integer getPhonelongcount(){
      return phoneLongCount;
    }
    
    public void setPhonelongcount(Integer phoneLongCount){
      this.phoneLongCount = phoneLongCount;
    }
    
    public Double getPhoneshortrate(){
      return phoneShortRate;
    }
    
    public void setPhoneshortrate(Double phoneShortRate){
      this.phoneShortRate = phoneShortRate;
    }
    
    public Integer getPhoneshortcount(){
      return phoneShortCount;
    }
    
    public void setPhoneshortcount(Integer phoneShortCount){
      this.phoneShortCount = phoneShortCount;
    }
    
    public String getState(){
      return state;
    }
    
    public void setState(String state){
      this.state = state;
    }
    
    public Integer getChannelid(){
      return channelId;
    }
    
    public void setChannelid(Integer channelId){
      this.channelId = channelId;
    }
    
    public String getChannelname(){
      return channelName;
    }
    
    public void setChannelname(String channelName){
      this.channelName = channelName;
    }
    
    public Date getAddtime(){
      return addTime;
    }
    
    public void setAddtime(Date addTime){
      this.addTime = addTime;
    }
    
    public String getReqext(){
      return reqExt;
    }
    
    public void setReqext(String reqExt){
      this.reqExt = reqExt;
    }
    
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}