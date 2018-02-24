/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mth_rssreader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Boutet
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button signin_ok_btn, signup_ok_btn, signin_page_btn, signup_page_btn;
    @FXML
    Parent root;
    
    private void login_main() {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        /*signin_ok_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
        //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
    };
        });
    
        
        signup_ok_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
        //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
    };
        });
    
        signin_page_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
        //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
    };
        });
    
        signup_page_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
                    //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
    };
        });*/
};
    @FXML
    public void Go_to_signup(ActionEvent event){
        System.out.println("wesh");
           try {
                root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
                Scene scene = new Scene(root);
                Window win = getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    public void Go_to_signin(){
        System.out.println("bien ?");
        /*        try {
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));6
        .
        
                //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
            } catch (IOException ex) { 
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
    }
    //@Override
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
