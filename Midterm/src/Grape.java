class Grape extends Fruit {
    Grape(double costPerPound, int caloriesPerOunce) {
        super(costPerPound, caloriesPerOunce);
    }

    @Override
    String growsOn() {
        return "Grapes grow on vines";
    }

    @Override
    boolean isLowCalorie() {

        if (caloriesPerOunce <= 10)
            return true;
        else
            return false;
    }

    @Override
    double costPerOunce() {
        return costPerPound / (double) 16;
    }
}
