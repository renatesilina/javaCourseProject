package musicapplication.albumresults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for storing artist properties
 */
@JsonIgnoreProperties(value = {"external_urls", "href", "id"})
public class Artist {
    String name, type, uri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
