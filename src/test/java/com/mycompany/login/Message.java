/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.login;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Message {
private String messageID;
private int messageNumber;
private String recipient;
private String messageText;
private static int totalMessagesSent = 0;
private static ArrayList<Message> messages = new ArrayList<>();
private static ArrayList<String> disregardedMessages = new ArrayList<>();
private static ArrayList<String> messageHashes = new ArrayList<>();
private static ArrayList<String> messageIDs = new ArrayList<>();
private static final String JSON_FILE = "messages.json";

public Message(String recipient, String messageText, int messageNumber) {
this.messageID = generateMessageID();
this.recipient = recipient;
this.messageText = messageText;
this.messageNumber = messageNumber;
totalMessagesSent++;
}

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
return messageID!= null && messageID.length() == 10;
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
String firstWord = words[0].toUpperCase();
String lastWord = words[words.length - 1].toUpperCase();
return messageID.substring(0,2).toUpperCase() + ":" + messageNumber + ":" + firstWord + lastWord;
}

public String sentMessage(Scanner input) {
System.out.println("\n1. Send Message");
System.out.println("2. Store Message to send later");
System.out.println("3. Disregard Message");
System.out.print("Choose option: ");

int choice = input.nextInt();
input.nextLine();

String hash = createMessageHash();

switch(choice) {
     case 1: // Sent
messages.add(this);
messageHashes.add(hash);
messageIDs.add(messageID);
storeMessage();
return "Message successfully sent";
     case 2: // Stored
messages.add(this);
messageHashes.add(hash);
messageIDs.add(messageID);
storeMessage();
return "Message stored successfully.";

     case 3: // Disregarded
disregardedMessages.add(messageText);
return "Press 0 to delete the message";
default:
return "Invalid choice";
}
}

public static void storeMessage() {
Gson gson = new Gson();
try (Writer writer = new FileWriter(JSON_FILE)) {
gson.toJson(messages, writer);
} catch (IOException e) {
System.out.println("Error saving messages: " + e.getMessage());
}
}

public static ArrayList<Message> loadMessages() {
Gson gson = new Gson();
try (Reader reader = new FileReader(JSON_FILE)) {
Type listType = new TypeToken<ArrayList<Message>>(){}.getType();
messages = gson.fromJson(reader, listType);
if(messages == null) messages = new ArrayList<>();
} catch (IOException e) {
messages = new ArrayList<>();
}

// Repopulate arrays from loaded messages
messageHashes.clear();
messageIDs.clear();
for(Message m : messages) {
messageHashes.add(m.createMessageHash());
messageIDs.add(m.getMessageID());
}
return messages;
}

// Part 3: Array methods
public static ArrayList<Message> getSentMessages() {
return messages;
}

public static ArrayList<String> getDisregardedMessages() {
return disregardedMessages;
}

public static ArrayList<String> getMessageHashes() {
return messageHashes;
}

public static ArrayList<String> getMessageIDs() {
return messageIDs;
}

public static String getLongestMessage() {
loadMessages();
if(messages.isEmpty()) return "No messages stored";
Message longest = messages.get(0);
for(Message m : messages) {
if(m.messageText.length() > longest.messageText.length()) {
longest = m;
}
}
return "Longest message: " + longest.messageText + "\nRecipient: " + longest.recipient;
}

public static String searchByMessageID(String id) {
loadMessages();
for(Message m : messages) {
if(m.messageID.equals(id)) {
return "Recipient: " + m.recipient + "\nMessage: " + m.messageText;
}
}
return "Message ID not found";
}

public static String searchByRecipient(String recipient) {
loadMessages();
StringBuilder sb = new StringBuilder();
for(Message m : messages) {
if(m.recipient.equals(recipient)) {
sb.append("ID: ").append(m.messageID)
.append(" | Message: ").append(m.messageText).append("\n");
}
}
return sb.length() == 0? "No messages for this recipient" : sb.toString();
}

public static String deleteByHash(String hash) {
loadMessages();
for(int i = 0; i < messages.size(); i++) {
if(messages.get(i).createMessageHash().equals(hash)) {
messages.remove(i);
messageHashes.remove(i);
messageIDs.remove(i);
storeMessage();
return "Message deleted successfully";
}
}
return "Hash not found";
}

public static String printMessages() {
loadMessages();
if(messages.isEmpty()) return "Coming Soon.";

StringBuilder sb = new StringBuilder();
for(Message m : messages) {
sb.append("Message ID: ").append(m.messageID).append("\n");
sb.append("Message Hash: ").append(m.createMessageHash()).append("\n");
sb.append("Recipient: ").append(m.recipient).append("\n");
sb.append("Message: ").append(m.messageText).append("\n");
sb.append("---\n");
}
return sb.toString();
}

public static int returnTotalMessages() {
loadMessages();
return messages.size();
}

// Getters
public String getMessageID() { return messageID; }
public String getRecipient() { return recipient; }
public String getMessageText() { return messageText; }
public int getMessageNumber() { return messageNumber; }
}