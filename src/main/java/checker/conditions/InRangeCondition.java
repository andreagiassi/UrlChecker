package checker.conditions;

/** Implement an in range alert condition */
public class InRangeCondition extends AlertCondition {

    /** Create an in range alert condition from min to max http status
     * @min the minimum http status
     * @max the maximum http status
     */
    public InRangeCondition(int min, int max) {
        this.min = min;
        this.max = max;
    }

    private int min;
    private int max;

    @Override
    public boolean check(int httpStatus) {
        return httpStatus >= min && httpStatus <= max;
    }

    @Override
    public String toString() {
        return "[" + min + " / " + max + "]";
    }

}
