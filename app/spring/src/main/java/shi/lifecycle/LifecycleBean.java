package shi.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 五月的仓颉 http://www.cnblogs.com/xrq730/p/5721366.html
 */
public class LifecycleBean implements InitializingBean, DisposableBean
{
    @SuppressWarnings("unused")
    private String    lifeCycleBeanName;
    
    public void setLifeCycleBeanName(String lifeCycleBeanName)
    {
        System.out.println("Enter LifecycleBean.setLifeCycleBeanName(), lifeCycleBeanName = " + lifeCycleBeanName);
        this.lifeCycleBeanName = lifeCycleBeanName;
    }

    public void destroy() throws Exception
    {
        System.out.println("Enter LifecycleBean.destroy()");
    }

    public void afterPropertiesSet() throws Exception
    {
        System.out.println("Enter LifecycleBean.afterPropertiesSet()");
    }
    
    public void beanStart()
    {
        System.out.println("Enter LifecycleBean.beanStart()");
    }
    
    public void beanEnd()
    {
        System.out.println("Enter LifecycleBean.beanEnd()");
    }
}
