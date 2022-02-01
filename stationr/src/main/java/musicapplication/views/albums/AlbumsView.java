package musicapplication.views.albums;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import musicapplication.SearchError;
import musicapplication.albumresults.Items;
import musicapplication.albumresults.ListOfAlbums;
import musicapplication.albumresults.albumtracks.AlbumTracks;
import musicapplication.servercommunication.Search;
import musicapplication.views.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Class for showing information about albums
 */
@PageTitle("Albums")
@Route(value = "albums", layout = MainLayout.class)
public class AlbumsView extends VerticalLayout {

    public AlbumsView() {

        searchAlbums();
    }

    /**
     * Method that creates components for user input,
     * retrieves user input and sets information retrieval in action
     */
    private void searchAlbums() {
        TextField name = new TextField("Search for albums");
        name.setWidth("280px");
        Button search = new Button("Search");
        // Autofocus lets button be clicked with keyboard enter/space
        search.setAutofocus(true);
        search.setWidth("284px");

        search.addClickListener(e -> {
            getItems(name.getValue());
            //Notification.show("You searched for: " + name.getValue());
        });

        VerticalLayout vLayout = new VerticalLayout(name, search);
        vLayout.setHeight("150px");
        vLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Add components to page
        add(vLayout);
    }

    /**
     * Method for retrieving information about albums from API
     *
     * @param name user search input
     */
    private void getItems(String name) {
        // Replace spaces in search string
        name = name.replaceAll(" ", "%20");

        Search trackInfo = new Search(name, 5);
        // Getting list of albums
        ListOfAlbums albums = trackInfo.getAlbums();

        // Successful search
        if (albums != null) {
            // Extracting album items
            Items[] albumItems = albums.getAlbums().getItems();

            setItemsOnPage(albumItems);
        } else {
            SearchError error = new SearchError();
            error.showError();
        }

    }

    /**
     * Method for creating components with information about album
     * Adds components to the view
     *
     * @param albumItems array with information about an album
     */
    private void setItemsOnPage(Items[] albumItems) {
        for (Items i : albumItems) {
            // Make image from url
            Image img = new Image(i.getImages()[1].getUrl(), i.getImages()[1].getUrl());
            // Make text components
            H1 name = new H1(i.getName());
            H3 songs = new H3("Total songs: " + i.getTotalTracks());
            H3 release = new H3("Release date: " + i.getReleaseDate());

            // Redirect to spotify page
            Button linkButton = new Button("Open in Spotify");
            // Open the url "_blank" = in a new tab
            linkButton.addClickListener(e -> UI.getCurrent().getPage().open(i.getSpotifyUrl(), "_blank"));
            HorizontalLayout buttonsLayout = new HorizontalLayout();

            // Make layout for components
            VerticalLayout vLayout = new VerticalLayout(img);
            vLayout.setWidth("305px");
            VerticalLayout vLayout2 = new VerticalLayout(name, songs, release, buttonsLayout);
            // Set items to be at the bottom
            vLayout2.setJustifyContentMode(JustifyContentMode.END);

            // Add button to a new horizontal line
            HorizontalLayout hLayout = new HorizontalLayout(vLayout, vLayout2);
            hLayout.setWidthFull();
            hLayout.setJustifyContentMode(JustifyContentMode.CENTER);

            // Add hLayout in a vertical layout to make it possible to later add a song grid underneath album info
            VerticalLayout albumVLayout = new VerticalLayout(hLayout);
            // Add configured components to page
            add(albumVLayout);
            albumTracks(i.getId(), buttonsLayout, albumVLayout);
            buttonsLayout.add(linkButton);
        }

    }

    /**
     * Method for retrieving album songs from API
     * Creates, and adds to view, a grid with information about songs
     *
     * @param albumId       the id of the album
     * @param buttonsLayout horizontal layout for choice buttons
     */
    private void albumTracks(String albumId, HorizontalLayout buttonsLayout, VerticalLayout albumLayout) {

        // Retrieve album tracks
        Search search = new Search(albumId, 0);
        AlbumTracks albumTracks = search.getAlbumTracks();
        Items[] items = albumTracks.getItems();

        // Add song grid to details
        HorizontalLayout hLayout = new HorizontalLayout(makeSongGrid(items));
        hLayout.setWidthFull();

        // Make choice buttons for viewing songs
        Button viewSongs = new Button("View songs");
        viewSongs.addClickListener(e -> albumLayout.add(hLayout));
        Button hideSongs = new Button("Hide songs");
        hideSongs.addClickListener(e -> albumLayout.remove(hLayout));

        // Add buttons to page
        buttonsLayout.add(viewSongs, hideSongs);
    }

    /**
     * Method for making a grid component with information about album songs
     *
     * @param songItems array with song information
     * @return customized grid with song information
     */
    private Grid<Items> makeSongGrid(Items[] songItems) {

        Grid<Items> tracks = new Grid<>(Items.class, false);
        tracks.addComponentColumn(tracks1 -> makeIframe(tracks1.getId())).setAutoWidth(true);
        tracks.addColumn(Items::getName).setAutoWidth(true);
        tracks.addColumn(Items::getDurationString).setAutoWidth(true); //.setTextAlign(ColumnTextAlign.CENTER)
        tracks.addColumn(Items::getArtists).setAutoWidth(true);
        tracks.setAllRowsVisible(true);

        tracks.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        tracks.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        tracks.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        tracks.setItems(songItems);
        tracks.setWidthFull();

        return tracks;
    }

    /**
     * Method for making an iframe for album songs
     *
     * @param id songs id
     * @return customized iframe
     */
    private IFrame makeIframe(String id) {
        // Make an iframe play button
        IFrame play = new IFrame("https://open.spotify.com/embed/track/" + id);
        play.setHeight("80px");
        play.setWidth("80px");
        play.getStyle().set("border", "0px");

        return play;
    }
}
