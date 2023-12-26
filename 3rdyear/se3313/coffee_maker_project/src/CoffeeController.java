public class CoffeeController {
    protected CoffeeModel coffeeModel;
    protected CoffeeView coffeeView;

    protected CoffeeState coffeeState;

    protected BrewBehavior brewBehavior;

    int currentCoffees;

    CoffeeController() {
        this.coffeeModel = new CoffeeModel();
        this.coffeeView = new CoffeeView(this);
        this.coffeeState = new Empty(this);
        this.currentCoffees = 0;
        coffeeModel.registerViewObserver(coffeeView);
        coffeeView.init();
    }

    public void setBrewBehavior(BrewBehavior brewBehavior) {
        this.brewBehavior = brewBehavior;
    }
    public void startBrew() {
        brewBehavior.brew(this);
    }

    public void start() {
        coffeeView.setMessage(coffeeState.start());
    }

    public void filled(String cupsToFillText) {
        try {
            setCurrentCoffees(Integer.parseInt(cupsToFillText));
            coffeeView.setMessage(coffeeState.filled());
        } catch (NumberFormatException e) {
            coffeeView.setMessage("Please enter a valid number of coffees to be filled in!");
        }
    }

    public void reset() {
        coffeeView.clearInputs();
        coffeeView.setMessage(coffeeState.reset());
    }

    public void updateTotalCups() {
        coffeeModel.updateTotalCount();
    }

    void finishBrew(int count) {
        coffeeView.setMessage("Finished brewing " + count + " coffees");
        coffeeModel.finishBrew(count);
    }

    void setCoffeeState(CoffeeState coffeeState) {
        this.coffeeState = coffeeState;
        switch (coffeeState) {
            case Idle idle -> coffeeView.turnOnIdle();
            case Brewing brewing -> coffeeView.turnOnBrewing();
            case Done done -> coffeeView.turnOnDone();
            case null, default -> coffeeView.turnOffIndicators();
        }
    }

    void setCurrentCoffees(int currentCoffees) {
        this.currentCoffees = currentCoffees;
    }

    int getCurrentCoffees() {
        return this.currentCoffees;
    }
}
