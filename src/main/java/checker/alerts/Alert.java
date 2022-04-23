package checker.alerts;

import checker.conditions.AlertCondition;
import checker.sites.Site;

import java.util.Optional;

/** A generic alert */
public abstract class Alert {

    /** Create an alert with the given message
     * @param message the message to track for this alert */
    public Alert(final String message) {
        this.message = message;
        // default no condition
        this.optionalAlertCondition = Optional.empty();
    }

    private String message;
    private Optional<AlertCondition> optionalAlertCondition;

    /** Return the message to give associated to this alert */
    public String getMessage() {
        return this.message;
    }

    /** Return true if this alert contains an alert condition */
    public boolean hasCondition() {
        return optionalAlertCondition.isPresent();
    }

    /** Return the alert condition set for this alert if exists, null otherwise */
    public Optional<AlertCondition> getOptionalAlertCondition() {
        return this.optionalAlertCondition;
    }

    /** Set an alert condition on this alert.
     * @param optionalAlertCondition the condition that must be evaluated */
    public void setOptionalAlertCondition(AlertCondition optionalAlertCondition) {
        this.optionalAlertCondition = Optional.of(optionalAlertCondition);
    }

    /** Send the alert for the given site */
    public abstract boolean send(Site site);

    /** Return the alert name to display */
    public abstract String getName();

    @Override
    public String toString() {
        return "Alert " + this.message;
    }

}
