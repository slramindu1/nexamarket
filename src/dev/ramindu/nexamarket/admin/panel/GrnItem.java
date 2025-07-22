/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.ramindu.nexamarket.admin.panel;

public class GrnItem {
    private String itemName;
    private int quantity;
    private double unitPrice;
    private double totalAmount;

    // Constructor
    public GrnItem(String itemName, int quantity, double unitPrice, double totalAmount) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
    }

    // Getters
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotalAmount() { return totalAmount; }
}
