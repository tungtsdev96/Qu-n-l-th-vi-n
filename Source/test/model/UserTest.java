/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import user.controller.AccountController;

/**
 *
 * @author tungts
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

      /**
     * Test of checkLogin method, of class User.
     */
    @Test
    public void testCheckLogin() throws Exception {
        System.out.println("checkLogin");
        assertEquals(true, AccountController.getInstance().checkLogin("admin", "1"));
        assertEquals(true, AccountController.getInstance().checkLogin("test1", "1"));
        assertEquals(true, AccountController.getInstance().checkLogin("test2", "1"));
        assertEquals(false, AccountController.getInstance().checkLogin("test", "1123"));
        assertEquals(false, AccountController.getInstance().checkLogin("test", ""));
    }

}
