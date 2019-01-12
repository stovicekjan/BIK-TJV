package cz.cvut.fit.tjv.cv.tvseries.webui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.vaadin.ui.themes.ValoTheme;
import cz.cvut.fit.tjv.cv.tvseries.restclient.PersonClient;
import cz.cvut.fit.tjv.cv.tvseries.restclient.SerieClient;
import cz.cvut.fit.tjv.cv.tvseries.restclient.SeriesClientException;
import cz.cvut.fit.tjv.cv.tvseries.service.PersonDTO;
import cz.cvut.fit.tjv.cv.tvseries.service.SerieDTO;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        SerieClient serieClient = SerieClient.getInstance();
        PersonClient personClient = PersonClient.getInstance();
        
        private Collection<SerieDTO> seriesData = null;
        private Collection<PersonDTO> personData = null;
        
        try {
            seriesData = new ArrayList(serieClient.retrieveAllJson());
            personData = new ArrayList(personClient.retrieveAllJson());
        } catch (SeriesClientException e) {
            Notification.show("The service is not running", Notification.Type.ERROR_MESSAGE);
        }
        
        Label seriesHead = new Label("Series");
        seriesHead.addStyleName(ValoTheme.LABEL_H2);
        
        
        Grid<SerieDTO> seriesGrid = new Grid<>(SerieDTO.class);
        seriesGrid.removeColumn("added");
        seriesGrid.addColumn("added", new LocalDateTimeRenderer("yyyy-MM-dd HH:mm"));
        seriesGrid.addColumn(s -> "Delete", new ButtonRenderer<>(event -> {
            serieClient.deleteById(event.getItem().getId());
            seriesData.remove(event.getItem());
        }))
        seriesGrid.setItems(seriesData);
        
        TextField serieId = new TextField("ID");
        TextField serieName = new TextField("Title");
        DateTimeField added = new DateTimeField("Added");
        
        Button submitSerie = new Button("Submit serie");
        
        seriesGrid.addSelectionListener(event -> {
            Optional<SerieDTO> sel = event.getFirstSelectedItem();
            if (sel.isPresent()) {
                SerieDTO selected = sel.get(); // dostanu primo objekt
                serieId.setValue(selected.getId() + "")
            }
        })
        
        HorizontalLayout serieForm = new HorizontalLayout(serieId, serieName, added, submitSerie);
        
        
        Label personsHead = new Label("Persons");
        seriesHead.addStyleName(ValoTheme.LABEL_H2);
        
        Grid<PersonDTO> personsGrid = new Grid<>(PersonDTO.class);
        
        TextField personId = new TextField("ID");
        TextField personName = new TextField("Name");
        Button submitPerson = new Button("Submit person");
        
        HorizontalLayout personForm = new HorizontalLayout(personId, personName, submitPerson);
        
        VerticalLayout personDataForm = new VerticalLayout(personsGrid, personForm);
        
        Button addToFavourites = new Button("Add to favourites");
        
        
        
        Label favouritesHead = new Label("Favourites");
        seriesHead.addStyleName(ValoTheme.LABEL_H2);
        Grid<SerieDTO> favouritesGrid = new Grid<>(SerieDTO.class);
        
        HorizontalLayout personView = new HorizontalLayout(personDataForm, addToFavourites, favouritesGrid);
        layout.addComponents(seriesHead, seriesGrid, personsHead, personView);
        

        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
