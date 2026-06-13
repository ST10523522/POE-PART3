/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package login;

import com.mycompany.login.Login;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Linda Baloyi
 */
public class LoginTest {
    
private Login login;

@Before
public void setUp() {
login = new Login();
}

// assertTrue tests - should return True
@Test
public void testUsernameCorrect() {
assertTrue(login.checkUserName("kyl_1"));
}

@Test
public void testPasswordCorrect() {
assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
}

@Test
public void testCellCorrect() {
assertTrue(login.checkCellPhoneNumber("+27838968976"));
}

// assertFalse tests - should return False
 @Test
public void testUsernameWrong() {
assertFalse(login.checkUserName("kyle!!!!!!!"));
}

@Test
public void testPasswordWrong() {
assertFalse(login.checkPasswordComplexity("password"));
}

@Test
public void testCellWrong() {
assertFalse(login.checkCellPhoneNumber("08966553"));
}

// assertEquals tests - exact messages from your tables
@Test
public void testUsernameErrorMsg() {
String result = login.registerUser("kyle!!!!!!!", "Ch@@se123", "+27831234567", "Kyle", "Smith");
assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
}

@Test
public void testLoginSuccessMsg() {
login.registerUser("kyl_1", "Ch@@se123", "+27831234567", "Kyle", "Smith");
String result = login.returnLoginStatus("kyl_1", "Ch@@se123");
assertEquals("Welcome Kyle Smith, it is great to see you again.", result);
}
}
