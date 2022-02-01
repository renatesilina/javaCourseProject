package musicapplication.playlistresults;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Class for storing a single track and a date when it's been added to the top playlist
 */
@JsonIgnoreProperties(value = {"added_by", "is_local", "primary_color", "video_thumbnail"})
public class TrackItems {
    @JsonAlias("added_at")
    Date addDate;
    SingleTrack track;

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public SingleTrack getTrack() {
        return track;
    }

    public void setTrack(SingleTrack track) {
        this.track = track;
    }
}
