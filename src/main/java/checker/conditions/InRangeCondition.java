package checker.conditions;

public class InRangeCondition extends AlertCondition {

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

}
