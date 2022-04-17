package checker.alerts;

public class EmailAlert extends Alert {

    public EmailAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "Email Alert";
    }

    @Override
    public boolean sendAlert() {

        //
        //  TODO: implement
        //

        return false;
    }

}
