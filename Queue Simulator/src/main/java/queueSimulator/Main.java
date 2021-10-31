// https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html
// https://www.tutorialspoint.com/java/util/timer_schedule_period.htm
// https://www.javacodegeeks.com/2013/01/java-thread-pool-example-using-executors-and-threadpoolexecutor.html
// https://www.youtube.com/watch?v=J09TLPgwd0Y
// https://www.youtube.com/watch?v=mTGdtC9f4EU
// https://www.youtube.com/watch?v=TCd8QIS-2KI&t=546s
package queueSimulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import queueSimulator.controller.Controller;
import queueSimulator.model.*;
import queueSimulator.utility.*;
import queueSimulator.view.GUI;

import javax.swing.text.TableView;

import java.util.concurrent.atomic.AtomicInteger;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}