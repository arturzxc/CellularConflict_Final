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
public class Point2DTest {
    
    public Point2DTest() {
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
     * Test of copy method, of class Point2D.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        Point2D instance = null;
        Point2D expResult = null;
        Point2D result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mirrorPoint method, of class Point2D.
     */
    @Test
    public void testMirrorPoint() {
        System.out.println("mirrorPoint");
        Point2D p = null;
        Point2D instance = null;
        Point2D expResult = null;
        Point2D result = instance.mirrorPoint(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addVelocity method, of class Point2D.
     */
    @Test
    public void testAddVelocity() {
        System.out.println("addVelocity");
        Vector2D velocity = null;
        Point2D instance = null;
        Point2D expResult = null;
        Point2D result = instance.addVelocity(velocity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vectorTo method, of class Point2D.
     */
    @Test
    public void testVectorTo() {
        System.out.println("vectorTo");
        Point2D p = null;
        Point2D instance = null;
        Vector2D expResult = null;
        Vector2D result = instance.vectorTo(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of distance method, of class Point2D.
     */
    @Test
    public void testDistance() {
        System.out.println("distance");
        Point2D point1 = null;
        Point2D point2 = null;
        float expResult = 0.0F;
        float result = Point2D.distance(point1, point2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of locationBetween method, of class Point2D.
     */
    @Test
    public void testLocationBetween() {
        System.out.println("locationBetween");
        float velocity = 0.0F;
        Point2D p1 = null;
        Point2D p2 = null;
        Point2D expResult = null;
        Point2D result = Point2D.locationBetween(velocity, p1, p2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Point2D.
     */
    @Test
    public void testToString() {
        //no need for test.
    }

    /**
     * Test of nextPoint method, of class Point2D.
     */
    @Test
    public void testNextPoint() {
        System.out.println("nextPoint");
        Vector2D vec = new Vector2D(5,5);
        Point2D instance = new Point2D(2,2);
        Point2D expResult = new Point2D(7,7);
        Point2D result = instance.nextPoint(vec);        
        assertTrue(expResult.equals(result));

    }

    /**
     * Test of main method, of class Point2D.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] str = null;
        Point2D.main(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
