package musicapplication.views.artists;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import musicapplication.SearchError;
import musicapplication.artistresults.ArtistItems;
import musicapplication.artistresults.ListOfArtists;
import musicapplication.artistresults.toptracks.ListOfTopTracks;
import musicapplication.artistresults.toptracks.Tracks;
import musicapplication.servercommunication.Search;
import musicapplication.views.MainLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Class for showing information about artists
 */
@PageTitle("Artists")
@Route(value = "artists", layout = MainLayout.class)
public class ArtistsView extends VerticalLayout {

    public ArtistsView() {
        setPadding(true);

        searchArtist();


    }

    /**
     * Method for creating components for user input
     * Adds components to the view
     * Sets information retrieval in action
     */
    private void searchArtist() {

        TextField name = new TextField("Artist name");
        name.setWidth("280px");

        Button search = new Button("Search");
        search.setAutofocus(true);
        search.setWidth("284px");
        search.addClickListener(e -> getArtistItems(name.getValue()));

        setAlignItems(Alignment.CENTER);
        add(name, search);
    }

    /**
     * Method for retrieving artist information from API
     * Modifies user input string
     * Shows error if nothing is found
     *
     * @param artistName user input
     */
    private void getArtistItems(String artistName) {

        // Replace spaces in search string
        artistName = artistName.replaceAll(" ", "%20");
        Search artist = new Search(artistName, 5);
        // Store list with artists
        ListOfArtists artists = artist.getArtists();

        // On successful search
        if (artists != null) {
            // Extract artist items
            ArtistItems[] artistItems = artists.getArtists().getItems();
            setItems(artistItems);

        } else {
            SearchError error = new SearchError();
            error.showError();
        }

    }

    /**
     * Method for making components with artist information
     * Adds components to the view
     *
     * @param artistItems list with artists information
     */
    private void setItems(ArtistItems[] artistItems) {

        for (ArtistItems artist : artistItems) {
            // Put items on page
            H1 artistName = new H1(artist.getName());
            artistName.getElement().getStyle().set("margin-bottom","0px");
            H4 artistGenres = new H4(artist.getGenres());
            H4 artistPopularity = new H4("Popularity: " + artist.getPopularity()+"\t, "+"       Followers: "+artist.getFollowers().getTotal());
            artistPopularity.getElement().getStyle().set("margin-top", "5px");
            VerticalLayout vLayout = new VerticalLayout(artistName, artistGenres, artistPopularity);
            vLayout.getElement().getStyle().set("padding-bottom", "0px");

            Image img = new Image(artist.getImages().getUrl(), "Artist image");
            // Add link to spotify page
            Button link = new Button("Open in Spotify");
            link.getElement().getStyle().set("margin-left", "90px");
            // Open in a new tab
            link.addClickListener(e -> UI.getCurrent().getPage().open(artist.getSpotifyUrl(), "_blank"));

            VerticalLayout vLayout2 = new VerticalLayout(img, link);
            vLayout2.setWidth("370px");
            vLayout2.setHorizontalComponentAlignment(Alignment.CENTER);
            HorizontalLayout hLayout = new HorizontalLayout(vLayout2);
            // Add artist's top songs to page
            topSongs(artist.getId(), hLayout);
            hLayout.setWidthFull();
            hLayout.setAlignItems(Alignment.START);

            add(vLayout, hLayout);
        }
    }

    /**
     * Method for retrieving information about artist's top songs
     * Makes a grid component with information about songs and adds to the view
     *
     * @param artistID API id for the artist
     * @param hLayout  horizontal layout with artist information
     */
    private void topSongs(String artistID, HorizontalLayout hLayout) {

        Search topSongs = new Search(artistID, 1);
        ListOfTopTracks topTracks = topSongs.getTopSongs();
        Tracks[] artistsTopTracks = topTracks.getTracks();

        // Make page components for tracks
        Grid<Tracks> tracks = new Grid<>(Tracks.class, false);
        tracks.addComponentColumn(tracks1 -> new Image(tracks1.getAlbumImage(), tracks1.getAlbumImage()))
                .setHeader("Image").setAutoWidth(true);
        tracks.addColumn(Tracks::getName).setHeader("Name")
                .setFooter(artistsTopTracks.length + " songs total");
        tracks.addColumn(Tracks::getDurationString).setHeader("Duration")
                .setFooter(totalDuration(artistsTopTracks)).setTextAlign(ColumnTextAlign.CENTER);
        tracks.addColumn(Tracks::getPopularity).setHeader("Popularity")
                .setFooter(avgPopularity(artistsTopTracks)).setTextAlign(ColumnTextAlign.CENTER);
        tracks.addColumn(Tracks::getAlbumName).setHeader("Album");

        // TODO: adjust hLayout so that grid have no limitations going downwards
        // Adjust grid height, no scrolling needed
        tracks.setAllRowsVisible(true);
        // Go through tracks and add them to the page
        tracks.setItems(artistsTopTracks);

        // Add a nice theme for the grid
        tracks.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        tracks.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        tracks.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // Add track grid to same horizontal layout as artist image
        hLayout.add(tracks);
    }

    /**
     * Method for calculating total duration of artist's to songs
     *
     * @param topTracks artist's top songs
     * @return total duration of the songs
     */
    private String totalDuration(Tracks[] topTracks) {
        int totalDuration = 0;

        for (Tracks t : topTracks) {
            totalDuration += t.getDuration();
        }
        int minutes = (totalDuration / 1000) / 60;
        int seconds = (totalDuration / 1000) % 60;

        return "" + minutes + "." + seconds + " duration total";
    }

    /**
     * Method for calculating average popularity of artist's top songs
     *
     * @param topTracks artist's top songs
     * @return average popularity of the songs
     */
    private String avgPopularity(Tracks[] topTracks) {
        int sumPopularity = 0;

        for (Tracks t : topTracks) {
            sumPopularity += t.getPopularity();
        }
        return String.valueOf((sumPopularity / topTracks.length)).concat(" popularity avg");
    }
}
