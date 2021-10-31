package queueSimulator.view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI {
    BorderPane mainRoot;
    VBox configBox;

    HBox configN;
    TextField configNText;
    Label configNLabel;

    HBox configM;
    TextField configMText;
    Label configMLabel;

    HBox configMaxSimulation;
    TextField configMaxSimulationText;
    Label configMaxSimulationLabel;

    HBox configMinArrival;
    TextField configMinArrivalText;
    Label configMinArrivalLabel;

    HBox configMaxArrival;
    TextField configMaxArrivalText;
    Label configMaxArrivalLabel;

    HBox configMinService;
    TextField configMinServiceText;
    Label configMinServiceLabel;

    HBox configMaxService;
    TextField configMaxServiceText;
    Label configMaxServiceLabel;

    TextArea myShop;

    HBox startBox;
    Button start;

    public TextField getConfigNText() { return configNText; }

    public TextField getConfigMText() { return configMText; }

    public TextField getConfigMaxSimulationText() { return configMaxSimulationText; }

    public TextField getConfigMinArrivalText() { return configMinArrivalText; }

    public TextField getConfigMaxArrivalText() { return configMaxArrivalText; }

    public TextField getConfigMinServiceText() { return configMinServiceText; }

    public TextField getConfigMaxServiceText() {
        return configMaxServiceText;
    }

    public TextArea getMyShop() { return myShop; }

    public Button getStart() { return start; }

    public GUI(Stage primaryStage) {
        mainRoot = new BorderPane();

        configBox  = new VBox(20);
        configN = new HBox();
        configNText = new TextField();
        configNLabel = new Label("N: ");
        configNText.setPrefWidth(80);
        configN.getChildren().add(configNLabel);
        configN.getChildren().add(configNText);
        configBox.getChildren().add(configN);
        configN.setAlignment(Pos.CENTER_RIGHT);

        configM = new HBox();
        configMText = new TextField();
        configMLabel = new Label("M: ");
        configMText.setPrefWidth(80);
        configM.getChildren().add(configMLabel);
        configM.getChildren().add(configMText);
        configBox.getChildren().add(configM);
        configM.setAlignment(Pos.CENTER_RIGHT);

        configMaxSimulation = new HBox();
        configMaxSimulationText = new TextField();
        configMaxSimulationLabel = new Label("Max Simulation: ");
        configMaxSimulationText.setPrefWidth(80);
        configMaxSimulation.getChildren().add(configMaxSimulationLabel);
        configMaxSimulation.getChildren().add(configMaxSimulationText);
        configBox.getChildren().add(configMaxSimulation);
        configMaxSimulation.setAlignment(Pos.CENTER_RIGHT);

        configMinArrival = new HBox();
        configMinArrivalText = new TextField();
        configMinArrivalLabel = new Label("Min Arrival: ");
        configMinArrivalText.setPrefWidth(80);
        configMinArrival.getChildren().add(configMinArrivalLabel);
        configMinArrival.getChildren().add(configMinArrivalText);
        configBox.getChildren().add(configMinArrival);
        configMinArrival.setAlignment(Pos.CENTER_RIGHT);

        configMaxArrival = new HBox();
        configMaxArrivalText = new TextField();
        configMaxArrivalLabel = new Label("Max Arrival: ");
        configMaxArrivalText.setPrefWidth(80);
        configMaxArrival.getChildren().add(configMaxArrivalLabel);
        configMaxArrival.getChildren().add(configMaxArrivalText);
        configBox.getChildren().add(configMaxArrival);
        configMaxArrival.setAlignment(Pos.CENTER_RIGHT);

        configMinService = new HBox();
        configMinServiceText = new TextField();
        configMinServiceLabel = new Label("Min Service: ");
        configMinServiceText.setPrefWidth(80);
        configMinService.getChildren().add(configMinServiceLabel);
        configMinService.getChildren().add(configMinServiceText);
        configBox.getChildren().add(configMinService);
        configMinService.setAlignment(Pos.CENTER_RIGHT);

        configMaxService = new HBox();
        configMaxServiceText = new TextField();
        configMaxServiceLabel = new Label("Max Service: ");
        configMaxServiceText.setPrefWidth(80);
        configMaxService.getChildren().add(configMaxServiceLabel);
        configMaxService.getChildren().add(configMaxServiceText);
        configBox.getChildren().add(configMaxService);
        configMaxService.setAlignment(Pos.CENTER_RIGHT);

        startBox = new HBox();
        start = new Button("Start");
        startBox.getChildren().add(start);
        configBox.getChildren().add(startBox);
        startBox.setAlignment(Pos.CENTER);

        myShop = new TextArea("Please insert the simulation constraints in the left\n" +
                "and then press Start for the queue simulation to\n" +
                "begin its processing.\n");
        mainRoot.setCenter(myShop);
        mainRoot.setLeft(configBox);

        primaryStage.setScene(new Scene(mainRoot, 800, 500));
        primaryStage.show();
    }



}
