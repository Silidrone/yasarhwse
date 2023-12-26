import java.util.Timer;
import java.util.TimerTask;

public class Idle extends CoffeeState{
    Idle(CoffeeController coffeeController) {
        super(coffeeController);
    }

    @Override
    String start() {
        coffeeController.startBrew();
        coffeeController.setCoffeeState(new Brewing(coffeeController));
        return "Started brewing coffee!";
    }

    @Override
    String filled() {
        return "You've already filled the machine!";
    }

    @Override
    String reset() {
        return "The machine hasn't finished brewing the filled coffee!";
    }
}
