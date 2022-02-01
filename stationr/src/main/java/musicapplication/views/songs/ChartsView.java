package musicapplication.views.songs;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import musicapplication.playlistresults.CountriesTopPlaylist;
import musicapplication.playlistresults.PlaylistSongs;
import musicapplication.playlistresults.SingleTrack;
import musicapplication.playlistresults.TrackItems;
import musicapplication.servercommunication.Search;
import musicapplication.views.MainLayout;

/**
 * Class for showing countries top charts
 */
@PageTitle("Charts")
@Route(value = "charts", layout = MainLayout.class)
public class ChartsView extends VerticalLayout {

    public ChartsView() {

        chooseCountry();
    }

    /**
     * Method for making a choice box for countries
     * Sets the data retrieval in action
     */
    private void chooseCountry() {
        ComboBox<Countries> choiceBox = new ComboBox<>("Country");
        // Set all enum values from Countries class
        // Add filter for countries that matches user's input
        ComboBox.ItemFilter<Countries> filter = (countries, filterString) -> countries.getName().toLowerCase().startsWith(filterString.toLowerCase());
        choiceBox.setItems(filter, Countries.values());
        choiceBox.setItemLabelGenerator(Countries::getName);
        // Get the selected country's id
        choiceBox.addValueChangeListener(e -> {
            getPlaylist(e.getValue().getPlaylistId());
        });
        choiceBox.setWidth("200px");

        HorizontalLayout hLayout = new HorizontalLayout(choiceBox);
        hLayout.setWidthFull();
        hLayout.setHeight("100px");
        // Position choiceBox in the center of hLayout
        hLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(hLayout);
    }

    /**
     * Method for retrieving country's playlist information
     * Makes components for and adds information to the view
     *
     * @param countryId API id for country's playlist
     */
    private void getPlaylist(String countryId) {
        System.out.println("PLAYLIST ID: " + countryId);
        Search search = new Search(countryId, 1);
        CountriesTopPlaylist playlist = search.getTopListsCountry();

        // TODO, try to add date here?
        Image img = new Image(playlist.getImages(), playlist.getImages());
        img.setHeight("350px");
        img.setWidth("350px");

        H2 name = new H2(playlist.getName());
        H4 description = new H4(playlist.getDescription());

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        VerticalLayout vLayout = new VerticalLayout(name, description, buttonsLayout);
        vLayout.setJustifyContentMode(JustifyContentMode.END);
        vLayout.setPadding(false);
        HorizontalLayout hLayout = new HorizontalLayout(img, vLayout);

        add(hLayout);
        getPlaylistTracks(playlist.getTracks(), buttonsLayout);

    }

    /**
     * Method for retrieving information about playlist's songs
     * Makes components with information and adds them to the view
     *
     * @param tracks        songs in playlist
     * @param buttonsLayout layout for choice buttons
     */
    private void getPlaylistTracks(PlaylistSongs tracks, HorizontalLayout buttonsLayout) {
        TrackItems[] items = tracks.getItems();
        int position = 1;
        Button showTracks = new Button("View tracks");
        Button hideTracks = new Button("Hide tracks");

        for (TrackItems i : items) {
            H3 trackNumber = new H3(String.valueOf(position));
            trackNumber.setWidth("100px");
            position++;
//            Image img = new Image(i.getTrack().getAlbumImage(), "Playlist image");
//            img.setHeight("150px");
//            img.setWidth("150px");
            IFrame playImg = makeIframe(i.getTrack().getId());
            Div imgDiv = new Div(playImg);
            imgDiv.setWidth("200px");

            H3 name = new H3(i.getTrack().getName());
            H5 artistName = new H5(i.getTrack().getArtists());
            VerticalLayout vLayout = new VerticalLayout(name, artistName);
            vLayout.setWidth("500px");

            H3 duration = new H3(i.getTrack().getDurationString());
            VerticalLayout vLayout2 = new VerticalLayout(new H4("Duration"), duration);
            vLayout2.setWidth("200px");
            H3 popularity = new H3(String.valueOf(i.getTrack().getPopularity()));
            VerticalLayout vLayout3 = new VerticalLayout(new H4("Popularity"), popularity);
            vLayout3.setWidth("200px");

            HorizontalLayout hLayout = new HorizontalLayout(trackNumber, imgDiv,
                    vLayout, vLayout2, vLayout3);

            // Center vertical position for components in hLayout
            hLayout.setWidthFull();
            hLayout.setHeight("180px");
            hLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
            hLayout.setJustifyContentMode(JustifyContentMode.EVENLY);

            showTracks.addClickListener(e -> add(hLayout));
            hideTracks.addClickListener(e -> remove(hLayout));
            buttonsLayout.add(showTracks, hideTracks);
        }

    }

    /**
     * Method for making an iframe for a song
     *
     * @param id API id of the song
     * @return customized iframe
     */
    private IFrame makeIframe(String id) {
        // Make an iframe play button
        IFrame play = new IFrame("https://open.spotify.com/embed/track/" + id);
        play.setHeight("150px");
        play.setWidth("150px");
        play.getStyle().set("border", "0px");

        return play;
    }
}
