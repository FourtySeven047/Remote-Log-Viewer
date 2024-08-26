/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package dev.thorben.remotelogviewer;

import dev.thorben.remotelogviewer.core.SFTPConnection;
import dev.thorben.remotelogviewer.frames.MainFrame;

/**
 *
 * @author thorbenbuenger
 */
public class RemoteLogViewer {

    private static SFTPConnection logConnection = null;
    private static SFTPConnection fileConnection = null;
    
    public static void main(String[] args) { 
        new MainFrame().setVisible(true);
        System.out.println("Hello World!");
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
