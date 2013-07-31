/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.physics;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Artur Krzynowek
 */
public class Vector2DTest {

    public Vector2DTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Vector2D.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Vector2D vector = null;
        Vector2D instance = null;
        instance.add(vector);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class Vector2D.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        float scalar = 0.0F;
        Vector2D instance = null;
        instance.divide(scalar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Vector2D.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        float scalar = 0.0F;
        Vector2D instance = null;
        instance.multiply(scalar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Vector2D.
     */
    @Test
    public void testSet_Vector2D() {
        System.out.println("set");
        Vector2D v = null;
        Vector2D instance = null;
        instance.set(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Vector2D.
     */
    @Test
    public void testSet_float_float() {
        System.out.println("set");
        float x = 0.0F;
        float y = 0.0F;
        Vector2D instance = null;
        instance.set(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    


    /**
     * Test of length method, of class Vector2D.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Vector2D instance = null;
        float expResult = 0.0F;
        float result = instance.length();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalise method, of class Vector2D.
     */
    @Test
    public void testNormalise() {
        System.out.println("normalise");
        Vector2D instance = null;
        instance.normalise();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isZero method, of class Vector2D.
     */
    @Test
    public void testIsZero() {
        System.out.println("isZero");
        Vector2D instance = new Vector2D(10, 0);

        //should be false
        boolean expResult = (Float.compare(instance.x, 0) == 0
                && Float.compare(instance.y, 0) == 0);
        //should be false
        boolean result = instance.isZero();

        //should be false false therfore are equal and return true.
        assertEquals(expResult, result);

        Vector2D instance2 = new Vector2D(0, 0);

        //should be true
        boolean expResult2 = true;
        //should be true
        boolean result2 = instance.isZero();

        //should be true true therfore are equal and return true.
        assertEquals(expResult, result);


    }

    /**
     * Test of copy method, of class Vector2D.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        Vector2D instance = null;
        Vector2D expResult = null;
        Vector2D result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dotProduct method, of class Vector2D.
     */
    @Test
    public void testDotProduct() {
        System.out.println("dotProduct");
        Vector2D v1 = null;
        Vector2D v2 = null;
        float expResult = 0.0F;
        float result = Vector2D.dotProduct(v1, v2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcAngleDegrees method, of class Vector2D.
     */
    @Test
    public void testCalcAngleDegrees() {
        System.out.println("calcAngleDegrees");
        Vector2D v1 = null;
        Vector2D v2 = null;
        float expResult = 0.0F;
        float result = Vector2D.calcAngleDegrees(v1, v2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Vector2D.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Vector2D v = new Vector2D(1, 1);
        Vector2D instance = new Vector2D(1, 1);
        boolean expResult = true;
        boolean result = instance.equals(v);
        assertEquals(expResult, result);
    }

    /**
     * Test of revert method, of class Vector2D.
     */
    @Test
    public void testRevert() {
        System.out.println("revert");
        Vector2D instance = null;
        Vector2D expResult = null;
        Vector2D result = instance.revert();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Vector2D.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Vector2D instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomise method, of class Vector2D.
     */
    @Test
    public void testRandomise() {
        System.out.println("randomise");
        Vector2D instance = new Vector2D(0, 0);
        for (int i = 0; i < 1000; i++) {
            instance.randomise();
            assertTrue(instance.x >= -1 && instance.x <= 1);
            assertTrue(instance.y >= -1 && instance.y <= 1);
        }


    }

    /**
     * Test of main method, of class Vector2D.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] str = null;
        Vector2D.main(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
