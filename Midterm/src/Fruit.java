abstract class Fruit {

    protected double costPerPound;
    protected int caloriesPerOunce;

    Fruit(double costPerPound, int caloriesPerOunce) {
        this.costPerPound = costPerPound;
        this.caloriesPerOunce = caloriesPerOunce;
    }

    abstract String growsOn();

    abstract boolean isLowCalorie();

    abstract double costPerOunce();
}
