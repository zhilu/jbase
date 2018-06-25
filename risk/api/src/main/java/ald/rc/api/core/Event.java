package ald.rc.api.core;

public enum Event {
	Login("登录事件"),
	Register("注册事件"),
	Trade("交易事件"),
	Payment("支付事件"),
	Refund("退款事件"),
	Loan("借款事件"),
	Transfer("转账事件"),
	Withdraw("提现事件"),
	Modify("修改事件"),
	Click("点击事件"),
	Activate("激活事件"),
	Other("其他事件");
	
	private String description;

	Event(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
