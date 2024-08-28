/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.thorben.remotelogviewer.core;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thorbenbuenger
 */
public class SFTPConnection {

    private String remoteHost = "127.0.0.1";
    private int remotePort = 22;
    private String username = "user";
    private String password = "";

    private final String localDirectory = "src/main/resources/";

    private Session session;
    private ChannelSftp sftp;

    public SFTPConnection(String remoteHost, int remotePort, String username, String password) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        try {
            JSch jsch = new JSch();
            jsch.setKnownHosts("/Users/" + System.getProperty("user.name") + "/.ssh/known_hosts");
            session = jsch.getSession(username, remoteHost, remotePort);
            session.setPassword(password);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            this.sftp = (ChannelSftp) channel;
        } catch (JSchException ex) {
            Logger.getLogger(SFTPConnection.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }

    public void disconnect() {
        session.disconnect();
        sftp.disconnect();
    }
    
    public void download(String remoteFile)  {
        try {
            sftp.get(remoteFile, localDirectory + "latest.log");
        } catch (SftpException ex) {
            Logger.getLogger(SFTPConnection.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }

    public void download(String localPath, String remoteFile) {
        try {
            sftp.get(remoteFile, localPath);
        } catch (SftpException ex) {
            Logger.getLogger(SFTPConnection.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }

    public void upload(String localFile, String remotePath) {
        try {
            sftp.put(localFile, "./" + remotePath);
        } catch (SftpException ex) {
            Logger.getLogger(SFTPConnection.class.getName()).log(Level.SEVERE, null, ex);
            ErrorHandler.handleFatal(ex);
        }
    }

}
