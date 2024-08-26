/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.thorben.remotelogviewer.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author thorbenbuenger
 */
public class JSONCredentialUtility {
    
    public static void saveLogCredentials(String hostname, int port, String username, String password, String remotePath) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hostname", hostname);
        jsonObject.put("port", port);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("remotepath", remotePath);
        
        try {
            FileWriter writer = new FileWriter("src/main/resources/log_credentials.json");
            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public static void saveFileCredentials(String hostname, int port, String username, String password, String localPath, String remotePath) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hostname", hostname);
        jsonObject.put("port", port);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("localpath", localPath);
        jsonObject.put("remotepath", remotePath);
        
        try {
            FileWriter writer = new FileWriter("src/main/resources/file_credentials.json");
            writer.write(jsonObject.toJSONString());
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static JSONObject getLogJsonObject() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/log_credentials.json"));
            return (JSONObject) obj;
          
        } catch(IOException | ParseException e) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    private static JSONObject getFileJsonObject() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/file_credentials.json"));
            return (JSONObject) obj;
          
        } catch(IOException | ParseException e) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public static String getLogHostName() {
        if (getLogJsonObject() == null) return "127.0.0.1";
        return (String) getLogJsonObject().get("hostname");
    }
    
    public static int getLogPort() {
        if (getLogJsonObject() == null) return 22;
        return Math.toIntExact((long) getLogJsonObject().get("port"));
    }
    
    public static String getLogUserName() {
        if (getLogJsonObject() == null) return "admin";
        return (String) getLogJsonObject().get("username");
    }
    
    public static String getLogPassword() {
        if (getLogJsonObject() == null) return "";
        return (String) getLogJsonObject().get("password");
    }
    
    public static String getLogRemotePath() {
        if (getLogJsonObject() == null) return "";
        return (String) getLogJsonObject().get("remotepath");
    }
    
    /*  -------------------------
        Section change
        -------------------------
    */
    
    public static String getFileHostName() {
        if (getFileJsonObject() == null) return "127.0.0.1";
        return (String) getFileJsonObject().get("hostname");
    }
    
    public static int getFilePort() {
        if (getFileJsonObject() == null) return 22;
        return Math.toIntExact((long) getFileJsonObject().get("port"));
    }
    
    public static String getFileUserName() {
        if (getFileJsonObject() == null) return "admin";
        return (String) getFileJsonObject().get("username");
    }
    
    public static String getFilePassword() {
        if (getFileJsonObject() == null) return "";
        return (String) getFileJsonObject().get("password");
    }
    
    public static String getFileLocalPath() {
        if (getFileJsonObject() == null) return "";
        return (String) getFileJsonObject().get("localpath");
    }
    
    public static String getFileRemotePath() {
        if (getFileJsonObject() == null) return "";
        return (String) getFileJsonObject().get("remotepath");
    }
}
