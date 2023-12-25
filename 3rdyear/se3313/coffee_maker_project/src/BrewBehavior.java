import java.util.Timer;
import java.util.TimerTask;

public abstract class BrewBehavior {
    public void brew(CoffeeController coffeeController) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                coffeeController.finishBrew(coffeeController.getCurrentCoffees());
                coffeeController.setCoffeeState(new Done(coffeeController));
                timer.cancel();
            }
        }, brewTime());
    }

    protected abstract int brewTime();
}
