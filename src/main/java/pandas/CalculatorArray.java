package pandas;

import java.util.ArrayList;

public class CalculatorArray {

    /*
    * Somme d'un tableau
    * @param un collectionneur (ArrayList)
    * @return doubl,e resultat de la somme
     */
    public static double sum(ArrayList<String> d) {
        double resultat = 0;
        for (int i = 0; i < d.size(); i++) {
            resultat += Double.parseDouble(d.get(i));
        }
        return resultat;
    }

    /*
    * Minimum d'un tableau
    * @param un collectionneur (ArrayList)
    * @return double, resultat du Minimum
     */
    public static double min(ArrayList<String> d) {
        double resultat = Double.MAX_VALUE;
        double tmp = 0;
        for (int i = 0; i < d.size(); i++) {
            tmp = Double.parseDouble(d.get(i));
            if (tmp < resultat) {
                resultat = tmp;
            }
        }
        return resultat;
    }

    /*
    * Maximum d'un tableau
    * @param un collectionneur (ArrayList)
    * @return double, resultat du Maximum
     */
    public static double max(ArrayList<String> d) {
        double resultat = 0;
        double tmp = 0;
        for (int i = 0; i < d.size(); i++) {
            tmp = Double.parseDouble(d.get(i));
            if (tmp > resultat) {
                resultat = tmp;
            }
        }
        return resultat;
    }

    /*
    * Moyenne d'un tableau
    * @param un collectionneur (ArrayList)
    * @return double resultat de la moyenne
     */
    public static double mean(ArrayList<String> d) {
        double resultat = 0;
        for (int i = 0; i < d.size(); i++) {
            resultat += Double.parseDouble(d.get(i));
        }
        resultat = (resultat / (d.size()));
        return resultat;
    }

}
