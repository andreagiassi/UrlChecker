package checker.conditions;

public abstract class AlertCondition {

    public AlertCondition() {
        // empty impl
    }

    public abstract boolean check(int httpStatus);

}
