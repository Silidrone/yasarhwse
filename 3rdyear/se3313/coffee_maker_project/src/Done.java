public class Done extends CoffeeState{
    Done(CoffeeController coffeeController) {
        super(coffeeController);
    }

    @Override
    String start() {
        return "You cannot start the machine before you reset it!";
    }

    @Override
    String filled() {
        return "You cannot fill the machine right now, it needs to be reset and then started first!";
    }

    @Override
    String reset() {
        coffeeController.setCoffeeState(new Empty(coffeeController));
        return "The machine has been reset!";
    }
}
