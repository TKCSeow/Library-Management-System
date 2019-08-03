/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author tseow
 */
public class Message implements Serializable{
    
    private String messageId;
    private LocalDateTime sentDateTime;
    private String sender;
    private String recipient;
    private String messageSubject;
    private String messageBody;
    

    public Message(String senderId, String recipient, String messageSubject, String messageBody) {
        
        messageId = senderId;
        this.sentDateTime = LocalDateTime.now();
        this.sender = sender;
        this.recipient = recipient;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;

    }
    
    public Message(String senderId, String recipient, String messageSubject, String messageBody, int itemID) {
        
        messageId = senderId + ":" + itemID;
        this.sentDateTime = LocalDateTime.now();
        this.sender = sender;
        this.recipient = recipient;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    
    
    
}
