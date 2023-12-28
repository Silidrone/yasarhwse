public class Done extends CoffeeState{
    Done(CoffeeController coffeeController) {
        super(coffeeController);
    }

    @Override
    String start() {
        return "Machine needs to be reset first!";
    }

    @Override
    String filled() {
        return "Machine needs to be reset first!";
    }

    @Override
    String reset() {
        coffeeController.setCoffeeState(new Empty(coffeeController));
        return "The machine has been reset!";
    }
}
