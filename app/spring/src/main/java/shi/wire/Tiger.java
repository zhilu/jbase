package shi.wire;

public class Tiger implements Animal {

    public void eat() {
        System.out.println("Tiger.eat()");
    }
    
    @Override
    public String toString() {
        return "I'm a tiger";
    }
}
