// User.java
package dev.ramindu.nexamarket.message.models;

public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    
    public User(int id, String username, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
}