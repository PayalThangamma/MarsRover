//Use Case: Controlling access to a resource.
class RealSubject {
    public String request() {
        return "RealSubject: Handling request.";
    }
}
class Proxy {
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }
    public String request() {
        // Perform access control or logging
        System.out.println("Proxy: Logging request");
        return realSubject.request();
    }
}
class Main {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Proxy proxy = new Proxy(realSubject);
        System.out.println(proxy.request());
    }
}
