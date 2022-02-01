package musicapplication;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SearchError {

    public void showError() {

        // Create new notification
        Notification error = new Notification();
        error.addThemeVariants(NotificationVariant.LUMO_ERROR);

        // Create components and layout for notification
        Div errorText = new Div(new Text("No results found, invalid search parameters."));

        // Button with close icon in it
        Button close = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        close.addClickListener(e -> error.close());

        // Add components to layout
        HorizontalLayout hLayout = new HorizontalLayout(errorText, close);
        hLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        error.add(hLayout);
        error.open();
    }
}
