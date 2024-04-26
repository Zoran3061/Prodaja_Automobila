/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Zoran
 */
public class ProveriDostupnostTest {
   
    public ProveriDostupnostTest() {
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
     * Test of dostupan method, of class ProveriDostupnost.
     */
    @Test
    public void testDostupan() throws Exception {
        int id = 0;
        ProveriDostupnost instance = new ProveriDostupnost();
        boolean expResult = false;
        boolean result = instance.dostupan(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testDostupan2() throws Exception {
        int id = 1;
        ProveriDostupnost instance = new ProveriDostupnost();
        boolean expResult = false;
        boolean result = instance.dostupan(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testDostupan3() throws Exception {
        int id = 2;
        ProveriDostupnost instance = new ProveriDostupnost();
        boolean expResult = false;
        boolean result = instance.dostupan(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testDostupan4() throws Exception {
        int id = 19;
        ProveriDostupnost instance = new ProveriDostupnost();
        boolean expResult = true;
        boolean result = instance.dostupan(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}

