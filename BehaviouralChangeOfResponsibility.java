//Use Case: Passing a request along a chain of handlers.
abstract class Handler {
    protected Handler successor;
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
    public abstract void handleRequest(String request);
}
class ConcreteHandler1 extends Handler {
    public void handleRequest(String request) {
        if (request.equals("handle1")) {
            System.out.println("Handled by Handler 1");
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
class ConcreteHandler2 extends Handler {
    public void handleRequest(String request) {
        if (request.equals("handle2")) {
            System.out.println("Handled by Handler 2");
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
class Main {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setSuccessor(handler2);

        handler1.handleRequest("handle1");
        handler1.handleRequest("handle2");
    }
}
