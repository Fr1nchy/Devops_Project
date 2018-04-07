/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pandas.Dataframe;

/**
 *
 * @author x5pid
 */
public class PandasTest {

    Dataframe d;

    public PandasTest() {
    }

    @Test(timeout = 100)
    public void testConstructeurCSV() {
        //Vide
        d = new Dataframe();
        assertTrue(d.getDataframes().isEmpty());
        //Champs vide
        d = new Dataframe("");
        assertTrue(d.getDataframes().isEmpty());
        //CSV vide
        d = new Dataframe("testempty.csv");
        assertTrue(d.getDataframes().isEmpty());
        //Que des labels et pas de données
        d = new Dataframe("testlabels.csv");
        assertTrue(d.getDataframes().isEmpty());
        //Sécurité taille des colonnes identiques
        d = new Dataframe("testtaille.csv");
        assertTrue(d.getDataframes().isEmpty());
        //sécurité id identique 
        d = new Dataframe("ididentique.csv");
        assertTrue(d.getDataframes().isEmpty());
        //Fonctionnement normal
        d = new Dataframe("sample.csv");
        assertTrue(d.getDataframes().size() == 4);
        //Label
        assertTrue(d.getDataframes().get(0).getLabel().equals("First Name"));
        assertTrue(d.getDataframes().get(1).getLabel().equals("Last Name"));
        assertTrue(d.getDataframes().get(2).getLabel().equals("Email"));
        assertTrue(d.getDataframes().get(3).getLabel().equals("Age"));
        //Type
        assertTrue(d.getDataframes().get(0).getType() == 3);
        assertTrue(d.getDataframes().get(1).getType() == 3);
        assertTrue(d.getDataframes().get(2).getType() == 3);
        assertTrue(d.getDataframes().get(3).getType() == 1);
        //Valeur
        //Colonne 1
        assertTrue(d.getDataframes().get(0).getTab().size() == 3);
        assertTrue(d.getDataframes().get(0).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(0).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(0).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(0).getTab().get(0).get(0), "Bob");
        assertEquals(d.getDataframes().get(0).getTab().get(1).get(0), "Jane");
        assertEquals(d.getDataframes().get(0).getTab().get(2).get(0), "Pete");
        //Colonne 2
        assertTrue(d.getDataframes().get(1).getTab().size() == 3);
        assertTrue(d.getDataframes().get(1).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(1).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(1).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(1).getTab().get(0).get(0), "Smith");
        assertEquals(d.getDataframes().get(1).getTab().get(1).get(0), "Wilson");
        assertEquals(d.getDataframes().get(1).getTab().get(2).get(0), "Von Burg");
        //Colonne 3
        assertTrue(d.getDataframes().get(2).getTab().size() == 3);
        assertTrue(d.getDataframes().get(2).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(2).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(2).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(2).getTab().get(0).get(0), "bob@mywebsite.com");
        assertEquals(d.getDataframes().get(2).getTab().get(1).get(0), "jane@example.com");
        assertEquals(d.getDataframes().get(2).getTab().get(2).get(0), "pete@example.com");
        //Colonne 4
        assertTrue(d.getDataframes().get(3).getTab().size() == 3);
        assertTrue(d.getDataframes().get(3).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(3).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(3).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(3).getTab().get(0).get(0), "34");
        assertEquals(d.getDataframes().get(3).getTab().get(1).get(0), "21");
        assertEquals(d.getDataframes().get(3).getTab().get(2).get(0), "44");

    }

