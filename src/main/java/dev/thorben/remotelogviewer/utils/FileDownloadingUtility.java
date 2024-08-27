/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.thorben.remotelogviewer.utils;

import dev.thorben.remotelogviewer.RemoteLogViewer;
import dev.thorben.remotelogviewer.core.ErrorHandler;
import dev.thorben.remotelogviewer.frames.MainFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Thorben Buenger
 */
public class FileDownloadingUtility {
    
    public static void download(JTextArea logArea, String remotePath) {
        try {
            RemoteLogViewer.getLogConnection().download(remotePath);
            logArea.setText("");
            Scanner scanner = new Scanner(new File("src/main/resources/latest.log"));
            int count = 1;
            while (scanner.hasNextLine()) {
                logArea.append(count + ". " + scanner.nextLine() + "\n");
                count++;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }
    
    public static void saveToDownloads() {
        String home = System.getProperty("user.home");
        File file = new File(home+"/Downloads/" + "latest.log"); 
        int index = 1;
        while (file.exists()) {
            file = new File(home+"/Downloads/" + "latest.log" + index);
        }
        try {
            FileWriter createFile;
            createFile = new FileWriter(file.getAbsolutePath(), true);
            Scanner scanner = new Scanner(new File("src/main/resources/latest.log"));
            while (scanner.hasNextLine()) {
                createFile.append(scanner.nextLine() + "\n");
            }
            createFile.close();
            scanner.close();
        } catch(IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }
}
