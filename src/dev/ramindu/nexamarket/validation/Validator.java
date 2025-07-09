/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.validation;

import javax.swing.JOptionPane;

/**
 *
 * @author Ramindu
 */
public class Validator {

    public static boolean isEmailValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null, "Email Input Cannot Be Empty", "Email Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!value.matches(Validation.EMAIL.validate())) {
             JOptionPane.showMessageDialog(null, "Enter Valid Email Address ", "Email Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        return true;
    }

   

    public static boolean isPasswordValid(String value) {
        if(value.isBlank()){
            JOptionPane.showMessageDialog(null, "Password Field Can't Be Empty", "Passowrd Validation", JOptionPane.WARNING_MESSAGE);
        }else if(value.matches(Validation.PASSOWRD.validate())){
            JOptionPane.showMessageDialog(null, "Passsword must include the following characters. \n" 
                    + "At Least one lowercase \n" 
                    + "At Least one Upercase \n" 
                    + "At Least one Special Character \n" 
                    + "At Least one Digit    \n" 
                    + "The Password Must Be Greater than 3 and less than 8 Characters" ,
                    "Email Validation", 
                    JOptionPane.WARNING_MESSAGE);
            
            return false;
        }
        return true;
    }

    public static boolean isUserNameValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null, "UserName Input Cannot Be Empty", "UserName Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!value.matches(Validation.USERNAME.validate())) {
            JOptionPane.showMessageDialog(null, "Enter Valid UserName", "UserName Validation", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
