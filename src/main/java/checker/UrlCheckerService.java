package checker;

import checker.alerts.Alert;
import checker.sites.Site;
import checker.sites.SiteStatus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

/** A URL service that check a site or a list of site. */
public class UrlCheckerService {

    /** Http status OK */
    public static final int HTTP_OK = 200;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UrlCheckerService() {
        sites = new ArrayList<>();
    }

    private ArrayList<Site> sites;

    /** Append the given site to the site list */
    public void addSite(Site site) {
        sites.add(site);
    }

    /** Check the site list */
    public void checkSites() {
        for (Site site : sites) {
            checkSite(site);
        }
    }

    /** Check a single site */
    public SiteStatus check(String uri) {
        Site site = new Site(uri);
        checkSite(site);
        return site.getStatus();
    }

    /** Check a single site and return a SiteStatus */
    public Optional<SiteStatus> checkSite(Site site) {
        if (!site.isEnabledCheck()) {
            SiteStatus status = site.getStatus();
            status.setHttpStatus(-1);
            return Optional.of(status);
        }

        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(site.getTimeout())).build();

        URI uri = URI.create(site.getUri());
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            handleResponse(site, response);

            return Optional.of(site.getStatus());
        } catch(Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /** Handle the response and retrieve the http status data. Check the alert conditions. */
    private void handleResponse(Site site, HttpResponse<String> response) {
        // store status data
        SiteStatus status = site.getStatus();
        status.setFromResponse(response);

        Optional<Alert> alertOpt = site.getAlertOptional();
        if (alertOpt.isPresent()) {
            // check status code - http error
            Alert alert = alertOpt.get();
            if (alert.hasCondition()) {
                if (alert.getAlertCondition().check(response.statusCode())) {
                    sendAlert(alert, site);
                }
            } else {
                // standard check on the http status 200 OK
                if (response.statusCode() != HTTP_OK) {
                    sendAlert(alert, site);
                }
            }
        }
    }

    /** Call the alert method for the given site */
    private void sendAlert(Alert alert, Site site) {
        // set last date of failure
        site.getStatus().setLastFailureDt(LocalDateTime.now());
        boolean sent = alert.send(site);
        if (!sent) {
            System.out.println("Error sending the alert for the site " + site);
        }
    }

    /** Print the site list */
    public void dumpSites() {
        System.out.println("Site status:");
        for (Site site : sites) {
            System.out.println(getSiteInfo(site));
        }
    }

    /** Print a single site information */
    private String getSiteInfo(final Site site) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(site.getStatus().getLastCheckDt().format(formatter));
        stringBuilder.append(" ");

        stringBuilder.append(site.getStatus().getVersion() + " ");
        stringBuilder.append(site.getStatus().getHttpStatus() + " ");
        stringBuilder.append(site.getUri() + " ");

        if (site.hasAlert()) {
            Alert alert = site.getAlertOptional().get();
            stringBuilder.append("(" + alert.getName() + ")");
        }

        return stringBuilder.toString();
    }

}
