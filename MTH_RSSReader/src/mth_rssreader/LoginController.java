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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Boutet
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField su_mail, su_id, su_pwd, si_mail, si_pwd;
    private String mail, id, pwd;
    Parent root;
    Scene scene;
    private Stage mainstage;
    
    @FXML
    public void Register(ActionEvent event){
        mail = su_mail.getText();
        pwd = su_pwd.getText();
        id = su_id.getText();
        //pseudo, mail, password à ajouter à bdd
        //if (register(id, password, mail) == true)
        label.setText("Registration Successful!");
        Go_to_signin(event);
        //else
        //label.setText("Incorrect informations, please try again");
    }
    
    @FXML
    public void Authenticate(ActionEvent event){
        //envoyer mail et pwd, 
        mail = si_mail.getText();
        pwd = si_pwd.getText();
        /*if (mail == ok && pwd == ok)
            Go_to_feeds();
        else
        {
            label.setText("Sorry, we couldn't identify you. Please register or try again.");
            Go_to_signin();
        */
    }
    
    @FXML
    public void Go_to_signup(ActionEvent event){
        System.out.println("wesh");
           try {
                root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
                scene = new Scene(root);
                mainstage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
                mainstage.setScene(scene);
                mainstage.show();
                //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @FXML
    public void Go_to_signin(ActionEvent event){
        System.out.println("bien ?");
        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            scene = new Scene(root);
            mainstage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            mainstage.setScene(scene);
            mainstage.show();
            //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@Override
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