    @Test(timeout = 100)
    public void testConstructeurArrays() {

        //Champs vide
        ArrayList<String> a = new ArrayList<>();
        d = new Dataframe(a);
        assertTrue(d.getDataframes().isEmpty());
        //Que des labels et pas de données
        ArrayList<String> b = new ArrayList<>();
        a.add("lab1");
        b.add("lab2");
        d = new Dataframe(a, b);
        assertTrue(d.getDataframes().isEmpty());
        //Sécurité taille des colonnes identiques
        a = new ArrayList<>();
        b = new ArrayList<>();
        a.add("taille");
        a.add("1");
        a.add("2");
        a.add("3");
        b.add("faux");
        b.add("x");
        d = new Dataframe(a, b);
        assertTrue(d.getDataframes().isEmpty());
        //sécurité id identique 
        a = new ArrayList<>();
        b = new ArrayList<>();
        a.add("id");
        a.add("1");
        b.add("id");
        b.add("x");
        d = new Dataframe(a, b);
        d = new Dataframe(a, b);
        assertTrue(d.getDataframes().isEmpty());
        //Fonctionnement normal
        a = addAuto(0);
        b = addAuto(1);
        ArrayList<String> c = addAuto(2);
        ArrayList<String> e = addAuto(3);
        Dataframe d1 = new Dataframe(a, b, c, e);

        assertTrue(d.getDataframes().size() == 4);
        //Label
        assertTrue(d.getDataframes().get(0).getLabel().equals("Boolean"));
        assertTrue(d.getDataframes().get(1).getLabel().equals("Int"));
        assertTrue(d.getDataframes().get(2).getLabel().equals("Float"));
        assertTrue(d.getDataframes().get(3).getLabel().equals("String"));
        //Type
        assertTrue(d.getDataframes().get(0).getType() == 3);
        assertTrue(d.getDataframes().get(1).getType() == 3);
        assertTrue(d.getDataframes().get(2).getType() == 3);
        assertTrue(d.getDataframes().get(3).getType() == 1);
        //Valeur
        //Colonne 1
        assertTrue(d.getDataframes().get(0).getTab().size() == 3);
        assertTrue(d.getDataframes().get(0).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(0).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(0).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(0).getTab().get(0).get(0), "true");
        assertEquals(d.getDataframes().get(0).getTab().get(1).get(0), "false");
        assertEquals(d.getDataframes().get(0).getTab().get(2).get(0), "true");
        //Colonne 2
        assertTrue(d.getDataframes().get(1).getTab().size() == 3);
        assertTrue(d.getDataframes().get(1).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(1).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(1).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(1).getTab().get(0).get(0), "0");
        assertEquals(d.getDataframes().get(1).getTab().get(1).get(0), "1");
        assertEquals(d.getDataframes().get(1).getTab().get(2).get(0), "2");
        //Colonne 3
        assertTrue(d.getDataframes().get(2).getTab().size() == 3);
        assertTrue(d.getDataframes().get(2).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(2).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(2).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(2).getTab().get(0).get(0), "0.1");
        assertEquals(d.getDataframes().get(2).getTab().get(1).get(0), "1.894");
        assertEquals(d.getDataframes().get(2).getTab().get(2).get(0), "2");
        //Colonne 4
        assertTrue(d.getDataframes().get(3).getTab().size() == 3);
        assertTrue(d.getDataframes().get(3).getTab().get(0).size() == 1);
        assertTrue(d.getDataframes().get(3).getTab().get(1).size() == 1);
        assertTrue(d.getDataframes().get(3).getTab().get(2).size() == 1);
        assertEquals(d.getDataframes().get(3).getTab().get(0).get(0), "10");
        assertEquals(d.getDataframes().get(3).getTab().get(1).get(0), "boolean");
        assertEquals(d.getDataframes().get(3).getTab().get(2).get(0), "2.1485");
    }

    private ArrayList<String> addAuto(int i) {
        ArrayList<String> a = new ArrayList<>();

        switch (i) {
            case 0:
                //Boolean
                a.add("Boolean");
                a.add("true");
                a.add("false");
                a.add("true");
                break;
            case 1:
                //Int
                a.add("Int");
                a.add("0");
                a.add("1");
                a.add("2");
                break;
            case 2:
                a.add("Float");
                a.add("0.1");
                a.add("1.894");
                a.add("2");
                break;
            case 3:
                //String
                a.add("String");
                a.add("10");
                a.add("boolean");
                a.add("2.1485");
                break;
        }
        return a;
    }

