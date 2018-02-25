/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mth_rssreader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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
import java.net.URL;
import java.net.URLConnection; 
import java.net.HttpURLConnection;
/**
 *
 * @author Boutet
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField su_mail, su_id, su_pwd, si_mail, si_pwd, si_domain;
    private String mail, id, pwd, urlParameters, domain, response;
    Parent root;
    Scene scene;
    private Stage mainstage;
    public URL url;
    public HttpURLConnection urlConnection;
    private ByteArrayOutputStream bo;
    
        public int isConnected(){
        try 
          {
            url = new URL("http://google.fr");
 
            URLConnection connection = url.openConnection();
            connection.connect();       
 
            System.out.println("Internet Connected");
            return(0);
            
        }catch (IOException e){
            System.out.println("Sorry, No Internet Connection");
            return (-1);
        }
    }
    
    @FXML
    public void Register(ActionEvent event){
        mail = su_mail.getText();
        pwd = su_pwd.getText();
        id = su_id.getText();
        System.out.println(id + ", " + pwd + " et " + mail);
        
        urlParameters = "/api/register?email="+mail+"&password="+pwd;
        
        try {
            url = new URL(domain+urlParameters);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            urlConnection.setRequestProperty("Accept","/");
            urlConnection.setRequestProperty("accept", "text/html");
            urlConnection.setRequestProperty("charset", "ISO-8859-1");
            urlConnection.connect();
            System.out.println(urlConnection.getResponseCode());
            response = readStream(urlConnection.getInputStream());
            System.out.println(response);             
            label.setText("Registration Successful!");
            Go_to_signin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);
    }
    
    @FXML
    public void Authenticate(ActionEvent event){
        //envoyer mail et pwd
        mail = si_mail.getText();
        pwd = si_pwd.getText();
        domain = si_domain.getText();
        System.out.println(mail + " et " + pwd + " et " + domain);
        
        urlParameters = "/api/login?email="+mail+"&password="+pwd;
        
        try {
            url = new URL(domain+urlParameters);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            urlConnection.setRequestProperty("Accept","/");
            urlConnection.setRequestProperty("accept", "text/html");
            urlConnection.setRequestProperty("charset", "ISO-8859-1");
            urlConnection.connect();
            System.out.println(urlConnection.getResponseCode());
            response = readStream(urlConnection.getInputStream());
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);

        //if (get("/login?email="+mail+"&password="+pwd) != -1 )
           /* try {
                root = FXMLLoader.load(getClass().getResource("Feeds.fxml"));
                scene = new Scene(root);
                mainstage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
                mainstage.setScene(scene);
                mainstage.show();
                //envoyer mail et password, si retour ok ->charger flux RSS, sinon label = "bah non"
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*else
        {
            label.setText("Sorry, we couldn't identify you. Please register or try again.");
            Go_to_signin();
        */
    }
    
    private String readStream(InputStream is) {         
        try {             
            bo = new ByteArrayOutputStream();             
            int i = is.read();             
            while(i != -1) {                 
                bo.write(i);                 
                i = is.read();             
            }             
            return bo.toString();         
        } catch (IOException e) {             
            return "";         
        }     
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
