/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.thorben.remotelogviewer.core;

import dev.thorben.remotelogviewer.frames.ErrorFrame;

/**
 *
 * @author Thorben Buenger
 */
public class ErrorHandler {
    
    public static void handle(Exception e, String message) {
        ErrorFrame frame = new ErrorFrame();
        frame.setErrorMessage(message);
        appendLog(e);
    }
    
    public static void handleFatal(Exception e) {
        handle(e, "Fatal Error: " + e.getMessage());
    }
    
    private static void appendLog(Exception e) {
        
    }
}
