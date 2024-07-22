// Use case: Creating objects without specifying the exact class.
public abstract class Animal {
    public abstract String speak();
}
public class Dog extends Animal {
    public String speak() {
        return "Woof!";
    }
}
public class Cat extends Animal {
    public String speak() {
        return "Meow!";
    }
}
public class AnimalFactory {
    public static Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "dog":
                return new Dog();
            case "cat":
                return new Cat();
            default:
                return null;
        }
    }
} 
class CreationalFactory{
    public static void main(String[] args) {
        Animal animal = AnimalFactory.createAnimal("dog");
        System.out.println(animal.speak());
    }
}
