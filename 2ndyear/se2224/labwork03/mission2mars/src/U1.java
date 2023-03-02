public class U1 extends Rocket {
    U1() {
        super();
        this.bodyWeight = 20000;
        this.cargoLimit = 36000;
        this.rocketCost = 1000000;
    }
    @Override
    public double getChanceOfExplosion() {
        return ((double)super.getCurrentWeight() / super.cargoLimit) * 6; // 5 times more than the ratio is 6 times the ratio
    }

    @Override
    public double getChanceOfCrash() {
        return ((double)super.getCurrentWeight() / super.cargoLimit);
    }
}
