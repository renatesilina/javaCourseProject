package musicapplication.albumresults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for storing links to images
 */
@JsonIgnoreProperties(value = {"height", "width"})
public class Images {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