    @Test(timeout = 100)
    public void testSelectDataLigne() {
        Dataframe d2 = null;
        //Securité vide dataframe
        d = new Dataframe();
        d2 = d.selectDataLigne(0);
        assertTrue(d2 == null);
        //Securité index hors champs
        d = new Dataframe("sample.csv");
        d2 = d.selectDataLigne(15);
        assertTrue(d2 == null);
        //Securité index identique
        d = new Dataframe("sample.csv");
        d2 = d.selectDataLigne(0, 0, 0);
        assertTrue(d2.getDataframes().size() == 4);
        //Label
        assertTrue(d2.getDataframes().get(0).getLabel().equals("First Name"));
        assertTrue(d2.getDataframes().get(1).getLabel().equals("Last Name"));
        assertTrue(d2.getDataframes().get(2).getLabel().equals("Email"));
        assertTrue(d2.getDataframes().get(3).getLabel().equals("Age"));
        //Type
        assertTrue(d2.getDataframes().get(0).getType() == 3);
        assertTrue(d2.getDataframes().get(1).getType() == 3);
        assertTrue(d2.getDataframes().get(2).getType() == 3);
        assertTrue(d2.getDataframes().get(3).getType() == 1);
        //Valeur
        //Colonne 1
        assertTrue(d2.getDataframes().get(0).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(0).size() == 1);
        assertEquals(d2.getDataframes().get(0).getTab().get(0).get(0), "Bob");
        //Colonne 2
        assertTrue(d2.getDataframes().get(1).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(1).getTab().get(0).size() == 1);
        assertEquals(d2.getDataframes().get(1).getTab().get(0).get(0), "Smith");
        //Colonne 3
        assertTrue(d2.getDataframes().get(2).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(2).getTab().get(0).size() == 1);
        assertEquals(d2.getDataframes().get(2).getTab().get(0).get(0), "bob@mywebsite.com");
        //Colonne 4
        assertTrue(d2.getDataframes().get(3).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(3).getTab().get(0).size() == 1);
        assertEquals(d2.getDataframes().get(3).getTab().get(0).get(0), "34");

        //Fonctionnement normal
        d = new Dataframe("sample.csv");
        d2 = d.selectDataLigne(0, 1);
        assertTrue(d2.getDataframes().size() == 4);
        //Label
        assertTrue(d2.getDataframes().get(0).getLabel().equals("First Name"));
        assertTrue(d2.getDataframes().get(1).getLabel().equals("Last Name"));
        assertTrue(d2.getDataframes().get(2).getLabel().equals("Email"));
        assertTrue(d2.getDataframes().get(3).getLabel().equals("Age"));
        //Type
        assertTrue(d2.getDataframes().get(0).getType() == 3);
        assertTrue(d2.getDataframes().get(1).getType() == 3);
        assertTrue(d2.getDataframes().get(2).getType() == 3);
        assertTrue(d2.getDataframes().get(3).getType() == 1);
        //Valeur
        //Colonne 1
        assertTrue(d2.getDataframes().get(0).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(0).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(0).getTab().get(0).get(0), "Bob");
        assertEquals(d2.getDataframes().get(0).getTab().get(1).get(0), "Jane");
        //Colonne 2
        assertTrue(d2.getDataframes().get(1).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(1).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(1).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(1).getTab().get(0).get(0), "Smith");
        assertEquals(d2.getDataframes().get(1).getTab().get(1).get(0), "Wilson");
        //Colonne 3
        assertTrue(d2.getDataframes().get(2).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(2).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(2).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(2).getTab().get(0).get(0), "bob@mywebsite.com");
        assertEquals(d2.getDataframes().get(2).getTab().get(1).get(0), "jane@example.com");
        //Colonne 4
        assertTrue(d2.getDataframes().get(3).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(3).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(3).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(3).getTab().get(0).get(0), "34");
        assertEquals(d2.getDataframes().get(3).getTab().get(1).get(0), "21");
    }

