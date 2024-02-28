public class FruitHierarchy {

    public static void main(String[] args) {

        // First argument is cost per pound.

        // There are 16 ounces in a pound.

        // The 2nd argument is calories per ounce.

        Fruit a = new Apple(0.80, 12);

        Fruit g = new Grape(1.60, 9);

        System.out.println(a.growsOn());

        // Fruits are low calorie if there are

        // 10 or fewer calories per ounce.

        System.out.println("Calories <= 10 per ounce? " + a.isLowCalorie());

        // There are 16 ounces per pound

        System.out.println("Cost: " + a.costPerOunce());

        System.out.println();

        System.out.println(g.growsOn());

        System.out.println(g.isLowCalorie());

        System.out.println("Cost: " + g.costPerOunce());

    }
}