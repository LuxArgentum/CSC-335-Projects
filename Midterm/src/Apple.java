class Apple extends Fruit {
    Apple(double costPerPound, int caloriesPerOunce) {
        super(costPerPound, caloriesPerOunce);
    }

    @Override
    String growsOn() {
        return "Apples grow on trees";
    }

    @Override
    boolean isLowCalorie() {
        // Fruits are low calorie if there are
        // 10 or fewer calories per ounce.

        if (caloriesPerOunce <= 10)
            return true;
        else
            return false;
    }

    @Override
    double costPerOunce() {
        // 16 ounces in a pound
        return costPerPound / (double) 16;
    }
}