    @Test(timeout = 100)
    public void testSelectDataColonne() {
        Dataframe d2 = null;
        //Sécurité vide dataframe
        d = new Dataframe();
        d2 = d.selectDataColonne("First Name");
        assertTrue(d2 == null);
        //Sécurité label hors champs
        d = new Dataframe("sample.csv");
        d2 = d.selectDataColonne("nnnnnnn");
        assertTrue(d2 == null);
        //Sécurité label identique
        d = new Dataframe("sample.csv");
        d2 = d.selectDataColonne("First Name", "First Name");
        assertTrue(d2.getDataframes().size() == 1);
        //Label
        assertTrue(d2.getDataframes().get(0).getLabel().equals("First Name"));
        //Type
        assertTrue(d2.getDataframes().get(0).getType() == 1);
        //Valeur
        //Colonne 1
        assertTrue(d2.getDataframes().get(0).getTab().size() == 3);
        assertTrue(d2.getDataframes().get(0).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(1).size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(2).size() == 1);
        assertEquals(d2.getDataframes().get(0).getTab().get(0).get(0), "Bob");
        assertEquals(d2.getDataframes().get(0).getTab().get(1).get(0), "Jane");
        assertEquals(d2.getDataframes().get(0).getTab().get(2).get(0), "Pete");
        //Fonctionnement normal
        d = new Dataframe("sample.csv");
        d2 = d.selectDataColonne("First Name", "Age");
        d = new Dataframe("sample.csv");
        d2 = d.selectDataLigne(0, 1);
        assertTrue(d2.getDataframes().size() == 2);
        //Label
        assertTrue(d2.getDataframes().get(0).getLabel().equals("First Name"));
        assertTrue(d2.getDataframes().get(1).getLabel().equals("Age"));
        //Type
        assertTrue(d2.getDataframes().get(0).getType() == 3);
        assertTrue(d2.getDataframes().get(1).getType() == 1);
        //Valeur
        //Colonne 1
        assertTrue(d2.getDataframes().get(0).getTab().size() == 3);
        assertTrue(d2.getDataframes().get(0).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(1).size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(2).size() == 1);
        assertEquals(d2.getDataframes().get(0).getTab().get(0).get(0), "Bob");
        assertEquals(d2.getDataframes().get(0).getTab().get(1).get(0), "Jane");
        assertEquals(d2.getDataframes().get(0).getTab().get(2).get(0), "Pete");
        //Colonne 2
        assertTrue(d2.getDataframes().get(1).getTab().size() == 3);
        assertTrue(d2.getDataframes().get(1).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(1).getTab().get(1).size() == 1);
        assertTrue(d2.getDataframes().get(1).getTab().get(2).size() == 1);
        assertEquals(d2.getDataframes().get(1).getTab().get(0).get(0), "34");
        assertEquals(d2.getDataframes().get(1).getTab().get(1).get(0), "21");
        assertEquals(d2.getDataframes().get(1).getTab().get(2).get(0), "44");

    }

    @Test(timeout = 100)
    public void testMeanCol() {
        //Sécurité opération impossible string/boolean
        ArrayList<String> a = addAuto(0);
        d = new Dataframe(a);
        assertEquals(d.meanCol("Boolean"), 0, 0.0);

        a = addAuto(3);
        d = new Dataframe(a);
        assertEquals(d.meanCol("String"), 0, 0.0);

        //Sécurité opération sur des 0,0,0
        a = new ArrayList<>();
        a.add("test");
        for (int i = 0; i < 10; i++) {
            a.add("0");
        }
        d = new Dataframe(a);
        assertEquals(d.meanCol("test"), 0, 0.0);

        //Sécurité opération sur des MAX double / limité float
        a = new ArrayList<>();
        a.add("test");
        for (int i = 0; i < 10; i++) {
            a.add(Double.MAX_EXPONENT + "");
        }
        d = new Dataframe(a);
        assertEquals(d.meanCol("test"), 0, 0.0);

        //Avec un label hors champs
        a = addAuto(2);
        d = new Dataframe(a);
        assertEquals(d.meanCol("azerty"), 0, 0.0);

        //Fonctionnement normal
        a = addAuto(1);
        d = new Dataframe(a);
        assertEquals(d.meanCol("Int"), 1, 0.0);
    }

    @Test(timeout = 100)
    public void testMinCol() {
        //Sécurité opération impossible string/boolean
        ArrayList<String> a = addAuto(0);
        d = new Dataframe(a);
        assertEquals(d.minCol("Boolean"), 0, 0.0);

        a = addAuto(3);
        d = new Dataframe(a);
        assertEquals(d.minCol("String"), 0, 0.0);

        //Sécurité opération sur des MAX double / limité float
        a = new ArrayList<>();
        a.add("test");
        for (int i = 0; i < 10; i++) {
            a.add(Double.MAX_EXPONENT + "");
        }
        d = new Dataframe(a);
        assertEquals(d.minCol("test"), 0, 0.0);

        //Avec un label hors champs
        a = addAuto(2);
        d = new Dataframe(a);
        assertEquals(d.minCol("azerty"), 0, 0.0);

        //Fonctionnement normal
        a = addAuto(1);
        d = new Dataframe(a);
        assertEquals(d.minCol("Int"), 1, 0.0);
    }

