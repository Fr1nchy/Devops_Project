/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pandas.Dataframes;

/**
 *
 * @author x5pid
 */
public class PandasTest {

    Dataframes d;

    public PandasTest() {
    }

    @Test(timeout = 100)
    public void testDataCSV() {
        d = new Dataframes("test.csv");
        assertFalse(d.getDataframes().isEmpty());
    }

    @Test(timeout = 100)
    public void testDataEmptyCSV() {
        d = new Dataframes("testempty.csv");
        assertFalse(d.getDataframes().isEmpty());
        assertTrue(d.getDataframes().size() == 1);
        assertTrue(d.getDataframes().get(0).getType() == 3);
        assertTrue(d.getDataframes().get(0).getTab().isEmpty());
    }

    @Test(timeout = 100)
    public void testDataCollectEmpty() {
        //Empty
        d = new Dataframes();
        assertTrue(d.getDataframes().isEmpty());
        //Empty collection
        ArrayList a = new ArrayList();
        d = new Dataframes(a);
        assertTrue(d.getDataframes().size() == 1);
        assertTrue(d.getDataframes().get(0).getType() == 3);
        assertTrue(d.getDataframes().get(0).getTab().isEmpty());

    }

    private ArrayList addAuto(int i) {
        ArrayList a = new ArrayList();

        switch (i) {
            case 0:
                //Boolean
                a.add("true");
                a.add("false");
                a.add("true");
                break;
            case 1:
                //Int
                a.add("0");
                a.add("1");
                a.add("2");
                break;
            case 2:
                //Float
                a.add("0.1");
                a.add("1.894");
                a.add("2");
                break;
            case 3:
                //String
                a.add("10");
                a.add("boolean");
                a.add("2.1485");
                break;

        }
        return a;
    }

    @Test(timeout = 100)
    public void testDataCollectInt() {
        ArrayList a = addAuto(1);
        d = new Dataframes(a);
        assertTrue(d.getDataframes().size() == 1);
        assertTrue(1 == d.getDataframes().get(0).getType());
        assertEquals(d.getDataframes().get(0).getTab().get(0), "0");
        assertEquals(d.getDataframes().get(0).getTab().get(1), "1");
        assertEquals(d.getDataframes().get(0).getTab().get(2), "2");

    }

    @Test(timeout = 100)
    public void testDataCollectFloat() {
        ArrayList a = addAuto(2);
        d = new Dataframes(a);
        assertTrue(d.getDataframes().size() == 1);
        assertTrue(2 == d.getDataframes().get(0).getType());
        assertEquals(d.getDataframes().get(0).getTab().get(0), "0.1");
        assertEquals(d.getDataframes().get(0).getTab().get(1), "1.894");
        assertEquals(d.getDataframes().get(0).getTab().get(2), "2");
    }

    @Test(timeout = 100)
    public void testDataCollectString() {
        ArrayList a = addAuto(3);
        d = new Dataframes(a);
        assertTrue(d.getDataframes().size() == 1);
        assertTrue(3 == d.getDataframes().get(0).getType());
        assertEquals(d.getDataframes().get(0).getTab().get(0), "10");
        assertEquals(d.getDataframes().get(0).getTab().get(1), "boolean");
        assertEquals(d.getDataframes().get(0).getTab().get(2), "2.1485");
    }

    @Test(timeout = 100)
    public void testDataCollectBoolean() {
        ArrayList a = addAuto(0);
        d = new Dataframes(a);
        assertTrue(d.getDataframes().size() == 1);
        assertTrue(0 == d.getDataframes().get(0).getType());
        assertEquals(d.getDataframes().get(0).getTab().get(0), "true");
        assertEquals(d.getDataframes().get(0).getTab().get(1), "false");
        assertEquals(d.getDataframes().get(0).getTab().get(2), "true");
    }

    @Test(timeout = 100)
    public void testDataCollectAll() {
        ArrayList a = addAuto(0);
        ArrayList a1 = addAuto(1);
        ArrayList a2 = addAuto(2);
        ArrayList a3 = addAuto(3);
        d = new Dataframes(a, a1, a2, a3);
        assertTrue(d.getDataframes().size() == 4);
        assertTrue(0 == d.getDataframes().get(0).getType());
        assertTrue(1 == d.getDataframes().get(1).getType());
        assertTrue(2 == d.getDataframes().get(2).getType());
        assertTrue(3 == d.getDataframes().get(3).getType());
        assertEquals(d.getDataframes().get(3).getTab().get(0), "10");
        assertEquals(d.getDataframes().get(3).getTab().get(1), "boolean");
        assertEquals(d.getDataframes().get(3).getTab().get(2), "2.1485");

        assertEquals(d.getDataframes().get(0).getTab().get(0), "true");
        assertEquals(d.getDataframes().get(0).getTab().get(1), "false");
        assertEquals(d.getDataframes().get(0).getTab().get(2), "true");

        assertEquals(d.getDataframes().get(2).getTab().get(0), "0.1");
        assertEquals(d.getDataframes().get(2).getTab().get(1), "1.894");
        assertEquals(d.getDataframes().get(2).getTab().get(2), "2");

        assertEquals(d.getDataframes().get(1).getTab().get(0), "0");
        assertEquals(d.getDataframes().get(1).getTab().get(1), "1");
        assertEquals(d.getDataframes().get(1).getTab().get(2), "2");
    }

    @Test(timeout = 100)
    public void testStatMeanEmpty() {
        d = new Dataframes();
        //d.sbcMean("A");
    }
    /*
    @Test(timeout = 100)
    public void testStatMean() {
        ArrayList a = addAuto(0);
        ArrayList a1 = addAuto(1);
        ArrayList a2 = addAuto(2);
        ArrayList a3 = addAuto(3);
        d = new Dataframes(a, a1, a2, a3);
    }

    @Test(timeout = 100)
    public void testStatMaxEmpty() {
        d = new Dataframes();

    }

    @Test(timeout = 100)
    public void testStatMax() {
        ArrayList a = addAuto(0);
        ArrayList a1 = addAuto(1);
        ArrayList a2 = addAuto(2);
        ArrayList a3 = addAuto(3);
        d = new Dataframes(a, a1, a2, a3);
    }

    @Test(timeout = 100)
    public void testStatMinEmpty() {
        d = new Dataframes();

    }

    @Test(timeout = 100)
    public void testStatMin() {
        ArrayList a = addAuto(0);
        ArrayList a1 = addAuto(1);
        ArrayList a2 = addAuto(2);
        ArrayList a3 = addAuto(3);
        d = new Dataframes(a, a1, a2, a3);
    }*/
}
