public class Empty extends CoffeeState {
    Empty(CoffeeController coffeeController) {
        super(coffeeController);
    }

    @Override
    String start() {
        return "The machine is empty, there is nothing to start!";
    }

    @Override
    String filled() {
        coffeeController.setCoffeeState(new Idle(coffeeController));
        return "Filled the machine with coffee!";
    }

    @Override
    String reset() {
        return "You cannot reset an empty coffee machine!";
    }
}