    @Test(timeout = 100)
    public void testMaxCol() {

        //Sécurité opération impossible string/boolean
        ArrayList<String> a = addAuto(0);
        d = new Dataframe(a);
        assertEquals(d.maxCol("Boolean"), 0, 0.0);

        a = addAuto(3);
        d = new Dataframe(a);
        assertEquals(d.maxCol("String"), 0, 0.0);

        //Sécurité opération sur des MAX double / limité float
        a = new ArrayList<>();
        a.add("test");
        for (int i = 0; i < 10; i++) {
            a.add(Double.MAX_EXPONENT + "");
        }
        d = new Dataframe(a);
        assertEquals(d.maxCol("test"), 0, 0.0);

        //Avec un label hors champs
        a = addAuto(2);
        d = new Dataframe(a);
        assertEquals(d.maxCol("azerty"), 0, 0.0);

        //Fonctionnement normal
        a = addAuto(1);
        d = new Dataframe(a);
        assertEquals(d.maxCol("Int"), 2, 0.0);
    }

    @Test(timeout = 100)
    public void testSumCol() {
        //Sécurité opération impossible string/boolean
        ArrayList<String> a = addAuto(0);
        d = new Dataframe(a);
        assertEquals(d.sumCol("Boolean"), 0, 0.0);

        a = addAuto(3);
        d = new Dataframe(a);
        assertEquals(d.sumCol("String"), 0, 0.0);

        //Sécurité opération sur des MAX double / limité float
        a = new ArrayList<>();
        a.add("test");
        for (int i = 0; i < 10; i++) {
            a.add(Double.MAX_EXPONENT + "");
        }
        d = new Dataframe(a);
        assertEquals(d.sumCol("test"), 0, 0.0);

        //Avec un label hors champs
        a = addAuto(2);
        d = new Dataframe(a);
        assertEquals(d.sumCol("azerty"), 0, 0.0);

        //Fonctionnement normal
        a = addAuto(1);
        d = new Dataframe(a);
        assertEquals(d.sumCol("Int"), 3, 0.0);
    }

    @Test(timeout = 100)
    public void groupby() {

        //Sécurité vide parametre
        d = new Dataframe("sample.csv");
        Dataframe d2 = d.groupby();
        assertTrue(d2 == null);
        //Sécurité vide dataframe
        d = new Dataframe();
        d2 = d.groupby("azerty");
        assertTrue(d2 == null);
        //Sécutité hors champs
        d = new Dataframe("sample.csv");
        d2 = d.groupby("azerty");
        assertTrue(d2 == null);

        //Sécutité même label
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        a.add("Int");
        a.add("1");
        a.add("1");
        b.add("float");
        b.add("1.25");
        b.add("4");
        d = new Dataframe(a, b);
        d2 = d.groupby("Int", "Int");
        assertTrue(d2.getDataframes().size() == 2);
        //Label
        assertTrue(d2.getDataframes().get(0).getLabel().equals("Int"));
        assertTrue(d2.getDataframes().get(1).getLabel().equals("float"));
        //Type
        assertTrue(d2.getDataframes().get(0).getType() == 1);
        assertTrue(d2.getDataframes().get(1).getType() == 2);
        //Valeur
        //Colonne 1
        assertTrue(d2.getDataframes().get(0).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(0).size() == 1);
        assertEquals(d2.getDataframes().get(0).getTab().get(0).get(0), "1");
        //Colonne 2
        assertTrue(d2.getDataframes().get(1).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(1).getTab().get(0).size() == 2);
        assertEquals(d2.getDataframes().get(1).getTab().get(0).get(0), "1.25");
        assertEquals(d2.getDataframes().get(1).getTab().get(0).get(1), "4");
        //Test des min max sum mean colonne
        assertEquals(d2.maxCol("float"), 4, 0.0);
        assertEquals(d2.minCol("float"), 1.25, 0.0);
        assertEquals(d2.meanCol("float"), 2.625, 0.0);
        assertEquals(d2.sumCol("float"), 5.25, 0.0);

        //Aucune modification
        a = new ArrayList<>();
        b = new ArrayList<>();
        a.add("Int");
        a.add("1");
        b.add("float");
        b.add("1.25");
        d = new Dataframe(a, b);
        d2 = d.groupby("Int");
        assertTrue(d2.getDataframes().size() == 2);
        //Label
        assertTrue(d2.getDataframes().get(0).getLabel().equals("Int"));
        assertTrue(d2.getDataframes().get(1).getLabel().equals("float"));
        //Type
        assertTrue(d2.getDataframes().get(0).getType() == 1);
        assertTrue(d2.getDataframes().get(1).getType() == 2);
        //Valeur
        //Colonne 1
        assertTrue(d2.getDataframes().get(0).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(0).size() == 1);
        assertEquals(d2.getDataframes().get(0).getTab().get(0).get(0), "1");
        //Colonne 2
        assertTrue(d2.getDataframes().get(1).getTab().size() == 1);
        assertTrue(d2.getDataframes().get(1).getTab().get(0).size() == 1);
        assertEquals(d2.getDataframes().get(1).getTab().get(0).get(0), "1.25");
    }

