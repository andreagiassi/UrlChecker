package checker.alerts;

public class FileAlert extends Alert {

    public FileAlert(String message) {
        super(message);
    }

    @Override
    public String getName() {
        return "File Alert";
    }

    @Override
    public boolean sendAlert() {

        //
        //  TODO: implement file alert
        //

        return false;
    }

}
