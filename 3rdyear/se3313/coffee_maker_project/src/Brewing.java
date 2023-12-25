public class Brewing extends CoffeeState{
    Brewing(CoffeeController coffeeController) {
        super(coffeeController);
    }

    @Override
    String start() {
        return "The machine is currently brewing, you cannot start!";
    }

    @Override
    String filled() {
        return "The machine is currently brewing, you cannot fill!";
    }

    @Override
    String reset() {
        return "You cannot reset the machine while brewing is in process!";
    }
}
