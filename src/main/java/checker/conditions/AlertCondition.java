package checker.conditions;

/** A generic condition to evaluate for an alert */
public abstract class AlertCondition {

    /** Check the implemented condition for the given http status code */
    public abstract boolean check(int httpStatus);

}
