/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.thorben.remotelogviewer.utils;

import dev.thorben.remotelogviewer.core.ErrorHandler;
import java.io.File;
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
 * @author Thorben Buenger
 */
public class JSONSettingUtility {
    
    public static void createConfigFile() {
        if (hasConfigFile()) return;
        try {
            FileWriter createFile;
            createFile = new FileWriter("src/main/resources/config.json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ssh_file", "/Users/" + System.getProperty("user.name") + "/.ssh/known_hosts"); 
            jsonObject.put("download_folder", System.getProperty("user.home") + "/Downloads/");
            
            createFile.write(jsonObject.toJSONString());
            createFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }
    
    public static void setSSHFilePath(String newPath) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ssh_file", newPath);
        jsonObject.put("download_folder", getDownloadFolderPath());
        
        try {
            FileWriter writer = new FileWriter("src/main/resources/config.json");
            writer.write(jsonObject.toJSONString());
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }
    
    public static void setDownloadFolderPath(String newPath) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ssh_file", getSSHFilePath());
        jsonObject.put("download_folder", newPath);
        
        try {
            FileWriter writer = new FileWriter("src/main/resources/config.json");
            writer.write(jsonObject.toJSONString());
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }
     
    public static String getSSHFilePath() {
       JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/config.json"));
            return (String) ((JSONObject) obj).get("ssh_file");
        } catch(IOException | ParseException ex) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
        return "/Users/" + System.getProperty("user.name") + "/.ssh/known_hosts";
    }
     
    public static String getDownloadFolderPath() {
         JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/config.json"));
            return (String) ((JSONObject) obj).get("download_folder");
        } catch(IOException | ParseException ex) {
            Logger.getLogger(JSONCredentialUtility.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
        return System.getProperty("user.home") + "/Downloads/";
    }
    
    private static boolean hasConfigFile() {
        File file = new File("src/main/resources/onfig.json");
        return file.exists();
    }
}
