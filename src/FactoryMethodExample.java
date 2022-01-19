interface Messenger {

    public void sendMessage();

    public String getMessage();

    public default void die() {
        System.out.println("Rather messager's users or messager's servers died, probably both of them.");
    }
}

abstract class Product { }

class Viber implements Messenger {

    public void sendMessage() {

        System.out.println("Отправляем сообщение в Viber!");
    }

    public String getMessage() {
        String message = "Читаем сообщение в Viber!";
        return message;
    }
}

class ConcreteProductA extends Product { }

class ConcreteProductB extends Product { }

abstract class Creator {
    public Messenger messenger;
    public abstract Product factoryMethod();
}

class ConcreteCreatorA extends Creator {

    ConcreteCreatorA() {
        setUpViber();
    }

    @Override
    public Product factoryMethod() { return new ConcreteProductA(); }
    public void setUpViber() {
        this.messenger = new Viber();
    }
}

class ConcreteCreatorB extends Creator {
    @Override
    public Product factoryMethod() { return new ConcreteProductB(); }
}

public class FactoryMethodExample {
    public static void main(String[] args) {
        // an array of creators
        Creator[] creators = {new ConcreteCreatorA(), new ConcreteCreatorB()};

        // iterate over creators and create products
        for (Creator creator: creators) {
            Product product = creator.factoryMethod();
            System.out.printf("Created {%s}\n", product.getClass());
            try {
                System.out.printf("With messenger - {%s}", creator.messenger.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
