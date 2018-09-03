package observer;

/**
 * 
 * @author shi 
 * �۲���ģʽ��Ҫ������ӿڣ����������࣬�۲��߽ӿڣ��۲���ʵ���ࣨ���ܶ�������ɹ۲�ӿ����.
 * java�Դ�API���й۲���ģʽ��ʵ��
 * 
 */

public class ModelObserverClient {
    public static void main(String[] args) {
        Subject sub = new Subject();//����
        //��˭���۲�
        IObserver observer1= new Observer1();
        IObserver observer2= new Observer2();
        //���ӹ۲���
        sub.attach(observer1);
        sub.attach(observer2);
        //���⿪ʼ���۲����Զ��۲쵽��
        sub.actionStart();
        sub.actionEnd();
    }
    
    
}
/**
 *  �۲��߽ӿ� 
 */
interface IObserver {
    public void update(Object o);
}
/**
 *  �۲��߽ӿ�ʵ����
 */
class Observer1 implements IObserver {

    @Override
    public void update(Object o) {
        System.out.println("the first know  : " + o.toString());
//      ��������
    }

}
/**
 *  �۲��߽ӿ�ʵ����
 */
class Observer2 implements IObserver {

    @Override
    public void update(Object o) {
        System.out.println("the second know  : " + o.toString());
//      ��������
    }

}

/**
 *  ���۲��߶�Ӧ�̳д��࣬ʵ�ֿɱ��۲졣
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
 *  ����ӿڣ�Ҫ�۲���Щ������
 */
interface ISubject {
    public void actionStart();
    public void actionEnd();
}

/**
 *   ���������ȡ������֪ͨ�۲��ߡ�
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
