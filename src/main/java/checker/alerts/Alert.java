package checker.alerts;

import checker.conditions.AlertCondition;
import checker.sites.Site;

/** A generic alert */
public abstract class Alert {

    /** Create an alert with the given message
     * @param message the message to track for this alert */
    public Alert(final String message) {
        this.message = message;
        // default no condition
        this.alertCondition = null;
    }

    private String message;
    private AlertCondition alertCondition;

    public String getMessage() {
        return this.message;
    }

    public boolean hasCondition() {
        return alertCondition != null;
    }

    public AlertCondition getAlertCondition() {
        return this.alertCondition;
    }

    /** Set an alert condition on this alert.
     * @param alertCondition the condition that must be evaluated */
    public void setAlertCondition(AlertCondition alertCondition) {
        this.alertCondition = alertCondition;
    }

    public abstract boolean send(Site site);

    /** Return the alert name to display */
    public abstract String getName();

    @Override
    public String toString() {
        return "Alert " + this.message;
    }

}
