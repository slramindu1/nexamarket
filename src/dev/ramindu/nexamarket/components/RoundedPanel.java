/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;

/**
 *
 * @author Ramindu
 */
public class RoundedPanel extends JPanel{
    
    
    public RoundedPanel(){
        init();
    }
    private void init(){
        putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }
    
}
