/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.thorben.remotelogviewer.monitors;

import com.jcraft.jsch.SftpProgressMonitor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author thorbenbuenger
 */
public class SFTPProgressBarMonitor implements SftpProgressMonitor {

    JProgressBar progressBar;
    double totalBytes = 0;
    double downloadedBytes = 0;

    public SFTPProgressBarMonitor(JProgressBar progressBar) {
        this.progressBar = progressBar;
        progressBar.setIndeterminate(true);
        progressBar.setValue(100);
    }

    @Override
    public void init(int i, String string, String string1, long l) {
        totalBytes = l;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public boolean count(long bytes) {        
        return (true);
    }

    @Override
    public void end() {

    }

}
