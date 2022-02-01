package musicapplication.views;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.theme.lumo.Lumo;
import musicapplication.views.albums.AlbumsView;
import musicapplication.views.artists.ArtistsView;
import musicapplication.views.songs.ChartsView;
import musicapplication.views.songs.SongsView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import java.util.ArrayList;
import java.util.List;

/**
 * The main view of the webpage is a top-level placeholder for other views.
 */
@PWA(name = "StationR", shortName = "StationR", enableInstallPrompt = false)
@Theme(themeFolder = "stationr")
@PageTitle("Main")
public class MainLayout extends AppLayout {

    /**
     * An inner class for making other views
     */
    public static class MenuItemInfo {

        // Menu item name
        private String text;
        // Small pictures shown next to menu item
        private VaadinIcon icon;
        private Class<? extends Component> view;

        // Create a menu item for a view
        public MenuItemInfo(String text, VaadinIcon icon, Class<? extends Component> view) {
            this.text = text;
            this.icon = icon;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public VaadinIcon getIconClass() {
            return icon;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }

    // Title of the page
    private H1 viewTitle;

    /**
     * Constructor that adds navigation bar and a view drawer to the main page
     */
    public MainLayout() {
        // Shows the drawer content right away, moving nav bar to the side
        //setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    /**
     * Method for creating content for navbar
     *
     * @return customized header for navbar
     */
    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        // Toggle button styling
        toggle.addClassName("text-secondary");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1("StationR");
        viewTitle.addClassNames("m-0", "text-l");

        // New header for navbar with toggle and title
        Header header = new Header(toggle, viewTitle);
        // Styling for header
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center", "w-full");
        return header;
    }

    /**
     * Method for creating name navigation and footer for the drawer
     *
     * @return the customized section
     */
    private Component createDrawerContent() {
        H2 appName = new H2("StationR");
        // Styling
        appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
        return section;
    }

    /**
     * Method for creating a navigation, with list of links to other views, for drawer
     *
     * @return customized navigation
     */
    private Nav createNavigation() {
        Nav nav = new Nav();
        // Styling
        nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list, improves accessibility
        UnorderedList list = new UnorderedList();
        // Styling
        list.addClassNames("list-none", "m-0", "p-0");
        nav.add(list);

        // Create links to other views
        for (RouterLink link : createLinks()) {
            ListItem item = new ListItem(link);
            list.add(item);
        }
        return nav;
    }

    /**
     * Method for creating a list with route links
     *
     * @return list of route links to other views
     */
    private List<RouterLink> createLinks() {
        // Create an array with other menu items(other views)
        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                new MenuItemInfo("Songs", VaadinIcon.MUSIC, SongsView.class), //

                new MenuItemInfo("Albums", VaadinIcon.FOLDER_OPEN, AlbumsView.class), //

                new MenuItemInfo("Artists", VaadinIcon.GROUP, ArtistsView.class), //

                new MenuItemInfo("Charts", VaadinIcon.LINE_CHART, ChartsView.class), //

        };

        // Create a new list for the links
        List<RouterLink> links = new ArrayList<>();

        // Go through array, create and add links to the list
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

    /**
     * Method for creating a route link
     *
     * @param menuItemInfo created menu item with information about a view
     * @return a route link to a view
     */
    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        // Styling
        link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
        // Set item route to the class of the view
        link.setRoute(menuItemInfo.getView());

        // Create icon for the item
        Icon icon = menuItemInfo.getIconClass().create();
        icon.addClassNames("me-s", "text-l");

        // Add items name
        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("font-medium", "text-s");

        link.add(icon, text);
        return link;
    }

    /**
     * Method for creating a footer for the drawer
     *
     * @return customized footer component
     */
    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

        return layout;
    }

    /**
     * Method for setting the page title to current page after entering a new page
     */
    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    /**
     * Method for retrieving title of the current page
     *
     * @return title of the current page
     */
    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
