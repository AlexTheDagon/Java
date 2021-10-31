package orderManagement;

import javafx.application.Application;
import javafx.stage.Stage;
import orderManagement.bll.ClientBLL;
import orderManagement.bll.OrderBLL;
import orderManagement.bll.ProductBLL;
import orderManagement.dao.AbstractDAO;
import orderManagement.model.*;
import orderManagement.presentation.GUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * The type Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException{

        GUI gui = new GUI(primaryStage);
    }


    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){ launch(args);
    }
}
