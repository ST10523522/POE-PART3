/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

/**
 *
 * @author Linda Baloyi
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Message {

    static Iterable<Message> getSentMessages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static boolean getLongestMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static boolean searchByMessageID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static boolean searchByRecipient(String rec) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static boolean deleteByHash(String hash) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
private String messageID;
private int messageNumber;
private String recipient;
private String messageText;
private static int totalMessagesSent = 0;
private static ArrayList<Message> messages = new ArrayList<>();
private static final String JSON_FILE = "messages.json";

public Message(String recipient, String messageText, int messageNumber) {
this.messageID = generateMessageID();
this.recipient = recipient;
this.messageText = messageText;
this.messageNumber = messageNumber;
totalMessagesSent++;
}

// Empty constructor for Gson
public Message() {}

private String generateMessageID() {
Random rand = new Random();
StringBuilder sb = new StringBuilder();
for(int i = 0; i < 10; i++) {
sb.append(rand.nextInt(10));
}
return sb.toString();
}

public boolean checkMessageID() {
return messageID.length() == 10;
}

public String checkRecipientCell() {
if(recipient!= null && recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 12)
return "Cell phone number successfully captured";
return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
}

public boolean checkMessageLength() {
return messageText!= null && messageText.length() <= 250;
}

public String createMessageHash() {
if(messageText == null || messageText.trim().isEmpty()) return "";
String[] words = messageText.trim().split(" ");
String firstWord = words[0];
String lastWord = words[words.length - 1];
return messageID.substring(0,2).toUpperCase() + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
}

    /**
     *
     * @param input
     * @return
     */
    public String sentMessage(Scanner input) {
System.out.println("\n1. Send Message");
System.out.println("2. Store Message to send later");
System.out.println("3. Disregard Message");
System.out.print("Choose option: ");
int choice = input.nextInt();
input.nextLine();


switch(choice) {
case 1:
messages.add(this);
storeMessage();
return "Message successfully sent";
case 2:
messages.add(this);
storeMessage();
return "Message successfully stored";
case 3:
return "Press 0 to delete the message";
default:
return "Invalid choice";
}
}

// JSON save
public static void storeMessage() {
Gson gson = new Gson();
try (Writer writer = new FileWriter(JSON_FILE)) {
gson.toJson(messages, writer);
} catch (IOException e) {
System.out.println("Error saving messages: " + e.getMessage());
}
}

// JSON load
public static ArrayList<Message> loadMessages() {
Gson gson = new Gson();
try (Reader reader = new FileReader(JSON_FILE)) {
Type listType = new TypeToken<ArrayList<Message>>(){}.getType();
messages = gson.fromJson(reader, listType);
if(messages == null) messages = new ArrayList<>();
} catch (IOException e) {
messages = new ArrayList<>();
}
return messages;
}

public static String printMessages() {
loadMessages();
if(messages.isEmpty()) return "No messages found";

StringBuilder sb = new StringBuilder();
for(Message m : messages) {
sb.append("Message ID: ").append(m.messageID)
.append("\nMessage Hash: ").append(m.createMessageHash())
.append("\nRecipient: ").append(m.recipient)
.append("\nMessage: ").append(m.messageText)
.append("\n---\n");
}
return sb.toString();
}

public static int returnTotalMessages() {
loadMessages();
return messages.size();
}

// Getters for testing + display
public String getMessageID() { return messageID; }
public String getRecipient() { return recipient; }
public String getMessageText() { return messageText; }
public int getMessageNumber() { return messageNumber; }

    private void setMessageContent(String messageText) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
