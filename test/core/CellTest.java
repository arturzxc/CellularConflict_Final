/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.physics.PhyInfo;
import core.physics.Point2D;
import core.physics.Spatial;
import core.physics.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Artur Krzynowek
 */
public class CellTest {

    private CellImpl cell = new CellImpl();

    public CellTest() {
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
     * Test of setDestinationAndOriginalPoint method, of class Cell.
     */
    @Test
    public void testSetDestinationLocation() {
        assertTrue(true);
    }

    /**
     * Test of getMaxMoves method, of class Cell.
     */
    @Test
    public void testGetMaxMoves() {
        assertTrue(true);
    }

    /**
     * Test of getCurrentSpeed method, of class Cell.
     */
    @Test
    public void testGetCurrentSpeed() {
        assertTrue(true);
    }

    /**
     * Test of getHunger method, of class Cell.
     */
    @Test
    public void testGetHunger() {
        assertTrue(true);
    }

    /**
     * Test of getMaxSpeed method, of class Cell.
     */
    @Test
    public void testGetMaxSpeed() {
        assertTrue(true);
    }

    /**
     * Test of getMaxTurn method, of class Cell.
     */
    @Test
    public void testGetMaxTurn() {
        assertTrue(true);
    }

    /**
     * Test of getPrayOf method, of class Cell.
     */
    @Test
    public void testGetPrayOf() {
        assertTrue(true);
    }

    /**
     * Test of getPredatorOf method, of class Cell.
     */
    @Test
    public void testGetPredatorOf() {
        assertTrue(true);
    }

    /**
     * Test of getTheta method, of class Cell.
     */
    @Test
    public void testGetTheta() {
        assertTrue(true);
    }

    /**
     * Test of getVictimInfo method, of class Cell.
     */
    @Test
    public void testGetVictimInfo() {
        assertTrue(true);
    }

    /**
     * Test of setW method, of class Cell.
     */
    @Test
    public void testSetW() {
        assertTrue(true);
    }

    /**
     * Test of setH method, of class Cell.
     */
    @Test
    public void testSetH() {
        assertTrue(true);
    }

    /**
     * Test of setAge method, of class Cell.
     */
    @Test
    public void testSetAge() {
        assertTrue(true);
    }

    /**
     * Test of getMovesLeft method, of class Cell.
     */
    @Test
    public void testGetMovesLeft() {
        assertTrue(true);
    }

    /**
     * Test of isSelected method, of class Cell.
     */
    @Test
    public void testIsSelected() {
        assertTrue(true);
    }

    /**
     * Test of setSelected method, of class Cell.
     */
    @Test
    public void testSetSelected() {
        assertTrue(true);
    }

    /**
     * Test of isFrozen method, of class Cell.
     */
    @Test
    public void testIsFrozen() {
        assertTrue(true);
    }

    /**
     * Test of setFrozen method, of class Cell.
     */
    @Test
    public void testSetFrozen() {
        assertTrue(true);
    }

    /**
     * Test of getId method, of class Cell.
     */
    @Test
    public void testGetId() {
        assertTrue(true);
    }

    /**
     * Test of getDestinationOriginalPoint method, of class Cell.
     */
    @Test
    public void testGetDestinationLocation() {
        assertTrue(true);
    }

    /**
     * Test of getFieldOfView method, of class Cell.
     */
    @Test
    public void testGetFieldOfView() {
        assertTrue(true);
    }

    /**
     * Test of getVelocity method, of class Cell.
     */
    @Test
    public void testGetVelocity() {
        assertTrue(true);
    }

    /**
     * Test of setVelocity method, of class Cell.
     */
    @Test
    public void testSetVelocity() {
        assertTrue(true);
    }

    /**
     * Test of setLinkColor method, of class Cell.
     */
    @Test
    public void testSetLinkColor() {
        assertTrue(true);;
    }

    /**
     * Test of setPrayOf method, of class Cell.
     */
    @Test
    public void testSetPrayOf() {
        assertTrue(true);
    }

    /**
     * Test of setPredatorOf method, of class Cell.
     */
    @Test
    public void testSetPredatorOf() {
        assertTrue(true);
    }

    /**
     * Test of getLastPos method, of class Cell.
     */
    @Test
    public void testGetLastPos() {
        assertTrue(true);
    }

    /**
     * Test of getSeparationRange method, of class Cell.
     */
    @Test
    public void testGetSeparationRange() {
        assertTrue(true);
    }

    /**
     * Test of setSeparationRange method, of class Cell.
     */
    @Test
    public void testSetSeparationRange() {
        assertTrue(true);
    }

    /**
     * Test of getImgIcon method, of class Cell.
     */
    @Test
    public void testGetImgIcon() {
        assertTrue(true);
    }

    /**
     * Test of getDetectionRange method, of class Cell.
     */
    @Test
    public void testGetDetectionRange() {
        assertTrue(true);
    }

    /**
     * Test of setDetectionRange method, of class Cell.
     */
    @Test
    public void testSetDetectionRange() {
        assertTrue(true);
    }

    /**
     * Test of getH method, of class Cell.
     */
    @Test
    public void testGetH() {
        assertTrue(true);
    }

    /**
     * Test of getW method, of class Cell.
     */
    @Test
    public void testGetW() {
        assertTrue(true);
    }

    /**
     * Test of getParentCell method, of class Cell.
     */
    @Test
    public void testGetParentCell() {
        assertTrue(true);
    }

    /**
     * Test of getLinkColor method, of class Cell.
     */
    @Test
    public void testGetLinkColor() {
        assertTrue(true);
    }

    /**
     * Test of getNumber method, of class Cell.
     */
    @Test
    public void testGetNumber() {
        assertTrue(true);
    }

    /**
     * Test of getChildreen method, of class Cell.
     */
    @Test
    public void testGetChildreen() {
        assertTrue(true);
    }

    /**
     * Test of setMovesLeft method, of class Cell.
     */
    @Test
    public void testSetMovesLeft() {
        System.out.println("setMovesLeft");
        assertTrue(true);
    }

    /**
     * Test of setLocation method, of class Cell.
     */
    @Test
    public void testSetLocation() {
        assertTrue(true);
    }

    /**
     * Test of getLocation method, of class Cell.
     */
    @Test
    public void testGetLocation() {
        assertTrue(true);
    }

    /**
     * Test of setSpatialColided method, of class Cell.
     */
    @Test
    public void testSetSpatialColided() {
        assertTrue(true);
    }

    /**
     * Test of getSpatialColided method, of class Cell.
     */
    @Test
    public void testGetSpatialColided() {
        assertTrue(true);
    }

    /**
     * Test of getCollisionShape method, of class Cell.
     */
    @Test
    public void testGetCollisionShape() {
        assertTrue(true);
    }

    /**
     * Test of setCollisionShape method, of class Cell.
     */
    @Test
    public void testSetCollisionShape() {
        assertTrue(true);
    }

    /**
     * Test of typeToString method, of class Cell.
     */
    @Test
    public void testTypeToString() {
        assertTrue(true);
    }

    /**
     * Test of takeDamage method, of class Cell.
     */
    @Test
    public void testTakeDamage() {
        assertTrue(true);
    }

    /**
     * Test of saveLocationForHistory method, of class Cell.
     */
    @Test
    public void testSaveLocationForHistory() {
        assertTrue(true);
    }

    /**
     * Test of unstuck method, of class Cell.
     */
    @Test
    public void testUnstuck() {
        assertTrue(true);
    }

    /**
     * Test of harvestInformation method, of class Cell.
     */
    @Test
    public void testHarvestInformation() {
        assertTrue(true);
    }

    /**
     * Test of asd method, of class Cell.
     */
    @Test
    public void testTick() {
        assertTrue(true);
    }

    /**
     * Test of update method, of class Cell.
     */
    @Test
    public void testSimpleTick() {
        assertTrue(true);
    }

    /**
     * Test of move method, of class Cell.
     */
    @Test
    public void testMove() {
        assertTrue(true);
    }

    /**
     * Test of attemptMoveByVelocity method, of class Cell.
     */
    @Test
    public void testAttemptMove() {
        assertTrue(true);
    }

    /**
     * Test of goToDestinationPoint method, of class Cell.
     */
    @Test
    public void testGoToDestinationPoint() {
        assertTrue(true);
    }

    /**
     * Test of hiostoryContainsPositionInDistance method, of class Cell.
     */
    @Test
    public void testHiostoryContainsPositionInDistance() {
        assertTrue(true);
    }

    /**
     * Test of delete method, of class Cell.
     */
    @Test
    public void testDelete() {
        assertTrue(true);
    }

    /**
     * Test of removeParent method, of class Cell.
     */
    @Test
    public void testRemoveParent() {
        assertTrue(true);
    }

    /**
     * Test of reproduce method, of class Cell.
     */
    @Test
    public void testReproduce() {
        assertTrue(true);
    }

    /**
     * Test of draw method, of class Cell.
     */
    @Test
    public void testDraw() {
        assertTrue(true);
    }

    /**
     * Test of getType method, of class Cell.
     */
    @Test
    public void testGetType() {
        assertTrue(true);
    }

    /**
     * Test of simpleDraw method, of class Cell.
     */
    @Test
    public void testSimpleDraw() {
        assertTrue(true);
    }

    public class CellImpl extends Cell {

        public String typeToString() {
            return "Green";
        }

        public void asd() {
        }

        public void reproduce() {
        }

        public void draw(Graphics2D g2d) {
        }

        public int getType() {
            return 0;
        }

        @Override
        public void update(PhyInfo phyInfo) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * Test of randomisePoint method, of class Cell.
     */
    @Test
    public void testRandomisePoint() {
        System.out.println("randomisePoint");
        Point2D point = null;
        Cell instance = new CellImpl();
        instance.randomisePoint(point);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of attemptMoveByVelocity method, of class Cell.
     */
    @Test
    public void testAttemptMoveByVelocity() {
        System.out.println("attemptMoveByVelocity");
        Vector2D vel = null;
        Point2D currentLoc = null;
        float speed = 0.0F;
        Cell instance = new CellImpl();
        boolean expResult = false;
        boolean result = instance.attemptMoveByVelocity(vel, currentLoc, speed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of attemptMoveToPoint method, of class Cell.
     */
    @Test
    public void testAttemptMoveToPoint() {
        System.out.println("attemptMoveToPoint");
        Point2D targetPoint = null;
        Point2D currentLoc = null;
        float speed = 0.0F;
        Cell instance = new CellImpl();
        boolean expResult = false;
        boolean result = instance.attemptMoveToPoint(targetPoint, currentLoc, speed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
