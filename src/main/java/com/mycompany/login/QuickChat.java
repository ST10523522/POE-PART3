/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

import java.util.Scanner;

/**
 *
 * @author Linda Baloyi
 */
public class QuickChat {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
Login login = new Login();
Message Message = new Message();

System.out.println("=== QUICKCHAT REGISTRATION ===");

// Part 1: Register first
System.out.print("Enter username with underscore, max 5 chars e.g kyl_1: ");
String username = input.nextLine();
System.out.print("Enter password: ");
String password = input.nextLine();
System.out.print("Enter cell +27...: ");
String cell = input.nextLine();
System.out.print("Enter first name: ");
String firstName = input.nextLine();
System.out.print("Enter last name: ");
String lastName = input.nextLine();

String regResult = login.registerUser(username, password, cell, firstName, lastName);
System.out.println(regResult);
if(!regResult.equals("User registered successfully")) return;

// Part 2: Login
System.out.println("\n=== LOGIN ===");
System.out.print("Enter username: ");
String userLogin = input.nextLine();
System.out.print("Enter password: ");
String passLogin = input.nextLine();

if(!login.loginUser(userLogin, passLogin)) {
System.out.println("Login failed. Exiting...");
return;
}
System.out.println(login.returnLoginStatus(userLogin, passLogin));

// Part 2: Messaging menu
System.out.print("\nHow many messages would you like to send? ");
int maxMessages = input.nextInt();
input.nextLine();

int count = 0;
int messagesSent =0;
while(messagesSent < maxMessages) {
    // Change menu to 4 options
System.out.println("\nMenu:");
System.out.println("1. Send Messages");
System.out.println("2. Show recently sent messages");
System.out.println("3. Stored Messages"); // NEW Part 3 option
System.out.println("4. Quit");
System.out.print("Choose option: ");
int choice = input.nextInt();
input.nextLine();

switch(choice) {
case 1:
if(messagesSent >= maxMessages) {
System.out.println("You have reached your message limit of " + maxMessages);
break;
}

System.out.print("Enter recipient cell number with international code e.g +27831234567: ");
String recipient = input.nextLine();

System.out.print("Enter message: ");
String messageText = input.nextLine();

// Check 250 char limit
if(messageText.length() > 250) {
System.out.println("Please enter a message of less than 250 characters.");
break;
}

// Create message object
Message msg = new Message(recipient, messageText, messagesSent);

// Check recipient format
String cellCheck = msg.checkRecipientCell();
System.out.println(cellCheck);

if(cellCheck.startsWith("Cell phone number successfully")) {
// THIS LINE calls sentMessage() which shows 1. Send 2. Store 3. Disregard
String result = msg.sentMessage(input);
System.out.println(result);

// Display details if sent or stored
if(result.contains("sent") || result.contains("stored")) {
System.out.println("\n--- Message Details ---");
System.out.println("Message ID: " + msg.getMessageID());
System.out.println("Message Hash: " + msg.createMessageHash());
System.out.println("Recipient: " + msg.getRecipient());
System.out.println("Message: " + msg.getMessageText());
messagesSent++;
}
}

case 2:
System.out.println(Message.printMessages());
break;

case 3: // Part 3: Stored Messages submenu
boolean storedMenu = true;
while(storedMenu) {
System.out.println("\n--- Stored Messages ---");
System.out.println("a. Display sender and recipient of all stored");
System.out.println("b. Display longest stored message");
System.out.println("c. Search by Message ID");
System.out.println("d. Search by Recipient");
System.out.println("e. Delete by Message Hash");
System.out.println("f. Full report");
System.out.println("g. Back to main menu");
System.out.print("Choose: ");
String subChoice = input.nextLine();

switch(subChoice) {
case "a":
for(Message m : Message.getSentMessages()) {
System.out.println("Sender: You | Recipient: " + m.getRecipient());
}
break;
case "b":
System.out.println(Message.getLongestMessage());
break;
case "c":
System.out.print("Enter Message ID: ");
String id = input.nextLine();
System.out.println(Message.searchByMessageID(id));
break;
case "d":
System.out.print("Enter Recipient +27...: ");
String rec = input.nextLine();
System.out.println(Message.searchByRecipient(rec));
break;
case "e":
System.out.print("Enter Message Hash to delete: ");
String hash = input.nextLine();
System.out.println(Message.deleteByHash(hash));
break;
case "f":
System.out.println(Message.printMessages());
break;
case "g":
storedMenu = false;
break;
default:
System.out.println("Invalid choice");
}
}
break;

case 4:
    boolean running = false;
System.out.println("\nTotal messages: " + Message.returnTotalMessages());
System.out.println("Goodbye!");
break;

}
}

System.out.println("Total messages: " + Message.returnTotalMessages());
input.close();
}
}
