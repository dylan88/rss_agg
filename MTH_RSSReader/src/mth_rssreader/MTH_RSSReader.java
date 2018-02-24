/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mth_rssreader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Boutet
 */
public class MTH_RSSReader extends Application {
    private Parent root;
    private Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
                root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("RSS Reader FTW !");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
