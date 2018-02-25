/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mth_rssreader;

/**
 *
 * @author Boutet
 */
public class User {
    String user_id;
    
    public String getUserID(){
        return(user_id);
    }
    
    public void setUserID(String new_str){
        user_id = new_str;
    }
}
