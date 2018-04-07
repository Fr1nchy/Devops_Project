package pandas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataframe {

    private ArrayList<Colonne> dataframes;
    public final int SUM = 0;
    public final int MIN = 1;
    public final int MAX = 2;
    public final int MEAN = 3;

    //Creation d'une dataFrame avec un CSV
    public Dataframe(String nomFichier) {
        System.out.println("Ajouter des sécurités sur les noms, Id unique et sur la taille");
        
        dataframes = new ArrayList<>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            while ((line = br.readLine()) != null) {
                String[] tab = line.split(",");

                if (dataframes.isEmpty()) {
                    for (int i = 0; i < tab.length; i++) {
                        ArrayList<ArrayList<String>> tmp = new ArrayList<>();
                        dataframes.add(new Colonne(tmp, tab[i].replaceAll("\"", "")));
                    }
                } else {
                    for (int i = 0; i < tab.length; i++) {
                        ArrayList<String> tmpint = new ArrayList<>();
                        tmpint.add(tab[i].replaceAll("\"", ""));
                        dataframes.get(i).getTab().add(tmpint);
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    //Creation d'une dataFrame avec une collection
    public Dataframe(ArrayList<String>... values) {
        dataframes = new ArrayList<>();

        if ((values.length > 0) && (!values[0].isEmpty())) {
            int i = 0;
            boolean b = true;
            int taille = values[0].size();
            String nom = "";

            while (b && i < values.length) {
                if (!values[i].isEmpty()) {
                    b = b & (taille == values[i].size()) & (!nom.equals(values[i].get(0)));
                    if (b) {
                        String label = values[i].get(0);
                        values[i].remove(0);
                        
                        ArrayList<ArrayList<String>> tmp = new ArrayList<>();
                        for(int j = 0 ; j < values[i].size();j++){
                            ArrayList<String> tmp1 = new ArrayList<>();
                            tmp1.add(values[i].get(j));
                            tmp.add(tmp1);
                        }
                        dataframes.add(new Colonne(tmp, label));
                    }
                }
                i++;
            }
            if (!b) {
                System.out.println("Erreur de taille ");
            }
        }
    }

    //Creation d'une dataFrame avec un sous-ensemble de lignes à partir de leur index
    public Dataframe selectDataLigne(Integer... index) {
        System.out.println("Sécurités sur les index, index unique");

        Dataframe data = new Dataframe();
        ArrayList<Colonne> tmp = new ArrayList<>();

        for (int i = 0; i < dataframes.size(); i++) {
            ArrayList<ArrayList<String>> tmpcol = new ArrayList<>();

            for (Integer index1 : index) {
                ArrayList<String> tmpligne = new ArrayList<>();
                tmpligne.add(dataframes.get(i).getTab().get(index1).toString());
                tmpcol.add(tmpligne);
            }
            tmp.add(new Colonne(tmpcol, dataframes.get(i).getLabel()));
        }
        data.setDataframes(tmp);
        return data;
    }

    //Creation d'une dataFrame avec un sous-ensemble de colonnes en utilisant les labels
    public Dataframe selectDataColonne(String... label) {
        System.out.println("Sécurités sur les label, index label");
        Dataframe data = new Dataframe();
        ArrayList<Colonne> tmp = new ArrayList<>();

        for (String label1 : label) {
            int i = 0;
            while ((i < dataframes.size()) && (!dataframes.get(i).getLabel().equals(label1))) {
                i++;
            }
            if (i < dataframes.size()) {
                tmp.add(new Colonne((ArrayList<ArrayList<String>>) dataframes.get(i).getTab().clone(), dataframes.get(i).getLabel()));
            }
        }
        data.setDataframes(tmp);
        return data;
    }

    private void showLabel() {
        for (int i = 0; i < dataframes.size(); i++) {
            System.out.print(dataframes.get(i).getLabel() + " ");
        }
        System.out.print("\n");
    }

    public void afficherDataframe() {
        System.out.println("Dataframe:");
        if (!dataframes.isEmpty()) {
            showLabel();
            for (int i = 0; i < dataframes.get(0).getTab().size(); i++) {
                for (int j = 0; j < dataframes.size(); j++) {
                    System.out.print(dataframes.get(j).getTab().get(i) + " ");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }
    }

    public void afficherPremieresLignes() {
        System.out.println("Dataframe premiere ligne:");

        if (!dataframes.isEmpty()) {
            showLabel();
            int taille = 0;
            if (dataframes.get(0).getTab().size() > 1) {
                taille = 2;
            } else {
                taille = dataframes.size();
            }
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < dataframes.size(); j++) {
                    System.out.print(dataframes.get(j).getTab().get(i) + " ");
                }
                System.out.print("\n");
            }
        }
    }

    public void afficherDernieresLignes() {
        System.out.println("Dataframe derniere ligne:");
        if (!dataframes.isEmpty()) {
            showLabel();
            for (int i = dataframes.get(0).getTab().size() - 1; i < dataframes.get(0).getTab().size(); i++) {
                for (int j = 0; j < dataframes.size(); j++) {
                    System.out.print(dataframes.get(j).getTab().get(i) + " ");
                }
                System.out.print("\n");
            }
        }
    }

    private int indexLabel(String label) {
        int i = 0;
        while ((i < dataframes.size()) && (!dataframes.get(i).getLabel().equals(label))) {
            i++;
        }
        if (i < dataframes.size() && dataframes.get(i).getType() < 3 && dataframes.get(i).getType() > 0) {
            return i;
        } else {
            return -1;
        }
    }

    //Statistiques de base sur les colonnes -> sbc
    public double meanCol(String label) {
        int i = indexLabel(label);
        if (i != -1) {
            return dataframes.get(i).mean();
        } else {
            System.out.println("Impossible Mean !!");
            return 0;
        }
    }

    public double sumCol(String label) {
        int i = indexLabel(label);
        if (i != -1) {
           return dataframes.get(i).sum();
        } else {
            System.out.println("Impossible Sum !!");
            return 0;
        }
    }

    public double minCol(String label) {
        int i = indexLabel(label);
        if (i != -1) {
            return dataframes.get(i).min();
        } else {
            System.out.println("Impossible min !!");
            return 0;
        }
    }

    public double maxCol(String label) {
        int i = indexLabel(label);
        if (i != -1) {
            return dataframes.get(i).max();
        } else {
            System.out.println("Impossible Max !!");
            return 0;
        }
    }

    public void setDataframes(ArrayList<Colonne> dataframes) {
        this.dataframes = dataframes;
    }

    public ArrayList<Colonne> getDataframes() {
        return dataframes;
    }

    public Dataframe clone() {
        Dataframe data = new Dataframe();
        ArrayList<Colonne> tmp = new ArrayList<>();

        for (int i = 0; i < dataframes.size(); i++) {
            tmp.add(new Colonne((ArrayList<ArrayList<String>>) dataframes.get(i).getTab().clone(), dataframes.get(i).getLabel()));
        }
        data.setDataframes(tmp);
        return data;
    }

    public Dataframe groupby(String... label) {
        Dataframe data = this.clone();
        //Sécurité
        if (!dataframes.isEmpty()) {
            int i = 0;
            int j = 0;
            int k = 0;
            boolean b = true;
            while (i < label.length && (b)) {
                j = 0;
                while (j < dataframes.size() && !dataframes.get(j).getTab().isEmpty()
                        && !(label[i].equals(dataframes.get(j).getLabel()))) {
                    j++;
                }
                if (label[i].equals(dataframes.get(j).getLabel())) {
                    b = true;
                } else {
                    b = false;
                }
                i++;
            }

            if ((i == label.length) && (b)) {

                ArrayList<ArrayList<String>> eq = new ArrayList();
                for (i = 0; i < dataframes.size(); i++) {
                    for (j = 0; j < label.length; j++) {
                        for (k = 0; k < dataframes.get(i).getTab().size(); k++) {
                            if (dataframes.get(i).getLabel().equals(label[j])) {
                                if (eq.size() == k) {
                                    eq.add(new ArrayList());
                                }
                                eq.get(k).add(dataframes.get(i).getTab().get(k).get(0));
                            }
                        }
                    }
                }
                System.out.println("eq:" + eq);

                //Algo distinc deux boucle
                for (i = 0; i < eq.size(); i++) {
                    for (j = 0; j < eq.size(); j++) {
                        if ((i != j) && (tabEquals(eq.get(i), eq.get(j)))) {
                            for (k = 0; k < data.getDataframes().size(); k++) {
                                for (int l = 0; l < data.getDataframes().get(k).getTab().get(j).size(); l++) {
                                    int m = 0;
                                    while (m < label.length && !data.getDataframes().get(k).getLabel().equals(label[m])) {
                                        m++;
                                    }
                                    if (m == label.length) {
                                        data.getDataframes().get(k).getTab().get(i).add(
                                                data.getDataframes().get(k).getTab().get(j).get(l)
                                        );
                                    }
                                }
                                data.getDataframes().get(k).getTab().remove(j);
                            }
                            eq.remove(j);
                        }
                    }
                }
            } else {
                System.out.println("Erreur de group by");
            }
        } else {
            System.out.println("Dataframe vide");
        }
        return data;
    }

    private boolean tabEquals(ArrayList<String> a, ArrayList<String> b) {
        if (a.size() == b.size()) {
            int i = 0;

            while (i < a.size() && a.get(i).equals(b.get(i))) {
                i++;
            }
            if (i == a.size()) {
                return true;
            }
        }
        return false;
    }

    public Dataframe groupbyOperation(String label, int op) {
        Dataframe d = this.clone();
        int i = indexLabel(label);
        if (i != -1) {
            double res = 0;
            for (int j = 0; j < d.getDataframes().get(i).getTab().size(); j++) {

                switch (op) {
                    case SUM:
                        res = CalculatorArray.sum(d.getDataframes().get(i).getTab().get(j));
                        break;
                    case MIN:
                        res = CalculatorArray.min(d.getDataframes().get(i).getTab().get(j));
                        break;
                    case MAX:
                        res = CalculatorArray.max(d.getDataframes().get(i).getTab().get(j));
                        break;
                    case MEAN:
                        res = CalculatorArray.mean(d.getDataframes().get(i).getTab().get(j));
                        break;
                    default:
                        System.out.println("Erreur Opération !!");
                }

                d.getDataframes().get(i).getTab().remove(j);
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(res + "");
                d.getDataframes().get(i).getTab().add(j, tmp);
            }
        } else {
            System.out.println("Impossible Opération !!");
        }
        return d;
    }

}
