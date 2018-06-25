package ald.rc.api.core;

public interface EventHandler<R> {
	boolean preHandle(EventContext<R> eventContext);
	void handleEvent(EventContext<R> eventContext);
	void postHandle(EventContext<R> eventContext);
}
