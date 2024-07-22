//Use Case: Notifying multiple observers when the state of a subject changes.
import java.util.ArrayList;
import java.util.List;

class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

ConcreteObserver1 extends Observer {
    public ConcreteObserver1(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("ConcreteObserver1: State changed to: " + subject.getState());
    }
}
ConcreteObserver2 extends Observer {
    public ConcreteObserver2(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("ConcreteObserver2: State changed to: " + subject.getState());
    }
}
class BehaviouralObservor {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new ConcreteObserver1(subject);
        new ConcreteObserver2(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}

