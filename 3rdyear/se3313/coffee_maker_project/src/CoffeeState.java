public abstract class CoffeeState {
   CoffeeController coffeeController;

   CoffeeState(CoffeeController coffeeController) {
       this.coffeeController = coffeeController;
   }
    abstract String start();
    abstract String filled();

    abstract String reset();
}
