/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.thorben.remotelogviewer.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thorben Buenger
 */
public class ErrorHandler {
    
    public static void handle(Exception e, String message) {
        JOptionPane.showOptionDialog(null, message, "Error", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, null, null);
        appendLog(e);
    }
    
    public static void handleFatal(Exception e) {
        handle(e, "Fatal Error: " + e.getMessage());
    }
    
    private static void appendLog(Exception e) {
        try {
            createLog();
            FileWriter createFile;
            createFile = new FileWriter("src/main/resources/application_log.txt", true);
            createFile.append(LocalTime.now().toString() + "\t" + e.getLocalizedMessage() + "\n");
            createFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createLog() {
        if (hasLog()) return;
        try {
            FileWriter createFile;
            createFile = new FileWriter("src/main/resources/application_log.txt");
            createFile.write("");
            createFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static boolean hasLog() {
        File file = new File("src/main/resources/application_log.txt");
        return file.exists();
    }
}
