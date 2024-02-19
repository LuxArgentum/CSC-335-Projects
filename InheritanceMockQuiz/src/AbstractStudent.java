public abstract class AbstractStudent {

    protected String name;
    protected int units;

    public AbstractStudent(String name, int units) {
        this.name = name;
        this.units = units;
    }

    abstract public double getTuitionDue();
}
