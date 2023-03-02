public class U2 extends Rocket {
    U2() {
        super();
        this.bodyWeight = 36000;
        this.cargoLimit = 58000;
        this.rocketCost = 120000000;
    }
    @Override
    public double getChanceOfExplosion() {
        return ((double)super.getCurrentWeight() / super.cargoLimit) * 5; // 4 times more than the ratio is 5 times the ratio
    }

    @Override
    public double getChanceOfCrash() {
        return ((double)super.getCurrentWeight() / super.cargoLimit) * 9; // 8 times more than the ratio is 9 times the ratio
    }
}