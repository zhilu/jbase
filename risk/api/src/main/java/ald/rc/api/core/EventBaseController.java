package ald.rc.api.core;

public abstract class EventBaseController <M, R> extends BaseController implements EventHandler<R>{

	protected abstract EventContext<R> getEventContext(M m);
	
	@Override
	public boolean preHandle(EventContext<R> eventContext) {
		eventContext.loadData();
		return false;
	}

	@Override
	public void handleEvent(EventContext<R> eventContext) {
	}

	@Override
	public void postHandle(EventContext<R> eventContext) {
		// TODO Auto-generated method stub
		
	}

}
