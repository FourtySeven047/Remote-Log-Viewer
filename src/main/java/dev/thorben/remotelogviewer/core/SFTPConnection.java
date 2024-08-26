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
import dev.thorben.remotelogviewer.monitors.SFTPProgressBarMonitor;
import java.io.IOException;


/**
 *
 * @author thorbenbuenger
 */
public class SFTPConnection {

    private String remoteHost = "127.0.0.1";
    private int remotePort = 22;
    private String username = "user";
    private String password = "";

    //private String remoteFile = "latest.log";
    private final String localDirectory = "src/main/resources/";

    //private SSHClient sshClient;
    //private SFTPClient sftpClient;
    private Session session;
    private ChannelSftp sftp;
         

    public SFTPConnection(String remoteHost, int remotePort, String username, String password) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.username = username;
        this.password = password;
    }

    public void connect() throws IOException, JSchException {
        /*
        System.out.println("----- remoteHost: " + remoteHost + ", remotePort: " + remotePort + ", username: " + username + ", password: " + password);
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect(remoteHost, remotePort);
        client.useCompression();
        client.authPassword(username, password);
        sshClient = client;
        sftpClient = sshClient.newSFTPClient();
        */
        JSch jsch = new JSch();
        jsch.setKnownHosts("/Users/thorbenbuenger/.ssh/known_hosts");
        session = jsch.getSession(username, remoteHost, remotePort);
        session.setPassword(password);
        session.connect();
        Channel sftp = session.openChannel("sftp");
        sftp.connect();
        this.sftp = (ChannelSftp) sftp;
    }

    public void disconnect() throws IOException {
        session.disconnect();
        sftp.disconnect();
    }

    public void download(String localPath, String remoteFile, SFTPProgressBarMonitor monitor) throws IOException, JSchException, SftpException {
        sftp.get(remoteFile, localDirectory + "latest.log", monitor);
        System.out.println("Werde ich schon davor ausgeführt?");
        //sftpClient.get(remoteFile, localDirectory + "latest.log");
    }
    
    public void download(String remoteFile) throws IOException, JSchException, SftpException {
        sftp.get(remoteFile, localDirectory + "latest.log");
        System.out.println("Werde ich schon davor ausgeführt?");
        //sftpClient.get(remoteFile, localDirectory + "latest.log");
    }

    public void download(String localPath, String remoteFile) throws IOException, SftpException, JSchException {
        sftp.get(remoteFile, localPath);
        System.out.println("Werde ich schon davor ausgeführt?");
    }

    public void upload(String localFile, String remotePath) throws SftpException {
        sftp.put(localFile, remotePath);
    }

}
