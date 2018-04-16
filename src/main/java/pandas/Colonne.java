/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandas;

import java.util.ArrayList;

public class Colonne {

    private ArrayList<ArrayList<String>> tab;
    private String label;
    private Integer type;

    /*
    * Création d'une colonne à partir d'un tableau de tableaux
    * @param un tableau de tableaux et un label du dataframe
     */
    public Colonne(ArrayList<ArrayList<String>> tab, String label) {
        this.type = typeDef(tab);
        this.tab = new ArrayList<ArrayList<String>>(tab);
        this.label = label;
    }

    /*
    * Max d'une colonne
    * @param un label du Dataframe
    * @return double resultat du max
     */
    public double max() {
        double resultat = 0;
        double tmp = 0;
        System.out.println("type:" + type);
        for (int i = 0; i < tab.size(); i++) {
            tmp = CalculatorArray.max(tab.get(i));
            if (tmp > resultat) {
                resultat = tmp;
            }
        }
        System.out.println("Max colonne:" + resultat);
        return resultat;
    }

    /*
    * Min d'une colonne
    * @param un label du Dataframe
    * @return double resultat du min
     */
    public double min() {
        double resultat = Double.MAX_VALUE;
        double tmp = 0;
        for (int i = 0; i < tab.size(); i++) {
            tmp = CalculatorArray.min(tab.get(i));
            if (tmp < resultat) {
                resultat = tmp;
            }
        }

        System.out.println("Min colonne:" + resultat);
        return resultat;
    }

    /*
    * Somme d'une colonne
    * @param un label du Dataframe
    * @return double resultat de la somme
     */
    public double sum() {
        double resultat = 0;
        for (int i = 0; i < tab.size(); i++) {
            resultat += CalculatorArray.sum(tab.get(i));
        }
        System.out.println("Sum colonne:" + resultat);
        return resultat;
    }

    /*
    * Moyenne d'une colonne
    * @param un label du Dataframe
    * @return double resultat de la moyenne
     */
    public double mean() {
        double resultat = 0;
        for (int i = 0; i < tab.size(); i++) {
            for (int j = 0; j < tab.get(i).size(); j++) {
                resultat += Double.parseDouble(tab.get(i).get(j));
            }
        }
        resultat = (resultat / (tab.size() + tab.get(0).size() - 1));
        System.out.println("Mean colonne:" + resultat);
        return resultat;
    }

    /*
    * Définit le type du collectionneur
    * @param un ArrayList d'un ArrayList de strings
    * @return int le type du tableau
     */
    private int typeDef(ArrayList<ArrayList<String>> col) {
        int max = -1;
        int typetmp = 0;
        for (int t = 0; t < col.size(); t++) {
            if (col.get(t).get(0).equals("true") || col.get(t).get(0).equals("false")) {
                typetmp = 0;
            } else {
                try {
                    Float.parseFloat(col.get(t).get(0));
                    typetmp = 2;
                    Integer.parseInt(col.get(t).get(0));
                    typetmp = 1;
                } catch (Exception ex) {
                    if (typetmp != 2) {
                        typetmp = 3;
                    }
                }
            }
            if (typetmp > max) {
                max = typetmp;
            }
        }
        return max;
    }

    public Integer getType() {
        if (type == -1) {
            type = typeDef(tab);
        }
        return type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<ArrayList<String>> getTab() {
        return tab;
    }

    public void setTab(ArrayList<ArrayList<String>> tab) {
        this.tab = tab;
    }

}
