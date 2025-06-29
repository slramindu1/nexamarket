/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.util;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramindu
 */
public class AppIconUtil {
    private static Image appIcon;
    static {
        try{
        URL IconPath = AppIconUtil.class.getResource("/dev/ramindu/nexamarket/img/logo24.png");
        
        
            ImageIcon icon = new ImageIcon(IconPath);
            AppIconUtil.appIcon = icon.getImage();
        
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Couldnt Found The Icon");
        }
    }
    
    public static void Applyicon(JFrame frame){
        if(frame != null){
            frame.setIconImage(appIcon);
        }
    }
}
