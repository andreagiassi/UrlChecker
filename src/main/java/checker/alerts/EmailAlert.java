package checker.alerts;

public class EmailAlert extends Alert {

    public EmailAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "EmailAlert";
    }

    @Override
    public boolean sendAlert() {

        //
        //  TODO: implement
        //

        return false;
    }

}
