/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

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
    

    public Message(String senderId, String recipientId, String messageSubject, String messageBody) {
        
        this.messageId = senderId;
        this.sentDateTime = LocalDateTime.now();
        this.sender = senderId;
        this.recipient = recipientId;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;

    }
    
    public Message(String senderId, String recipientId, String messageSubject, String messageBody, ArrayList<Message> mList) {
        
        this.messageId = createResourceRequestMessageId(senderId, mList);
        this.sentDateTime = LocalDateTime.now();
        this.sender = senderId;
        this.recipient = recipientId;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;

    }
    
    public Message(String senderId, String recipientId, String messageSubject, String messageBody, int itemID) {
        
        this.messageId = senderId + ":" + itemID;
        this.sentDateTime = LocalDateTime.now();
        this.sender = senderId;
        this.recipient = recipientId;
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

    private String createResourceRequestMessageId(String senderId, ArrayList<Message> mList)
    {
        String id = "";
        String id2ndHalf = "";
        
        boolean isNew = false;
        
        int rnd = new Random().nextInt(100);
        
        if (mList.isEmpty() == false) {
                   
        
            do {
            
            
                for (Message m : mList)
                {
                
                
                    String[] messageId = m.messageId.split(":");
                    String messageId2ndHalf = messageId[1];
                
                    messageId2ndHalf = messageId2ndHalf.replace("R", "");
                
                    if (rnd != Integer.parseInt(messageId2ndHalf)) 
                    {                      
                        isNew = true;
                    }
                
                }
            
            } while (isNew == false);
        }
        
        id2ndHalf = "R" + rnd;
        
        id = senderId + ":" + id2ndHalf;
        
        
        return id;
    }
    
    
}
