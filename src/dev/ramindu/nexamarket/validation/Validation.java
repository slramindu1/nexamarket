/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package dev.ramindu.nexamarket.validation;

/**
 *
 * @author Ramindu
 */
public enum Validation {
    USERNAME() {
        @Override
        public String validate() {
            return "^[a-zA-Z][a-zA-Z0-9._]{2,15}$";
        }

    },
    EMAIL() {
        @Override
        public String validate() {
            return "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        }
    },
    PASSOWRD() {
        @Override
        public String validate() {
            return "^[a-zA-Z]{3,}$";
        }
    };
 

    public String validate() {
        return "";
    }
}
