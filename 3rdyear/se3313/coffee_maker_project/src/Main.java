public class Main {
    public static void main(String[] args) {
        CoffeeController coffeeController = new CoffeeController();
        coffeeController.setBrewBehavior(new FastBrew());
    }
}