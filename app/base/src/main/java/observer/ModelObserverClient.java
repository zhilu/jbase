package observer;

/**
 * 
 * @author shi 
 * 观察者模式主要由主题接口，具体主题类，观察者接口，观察者实现类（可能多个），可观察接口组成.
 * java自带API中有观察者模式的实现
 * 
 */

public class ModelObserverClient {
    public static void main(String[] args) {
        Subject sub = new Subject();//主题
        //由谁来观察
        IObserver observer1= new Observer1();
        IObserver observer2= new Observer2();
        //增加观察者
        sub.attach(observer1);
        sub.attach(observer2);
        //主题开始，观察者自动观察到。
        sub.actionStart();
        sub.actionEnd();
    }
    
    
}
/**
 *  观察者接口 
 */
interface IObserver {
    public void update(Object o);
}
/**
 *  观察者接口实现类
 */
class Observer1 implements IObserver {

    @Override
    public void update(Object o) {
        System.out.println("the first know  : " + o.toString());
//      其他动作
    }

}
/**
 *  观察者接口实现类
 */
class Observer2 implements IObserver {

    @Override
    public void update(Object o) {
        System.out.println("the second know  : " + o.toString());
//      其他动作
    }

}

/**
 *  被观察者都应继承此类，实现可被观察。
 */
class Obserable{
    private java.util.Vector<IObserver> observers = new java.util.Vector<IObserver>();
    public void attach(IObserver o) {
        observers.addElement(o);
    }

    public void detach(IObserver o) {
        observers.removeElement(o);
    }

    public void notifyObservers(Object obj) {
        for (IObserver o : observers) {
            o.update(obj.toString());
        }
    }

}
/**
 *  主题接口，要观察那些动作。
 */
interface ISubject {
    public void actionStart();
    public void actionEnd();
}

/**
 *   具体主题采取动作后通知观察者。
 */
class Subject extends Obserable implements ISubject{

    @Override
    public void actionStart() {
        System.out.println("action start >>>");
        notifyObservers("start");
    }

    @Override
    public void actionEnd() {
        // TODO Auto-generated method stub
        notifyObservers("end");
    }
    
}
