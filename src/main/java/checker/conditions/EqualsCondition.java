package checker.conditions;

public class EqualsCondition extends AlertCondition {

    public EqualsCondition(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    private int httpStatus;

    @Override
    public boolean check(int httpStatus) {
        return this.httpStatus == httpStatus;
    }

    @Override
    public String toString() {
        return "[" + httpStatus + "]";
    }

}
