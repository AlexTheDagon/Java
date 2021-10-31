package queueSimulator.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import queueSimulator.model.QueueManager;
import queueSimulator.model.SimulationConstraints;
import queueSimulator.view.GUI;

public class Controller {
    SimulationConstraints myData;
    QueueManager theManager;
    GUI gui;
    Button start;

    public Controller(Stage primaryStage) {
        gui = new GUI(primaryStage);
        start = gui.getStart();
        start.setOnAction(autobotsRollOut -> {
            myData = new SimulationConstraints(
                    Integer.parseInt(gui.getConfigNText().getText()),
                    Integer.parseInt(gui.getConfigMText().getText()),
                    Integer.parseInt(gui.getConfigMaxSimulationText().getText()),
                    Integer.parseInt(gui.getConfigMinArrivalText().getText()),
                    Integer.parseInt(gui.getConfigMaxArrivalText().getText()),
                    Integer.parseInt(gui.getConfigMinServiceText().getText()),
                    Integer.parseInt(gui.getConfigMaxServiceText().getText())
            );

            theManager = new QueueManager(myData, gui);
            theManager.start();
        });
    }
}
