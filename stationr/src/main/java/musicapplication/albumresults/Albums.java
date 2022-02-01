package musicapplication.albumresults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for storing Album items, retrieved from spotify web API
 */
@JsonIgnoreProperties(value = {"href", "next", "offset", "previous", "total", "limit"})
public class Albums {
    Items[] items;

    public Items[] getItems() {
        return items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }
}
