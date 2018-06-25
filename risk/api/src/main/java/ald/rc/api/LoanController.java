package ald.rc.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;

import ald.rc.api.bo.LoanModel;
import ald.rc.api.bo.LoanResult;
import ald.rc.api.core.DefaultEventContext;
import ald.rc.api.core.Event;
import ald.rc.api.core.EventBaseController;
import ald.rc.api.core.EventContext;

@Controller
@Scope(value = "prototype")
public class LoanController extends EventBaseController<LoanModel,LoanResult> {

	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	@Override
	protected EventContext<LoanResult> getEventContext(LoanModel t) { 
		Map<String,Object> eventData = Maps.newHashMap();
		//根据事件类型准备所需要的字段
		eventData.put("osType", t.getDeviceInfo().getOsType());
		return new DefaultEventContext<>(Event.Login, eventData);
	}

	@RequestMapping(value = ApiPath.LOAN_ASY)
	public void loanAsy(LoanModel model) {
		logger.info("{}",model);
	}

	@RequestMapping(value = ApiPath.LOAN_SYN)
	public void loanSyn(LoanModel model) {

	}

}
