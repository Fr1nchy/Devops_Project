/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandas;

/**
 *
 * @author x5pid
 */
public class Pandas {

    public static void main(String[] args) {
        Dataframe d;
        d = new Dataframe("doublon.csv");
        d.afficherDataframe();

        d.mean("Age");
        d.max("Age");
        d.min("Age");
        d.sum("Age");
        System.out.println("");
        d.afficherDernieresLignes();
        System.out.println("");
        d.afficherPremieresLignes();
        System.out.println("");
        Dataframe d2 = d.selectDataLigne(0, 1);
        d2.afficherDataframe();
        System.out.println("");
        Dataframe d1 = d.selectDataColonne("First Name", "Age");
        d1.afficherDataframe();
        System.out.println("");
        
        Dataframe d3 = d.groupbyAggreate("First Name");
        d3.afficherDataframe();

        /*
        //d1.afficherDataframe();
        //d.afficherDernieresLignes();
        //d.afficherPremieresLignes();
        /*ArrayList a = new ArrayList();
        ArrayList a1 = new ArrayList();
        ArrayList a2 = new ArrayList();
        ArrayList a3 = new ArrayList();
        ArrayList a4 = new ArrayList();
        
        a.add("Int");
        a.add("0");
        a.add("1");
        a.add("2");
        
        a1.add("Float");
        a1.add("0.1");
        a1.add("1.894");
        a1.add("2");
        
        a2.add("String");
        a2.add("10");
        a2.add("boolean");
        a2.add("2.1485");
        
        a3.add("Boolean");
        a3.add("true");
        a3.add("false");
        a3.add("true");
        
        d = new Dataframe(a,a1,a2,a3);
        d.afficherDataframe();
        d.afficherPremieresLignes();
        d.afficherDernieresLignes();
        
        d.mean("Int");
        d.mean("Float");
        d.mean("String");
        d.mean("Boolean");
        
        d.max("Int");
        d.max("B");
        
        d.min("Int");
        d.min("B");*/
    }

}
