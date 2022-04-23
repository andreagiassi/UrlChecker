package checker.alerts;

import checker.sites.Site;

/** A void implementation for an email alert */
public class EmailAlert extends Alert {

    public EmailAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "Email Alert";
    }

    @Override
    public boolean send(Site site) {

        //
        //  TODO: implement smtp email alert
        //

        return false;
    }

}
