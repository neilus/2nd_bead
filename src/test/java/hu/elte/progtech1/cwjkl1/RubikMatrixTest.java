package hu.elte.progtech1.cwjkl1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class RubikMatrixTest {
    RubikMatrix tester;
    Random rnd;
    int row, col, n, dim;

    @Test
    public void shiftUp() throws Exception {
        int[] expCol = new int[dim];
        int[] oldCol;
        for(int k = 0; k < dim; k++){
            expCol[ (dim + k - 1) % dim] = tester.rubik[col][k];
        }
        oldCol = tester.rubik[col];

        tester.shiftUp(col);

        int[] newCol = tester.rubik[col];

        assertArraysEquals("Shifting up", expCol, oldCol, newCol);
    }

    @Test
    public void shiftDown() throws Exception {
        int[] expCol = new int[dim];
        int[] oldCol;
        for(int k = 0; k < dim; k++){
            expCol[ (dim + k + 1) % dim] = tester.rubik[col][k];
        }
        oldCol = tester.rubik[col];

        tester.shiftDown(col);

        int[] newCol = tester.rubik[col];

        assertArraysEquals("Shifting down", expCol, oldCol, newCol);
    }

    @Test
    public void shiftRight() throws Exception {
        int[] expRow = new int[dim];
        int[] oldRow = new int[dim];
        for(int k = 0; k < dim; k++){
            expRow[ (dim + k + 1) % dim] = tester.rubik[k][row];
            oldRow[k] = tester.rubik[k][row];
        }

        tester.shiftRight(row);

        int[] newRow = new int[dim];
        for(int k = 0; k < dim; k++){
            newRow[k] = tester.rubik[k][row];
        }

        assertArraysEquals("Shifting right", expRow, oldRow, newRow);
    }

    public String toString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < arr.length; i++){
            sb.append(" ");
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    /**
     * Asserts when the expected and the actual arrays content are not the same
     * @param msg Assert message
     * @param expected Expected array
     * @param orig The original array before it was modified
     * @param actual The actual array
     */
    public void assertArraysEquals(String msg, int[] expected, int[] orig, int[] actual){
        try {
            assertArrayEquals(msg, expected, actual);
        }catch (ArrayComparisonFailure ex) {
            System.err.println("\n" + ex.getMessage() + "\ndim:" + tester.getSize());
            System.err.println("Expected: " + toString(expected));
            System.err.println("Original: " + toString(orig));
            System.err.println("Actual:   " + toString(actual));
            throw ex;
        }
    }

    @Test
    public void shiftLeft() throws Exception {
        int[] expRow = new int[dim];
        int[] oldRow = new int[dim];
        for(int k = 0; k < dim; k++){
            expRow[ (dim + k - 1) % dim] = tester.rubik[k][row];
            oldRow[k] = tester.rubik[k][row];
        }

        tester.shiftLeft(row);
        int[] newRow = new int[dim];
        for(int k = 0; k < dim; k++){
            newRow[k] = tester.rubik[k][row];
        }
        assertArraysEquals("Shifting a row left", expRow, oldRow, newRow);
    }

    @Before
    public void setUp() throws Exception {
        rnd = new Random();
        // check the color correctnes a lot of time, every time
        do {
            dim = rnd.nextInt(32);
        }while(dim <= 1); // but only positive numbers allowed!
        // create a new matrix with positive random dimension
        tester = new RubikMatrix(dim);
        // validate the matrix
        valueCheck(tester, dim);
        nrOfAppearanceCheck(tester, dim);

        row = rnd.nextInt(dim);
        col = rnd.nextInt(dim);
    }

    /**
     * How many times can/should be a number contained in the matrix
     * @param m the matrix initilized with random values
     * @param n the correct number of appearance
     */
    public void nrOfAppearanceCheck(RubikMatrix m, int n){
        int[] numbers = new int[n];

        for(int i = 0; i < m.rubik.length; i++){
            for(int j = 0; j < m.rubik[i].length; j++){
                int k = m.rubik[i][j];
                numbers[k] ++;
                assertTrue("" + k + ": " + numbers[k] + " < " +n,
                        numbers[k] <= n);
            }
        }

        for(int k = 0; k < m.rubik.length; k++){
            assertEquals("Should contain exact amount of occurrences",
                    n, numbers[k]);
        }

    }

    /**
     * Maximum value that the matrix could contain
     * @param m the matrix initilized with random values
     * @param n the values should be less than
     */
    public void valueCheck(RubikMatrix m, int n){

        for(int i = 0; i < m.rubik.length; i++){
            for(int j = 0; j < m.rubik[i].length; j++){
                assertTrue("Should contain a number less than " + n,
                        m.rubik[i][j] < n);

            }
        }
    }

    @Test
    public void dimensionBorderValues() {

        try {
            tester = new RubikMatrix(-1);
            tester.getSize();
        } catch (NullPointerException nullex) {
//            System.err.println("With negative dimension the exception message is: " + nullex.getMessage());
        }

        try {
            tester = new RubikMatrix(0);
            tester.getSize();
            assertNotNull("Zero (null) dimension", tester.getSize());
        } catch(NullPointerException nullex) {
//            System.err.println("With zero dimension the exception message is: " +nullex.getMessage());
        }
        {
            tester = new RubikMatrix(1);
            assertEquals("this is just a single color", 1, tester.getSize());
            int k = 0;
            for (int i = 0; i < tester.rubik.length; i++) {
                for (int j = 0; j < tester.rubik[i].length; j++) {
                    k++;
                }
            }
            assertEquals("It should have a single value", 1, k);
        }
        {
            tester = new RubikMatrix(2);
            assertEquals("this should be 2 colors", 2, tester.getSize());
            int k = 0;
            for (int i = 0; i < tester.rubik.length; i++) {
                for (int j = 0; j < tester.rubik[i].length; j++) {
                    k++;
                }
            }
            assertEquals("It should have four value", 4, k);

        }
    }
    @Test
    public void helloMatrix(){
        RubikMatrix rubik = new RubikMatrix(2);
        assertEquals("2x2", 2, rubik.getSize());
        RubikMatrix rubika2 = new RubikMatrix(3);
        assertEquals("3x3", 3, rubika2.getSize());
    }
}