    @Test(timeout = 100)
    public void groupbyOperation() {
        //vide
        d = new Dataframe();
        Dataframe d2 = d.groupbyOperation("azerty", d.MAX);
        assertTrue(d2 == null);

        //Sécurité hors champs label
        d = new Dataframe("sample.csv");
        d2 = d.groupbyOperation("zaes", d.MAX);
        assertTrue(d2 == null);

        //Sécurité hors champs opération
        d = new Dataframe("sample.csv");
        d2 = d.groupbyOperation("First Name", 54946);
        assertTrue(d2 == null);

        //Fonctionnement normal avec sum mean min max
        ArrayList<String> g = new ArrayList<>();
        ArrayList<String> sum = new ArrayList<>();
        ArrayList<String> mean = new ArrayList<>();
        ArrayList<String> min = new ArrayList<>();
        ArrayList<String> max = new ArrayList<>();

        g.add("g");
        g.add("1");
        g.add("1");
        g.add("2");
        g.add("2");

        sum.add("sum");
        sum.add("0");
        sum.add("1");
        sum.add("2");
        sum.add("3");

        min.add("min");
        min.add("4");
        min.add("5");
        min.add("6");
        min.add("7");

        max.add("max");
        max.add("8");
        max.add("9");
        max.add("10");
        max.add("11");

        mean.add("mean");
        mean.add("12");
        mean.add("13");
        mean.add("14");
        mean.add("15");

        d = new Dataframe(g, sum, min, max, mean);
        d2 = d.groupby("g");
        d2 = d2.groupbyOperation("max", d2.MAX);
        d2 = d2.groupbyOperation("min", d2.MIN);
        d2 = d2.groupbyOperation("sum", d2.SUM);
        d2 = d2.groupbyOperation("mean", d2.MEAN);

        assertTrue(d2.getDataframes().size() == 5);
        //Label
        assertTrue(d2.getDataframes().get(0).getLabel().equals("g"));
        assertTrue(d2.getDataframes().get(1).getLabel().equals("sum"));
        assertTrue(d2.getDataframes().get(2).getLabel().equals("min"));
        assertTrue(d2.getDataframes().get(3).getLabel().equals("max"));
        assertTrue(d2.getDataframes().get(4).getLabel().equals("mean"));

        //Type
        assertTrue(d2.getDataframes().get(0).getType() == 1);
        assertTrue(d2.getDataframes().get(1).getType() == 1);
        assertTrue(d2.getDataframes().get(2).getType() == 1);
        assertTrue(d2.getDataframes().get(3).getType() == 1);
        assertTrue(d2.getDataframes().get(4).getType() == 1);
        //Valeur
        //Colonne 1
        assertTrue(d2.getDataframes().get(0).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(0).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(0).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(0).getTab().get(0).get(0), "1");
        assertEquals(d2.getDataframes().get(0).getTab().get(1).get(0), "2");
        //Colonne 2 SUM
        assertTrue(d2.getDataframes().get(1).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(1).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(1).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(1).getTab().get(0).get(0), "1");
        assertEquals(d2.getDataframes().get(1).getTab().get(1).get(0), "5");
        //Colonne 3 min
        assertTrue(d2.getDataframes().get(2).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(2).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(2).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(2).getTab().get(0).get(0), "4");
        assertEquals(d2.getDataframes().get(2).getTab().get(1).get(0), "6");
         //Colonne 4 max
        assertTrue(d2.getDataframes().get(3).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(3).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(3).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(3).getTab().get(0).get(0), "9");
        assertEquals(d2.getDataframes().get(3).getTab().get(1).get(0), "11");
        
        //Colonne 5  mean
        assertTrue(d2.getDataframes().get(4).getTab().size() == 2);
        assertTrue(d2.getDataframes().get(4).getTab().get(0).size() == 1);
        assertTrue(d2.getDataframes().get(4).getTab().get(1).size() == 1);
        assertEquals(d2.getDataframes().get(4).getTab().get(0).get(0), "12.5");
        assertEquals(d2.getDataframes().get(4).getTab().get(1).get(0), "14.5");

    }
}
