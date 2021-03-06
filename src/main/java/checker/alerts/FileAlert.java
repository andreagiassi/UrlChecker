package checker.alerts;

import checker.sites.Site;

import java.io.BufferedWriter;
import java.io.FileWriter;

/** a concrete implementation for a file alert log */
public class FileAlert extends Alert {

    public static final String TEST_LOG = "test.log";
    private String fileName = TEST_LOG;

    public FileAlert(String message) {
        super(message);
    }

    public FileAlert(String fileName, String message) {
        super(message);
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return "File Alert";
    }

    @Override
    public boolean send(Site site) {
        try
        {
            // append log on file
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String fileLogText = site.toString() + " " + this.getMessage() + " " + getOptionalAlertCondition().toString();
            bw.write(fileLogText);

            bw.newLine();
            bw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
