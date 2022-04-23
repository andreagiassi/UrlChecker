package checker.alerts;

import checker.sites.Site;

/** Implement a console log alert on standard output */
public class ConsoleAlert extends Alert {

    /** Create a console log alert */
    public ConsoleAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "Console alert";
    }

    /** Print the console message for the given site */
    @Override
    public boolean send(Site site) {
        // console log alert
        System.out.println("Console alert: " + this.getMessage());
        return true;
    }

}
