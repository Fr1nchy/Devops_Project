package pandas;

import java.util.ArrayList;

public class CalculatorArray {

    public static double sum(ArrayList<String> d) {
        double resultat = 0;
        for (int i = 0; i < d.size(); i++) {
            resultat += Double.parseDouble(d.get(i));
        }
        return resultat;
    }

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

    public static double mean(ArrayList<String> d) {
        double resultat = 0;
        for (int i = 0; i < d.size(); i++) {
            resultat += Double.parseDouble(d.get(i));
        }
        resultat = (resultat / (d.size() - 1));
        return resultat;
    }

    

}
