// Message.java
package dev.ramindu.nexamarket.message.models;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int fromUserId;
    private int toUserId;
    private String messageText;
    private Timestamp sentTime;
    
    // Constructor, getters, and setters
    public Message(int id, int fromUserId, int toUserId, String messageText, Timestamp sentTime) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.messageText = messageText;
        this.sentTime = sentTime;
    }
    
    // Getters
    public int getId() { return id; }
    public int getFromUserId() { return fromUserId; }
    public int getToUserId() { return toUserId; }
    public String getMessageText() { return messageText; }
    public Timestamp getSentTime() { return sentTime; }
}