public class InState extends AbstractStudent {
    public InState(String name, int units) {
        super(name, units);
    }

    @Override
    public double getTuitionDue() {
        if (units < 12.0) {
            return 500.00 * units;
        } else {
            return 6000.00;
        }
    }
}
