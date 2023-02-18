package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

class mail{
    Date date;
    String sender;
    String message;

    public mail(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store

    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<mail> inbox;
    ArrayList<mail> trash;

    public ArrayList<mail> getInbox() {
        return inbox;
    }

    public ArrayList<mail> getTrash() {
        return trash;
    }

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new ArrayList<mail>();
        this.trash = new ArrayList<mail>();

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        mail newMail = new mail(date,sender,message);

        if(inbox.size() == inboxCapacity){
            mail oldest = inbox.get(0);
            inbox.remove(0);
            trash.add(oldest);
            inbox.add(newMail);
        }
        else {
            inbox.add(newMail);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(mail m: inbox){
            if(m.getMessage().equals(message)){
                trash.add(m);
                inbox.remove(m);
                break;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()){
            return null;
        }
        else {
            mail msg = inbox.get(inbox.size()-1);
            String message = msg.getMessage();
            return message;
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty()){
            return null;
        }
        else {
            mail msg1 = inbox.get(0);
            String message = msg1.getMessage();
            return message;
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        int numOfEmails = 0;
        //It is guaranteed that start date <= end date
        Iterator<mail> it = inbox.iterator();
        while(it.hasNext()){

            mail objectMail = it.next();
            if(objectMail.date.getTime() >= start.getTime() && objectMail.date.getTime() <= end.getTime()){
                numOfEmails++;
            }
        }
        return numOfEmails;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}