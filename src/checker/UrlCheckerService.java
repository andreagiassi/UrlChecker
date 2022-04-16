package checker;

import checker.alert.Alert;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UrlCheckerService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UrlCheckerService() {
        sites = new ArrayList<>();
    }

    private ArrayList<Site> sites;

    public void addSite(Site site) {
        sites.add(site);
    }

    public void checkSites() {
        for (Site site : sites) {
            checkSite(site);
        }
    }

    public void checkSite(Site site) {
        // check if site is enabled
        if (!site.isEnabledCheck()) {
            SiteStatus status = site.getStatus();
            status.setHttpStatus(-1);
            status.setLastCheckDt(LocalDateTime.now());

            System.out.println(site + " (skip)");
            return ;
        }

        // perform the http call
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(site.getTimeout())).build();

        URI uri = URI.create(site.getUri());
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int contentLength = response.body().length();
            HttpClient.Version version = response.version();

            // store last status data
            SiteStatus status = site.getStatus();
            status.setHttpStatus(response.statusCode());
            status.setContentLength(contentLength);
            status.setVersion(version);
            status.setLastCheckDt(LocalDateTime.now());

            // handle the alert
            Alert alert = site.getAlert();
            if (alert != null) {
                // check status code - http error
                if (response.statusCode() != 200) {
                    // set last date of failure
                    status.setLastFailureDt(LocalDateTime.now());

                    sendAlert(site);
                }
            }

            System.out.println(site);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void sendAlert(Site site) {
        if (site.hasAlert()) {
            Alert alert = site.getAlert();
            boolean sent = alert.sendAlert();
            if (!sent) {
                System.out.println("Error sending the alert for the site " + site);
            }
        }
    }

    public void dump() {
        System.out.println("Site status:");
        for (Site site : sites) {
            System.out.println(getSiteInfo(site));
        }
    }

    private String getSiteInfo(final Site site) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(site.getStatus().getVersion() + " ");
        stringBuilder.append(site.getStatus().getHttpStatus() + " ");
        stringBuilder.append(site.getUri() + " ");

        if (site.hasAlert()) {
            stringBuilder.append(site.getAlert().getName() + " ");
        }

        stringBuilder.append(site.getStatus().getLastCheckDt().format(formatter));

        return stringBuilder.toString();
    }

}
