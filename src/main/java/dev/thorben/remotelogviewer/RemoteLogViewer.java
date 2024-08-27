/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package dev.thorben.remotelogviewer;

import dev.thorben.remotelogviewer.core.ErrorHandler;
import dev.thorben.remotelogviewer.core.SFTPConnection;
import dev.thorben.remotelogviewer.frames.MainFrame;
import dev.thorben.remotelogviewer.utils.JSONSettingUtility;

/**
 *
 * @author thorbenbuenger
 */
public class RemoteLogViewer {

    private static SFTPConnection logConnection = null;
    private static SFTPConnection fileConnection = null;
    
    public static void main(String[] args) { 
        ErrorHandler.createLog();
        JSONSettingUtility.createConfigFile();
        new MainFrame().setVisible(true);
    }
    
    public static void setLogConnection(SFTPConnection sftpConnection) {
        logConnection = sftpConnection;
    } 
    
    public static SFTPConnection getLogConnection() {
        return logConnection;
    }
    
    public static void setFileConnection(SFTPConnection sftpConnection) {
        fileConnection = sftpConnection;
    } 
    
    public static SFTPConnection getFileConnection() {
        return fileConnection;
    }
}
