package shi.wire;

public class Lion implements Animal {

    public void eat() {
        System.out.println("Lion.eat()");
    }

    @Override
    public String toString() {
        return "I'm a lion";
    }
}
