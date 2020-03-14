/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tungts
 */
public class CoppyOfBookTest {
    
    public CoppyOfBookTest() {
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
     * Test of addCoppyOfBookByIdBook method, of class CoppyOfBook.
     */
    @Test
    public void testAddCoppyOfBookByIdBook() {
        System.out.println("addCoppyOfBookByIdBook");
        String idBook = "VN1000";
        CoppyOfBook instance = new CoppyOfBook();
        instance.setIdCoppyOfBook("VN1000-04");
        instance.setNumberOfCoopy(4);
        instance.setTypeOfCoppy(CoppyOfBook.TypeOfCoppy.REFERENCE);
        instance.setPrice(30000);
        boolean expResult = false;
        boolean result = false;
        try {
            result = instance.addCoppyOfBookByIdBook(idBook);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoppyOfBookTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CoppyOfBookTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

}
