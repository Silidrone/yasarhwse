import java.util.ArrayList;

public class Rocket implements SpaceShip{

    protected ArrayList<Item> items = new ArrayList<>();
    protected int bodyWeight;
    protected int cargoLimit;
    protected int rocketCost;

    public void loadItem(Item item) {
        items.add(item);
    }
    public int getCargoWeight() {
        int cargoWeight = 0;
        for (Item item : items) {
            cargoWeight += item.getWeight();
        }

        return cargoWeight;
    }

    public int getCurrentWeight() {
        return bodyWeight + getCargoWeight();
    }

    public double getChanceOfExplosion() {
        return Math.random(); //as it is not specified in the lab work slide I just chose a random number
    }
    public double getChanceOfCrash(){
        return Math.random(); //as it is not specified in the lab work slide I just chose a random number
    }

    public int getRocketCost() {
        return rocketCost;
    }

    @Override
    public boolean launch() {
        return (100 * Math.random()) >= this.getChanceOfExplosion();
    }

    @Override
    public boolean land() {
        return (100 * Math.random()) >= this.getChanceOfCrash();
    }

    @Override
    public boolean canCarry(Item item) {
        return (getCargoWeight() + item.getWeight()) <= cargoLimit;
    }
}
