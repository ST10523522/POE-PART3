/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login;

/**
 *
 * @author Linda Baloyi
 */
public class Login {
private String username;
private String password;
private String cellNumber;
private String firstName;
private String lastName;
private boolean loggedIn = false;

public boolean checkUserName(String username) {
return username.contains("_") && username.length() <= 5;
}

public boolean checkPasswordComplexity(String password) {
return password.length() >= 8 &&
password.matches(".*[A-Z].*") &&
password.matches(".*[a-z].*") &&
password.matches(".*[0-9].*") &&
password.matches(".*[!@#$%^&*()].*");
}

public boolean checkCellPhoneNumber(String cell) {
return cell.startsWith("+") && cell.length() >= 10 && cell.length() <= 12;
}

public String registerUser(String username, String password, String cell, String firstName, String lastName) {
if(!checkUserName(username))
return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
if(!checkPasswordComplexity(password))
return "Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
if(!checkCellPhoneNumber(cell))
return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";

this.username = username;
this.password = password;
this.cellNumber = cell;
this.firstName = firstName;
this.lastName = lastName;
return "User registered successfully";
}

public String returnLoginStatus(String username, String password) {
if(username.equals(this.username) && password.equals(this.password)) {
loggedIn = true;
return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
} else {
return "Username or password incorrect, please try again";
}
}

public boolean loginUser(String username, String password) {
String result = returnLoginStatus(username, password);
return result.startsWith("Welcome");
}

public boolean isLoggedIn() { return loggedIn; }
}

