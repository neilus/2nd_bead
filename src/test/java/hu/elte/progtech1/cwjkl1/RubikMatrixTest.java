package hu.elte.progtech1.cwjkl1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by neilus on 4/24/16.
 */
public class RubikMatrixTest {
    RubikMatrix tester;

    @Test
    public void shiftUp() throws Exception {



    }

    @Test
    public void shiftDown() throws Exception {

    }

    @Test
    public void shiftRight() throws Exception {

    }

    @Test
    public void shiftLeft() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void dimensionBorderValues() {

        try {
            tester = new RubikMatrix(-1);
            tester.getSize();
        } catch (NullPointerException nullex) {
            System.err.println("With negative dimension the exception message is: " + nullex.getMessage());
        }

        try {
            tester = new RubikMatrix(0);
            tester.getSize();
            assertNotNull("Zero (null) dimension", tester.getSize());
        } catch(NullPointerException nullex) {
            System.err.println("With zero dimension the exception message is: " +nullex.getMessage());
        }

        tester = new RubikMatrix(1);
        assertEquals("this is just a single color", 1, tester.getSize());
        assertEquals("nulla", 0, tester.rubik[0][0]);

    }
    @Test
    public void helloMatrix(){
        RubikMatrix rubik = new RubikMatrix(2);
        assertEquals("2x2", 2, rubik.getSize());
        RubikMatrix rubika2 = new RubikMatrix(3);
        assertEquals("3x3", 3, rubika2.getSize());
    }
}