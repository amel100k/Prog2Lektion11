package opgave01.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import opgave01.controller.Controller;
import opgave01.models.Person;
import opgave01.models.Role;

import java.util.ArrayList;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    private final ListView<Person> lvwPerson = new ListView<>();
    private ListView<Person> lvwPersonFiltreret = new ListView<>();
    private final Controller controller = new Controller();
    private TextField txfPersonName;
    private final ToggleGroup group = new ToggleGroup();
    private ComboBox<Role> comboBox = new ComboBox<>();

    @Override
    public void stop() {

    }
    private void initContent(GridPane pane) {
        Label lblPerson = new Label("Personer");
        pane.add(lblPerson,0,0);
        pane.add(lvwPerson,0,1);
        lvwPerson.getItems().setAll(controller.getPeople());

        Label lblPersonName = new Label("Personens navn");
        pane.add(lblPersonName,0,2);
        txfPersonName = new TextField();
        pane.add(txfPersonName,1,2);

        Label lblPersonRole = new Label("Personens rolle");
        pane.add(lblPersonRole,0,3);
        VBox vBox = new VBox();
        pane.add(vBox,1,3);
        String[] personRoleStrings = {"TEACHER", "STUDENT"};
        for (int i = 0; i < personRoleStrings.length; i++) {
            RadioButton rb = new RadioButton();
            vBox.getChildren().add(rb);
            rb.setText(personRoleStrings[i]);
            rb.setToggleGroup(group);
        }

        Label lblFiltrePåRolle = new Label("Filtre På Rolle");
        pane.add(lblFiltrePåRolle,1,0);
        comboBox.getItems().add(Role.STUDENT);
        comboBox.getItems().add(Role.TEACHER);
        pane.add(comboBox,1,1);

        Button btnAddPerson = new Button("Tilføj Person");
        pane.add(btnAddPerson,0,4);
        btnAddPerson.setOnAction(event -> this.addPersonAction());

        Button btnFiltrePerson = new Button("Filtre Personer");
        pane.add(btnFiltrePerson,2,1);
        btnFiltrePerson.setOnAction(event -> this.filtrePerson());

        lvwPersonFiltreret = new ListView<>();
        pane.add(lvwPersonFiltreret,3,1);
    }
    public void addPersonAction(){
        RadioButton rb = (RadioButton) group.getSelectedToggle();
        lvwPerson.getItems().add(controller.createPerson(txfPersonName.getText(), Role.valueOf(rb.getText().toUpperCase())));
        lvwPerson.getItems().setAll(controller.getPeople());
    }
    public void filtrePerson(){
        ArrayList<Person> filterPeople = new ArrayList<>();
        filterPeople = controller.filter(comboBox.getSelectionModel().getSelectedItem());
        for (Person filterPerson : filterPeople) {
            lvwPersonFiltreret.getItems().add(filterPerson);
        }
    }
}
