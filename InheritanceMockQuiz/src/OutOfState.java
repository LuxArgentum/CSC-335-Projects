public class OutOfState extends AbstractStudent {
    public OutOfState(String name, int units) {
        super(name, units);
    }

    @Override
    public double getTuitionDue() {
        if (units < 12.0) {
            return 1000.00 * units + 150.00;
        } else {
            return 12000.00 + 150.00;
        }
    }
}
