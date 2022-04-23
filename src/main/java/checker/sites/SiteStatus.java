package checker.sites;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

/** Contains the site status */
public class SiteStatus {

    private int httpStatus;
    private int contentLength;
    private HttpClient.Version version;

    private LocalDateTime lastCheckDt;
    private LocalDateTime lastFailureDt;

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(int length) {
        this.contentLength = length;
    }

    public HttpClient.Version getVersion() {
        return this.version;
    }

    public void setVersion(HttpClient.Version version) {
        this.version = version;
    }
    public LocalDateTime getLastCheckDt() {
        return this.lastCheckDt;
    }

    public LocalDateTime getLastFailureDt() {
        return this.lastFailureDt;
    }

    public void setLastCheckDt(LocalDateTime dt) {
        this.lastCheckDt = dt;
    }

    public void setLastFailureDt(LocalDateTime dt) {
        this.lastFailureDt = dt;
    }

    @Override
    public String toString() {
        return this.version + " " + this.httpStatus + " Content " + this.contentLength + " byte on " + getLastCheckDt();
    }

    /** set this site status data from a given HttpResponse */
    public void setFromResponse(HttpResponse<String> response) {
        this.setHttpStatus(response.statusCode());
        this.setContentLength(response.body().length());
        this.setVersion(response.version());
        this.setLastCheckDt(LocalDateTime.now());
    }

}
