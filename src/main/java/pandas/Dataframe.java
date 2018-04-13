package pandas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import pandas.Exception.ArgumentException;
import pandas.Exception.EmptyDataException;
import pandas.Exception.OperationException;
import pandas.Exception.TypeOrIndexException;

public class Dataframe {

    private ArrayList<Colonne> dataframes;
    public final int SUM = 0;
    public final int MIN = 1;
    public final int MAX = 2;
    public final int MEAN = 3;

    //Creation d'une dataFrame avec un CSV
    public Dataframe(String nomFichier) {

        dataframes = new ArrayList<>();
        String line = "";
        int taille = -1;
        boolean b = true;
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            while ((line = br.readLine()) != null && b) {
                String[] tab = line.split(",");
                if (taille == -1) {
                    taille = tab.length;
                }
                b = b & (taille == tab.length);
                if (b) {
                    if (dataframes.isEmpty()) {
                        for (int i = 0; i < tab.length; i++) {
                            for (int j = 0; j < tab.length; j++) {
                                if (i != j) {
                                    b = b & (!tab[i].equals(tab[j]));
                                }
                            }
                            b = b & !tab[i].replaceAll("\"", "").isEmpty();
                        }
                        if (b) {
                            for (int i = 0; i < tab.length; i++) {
                                ArrayList<ArrayList<String>> tmp = new ArrayList<>();
                                dataframes.add(new Colonne(tmp, tab[i].replaceAll("\"", "")));
                            }
                        }
                    } else {
                        for (int i = 0; i < tab.length; i++) {
                            b = b & !tab[i].replaceAll("\"", "").isEmpty();
                            if (b) {
                                ArrayList<String> tmpint = new ArrayList<>();
                                tmpint.add(tab[i].replaceAll("\"", ""));
                                dataframes.get(i).getTab().add(tmpint);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            dataframes = null;
        }
        if (!b) {
            dataframes = null;
            throw new ArgumentException("");
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
                        nom = values[i].get(0);
                        values[i].remove(0);

                        ArrayList<ArrayList<String>> tmp = new ArrayList<>();
                        for (int j = 0; j < values[i].size(); j++) {
                            ArrayList<String> tmp1 = new ArrayList<>();
                            if (!values[i].get(j).isEmpty()) {
                                tmp1.add(values[i].get(j));
                                tmp.add(tmp1);
                            } else {
                                b = false;
                            }
                        }
                        dataframes.add(new Colonne(tmp, nom));
                    }
                }
                i++;
            }
            if (!b) {
                dataframes = null;
                throw new ArgumentException("");
            }
        }
    }

    //Creation d'une dataFrame avec un sous-ensemble de lignes à partir de leur index
    public Dataframe selectDataLigne(Integer... index) {
        Dataframe data = null;
        if (dataframes != null && !dataframes.isEmpty()) {
            boolean b = true;
            ArrayList<Integer> id = new ArrayList<>();
            for (int i = 0; i < index.length; i++) {
                b = true;
                if (index[i] >= 0 && index[i] < dataframes.get(i).getTab().size()) {
                    for (int j = i + 1; j < index.length; j++) {
                        if (index[i] == index[j]) {
                            b = false;
                        }
                    }
                    if (b) {
                        id.add(index[i]);
                    }
                }
            }

            data = new Dataframe();
            ArrayList<Colonne> tmp = new ArrayList<>();

            for (int i = 0; i < dataframes.size(); i++) {
                ArrayList<ArrayList<String>> tmpcol = new ArrayList<>();
                for (Integer id1 : id) {
                    tmpcol.add((ArrayList<String>) dataframes.get(i).getTab().get(id1).clone());
                }
                tmp.add(new Colonne(tmpcol, dataframes.get(i).getLabel()));
            }
            data.setDataframes(tmp);
        }
        return data;
    }

//Creation d'une dataFrame avec un sous-ensemble de colonnes en utilisant les labels
    public Dataframe selectDataColonne(String... label) {
        Dataframe data = null;
        if (dataframes != null && !dataframes.isEmpty()) {

            boolean b = true;
            ArrayList<String> lab = new ArrayList<>();
            for (int i = 0; i < label.length; i++) {
                b = true;
                for (int j = i + 1; j < label.length; j++) {
                    if (label[i].equals(label[j])) {
                        b = false;
                    }
                }
                if (b) {
                    lab.add(label[i]);
                }

            }
            if (!lab.isEmpty()) {
                data = new Dataframe();
                ArrayList<Colonne> tmp = new ArrayList<>();
                for (int j = 0; j < lab.size(); j++) {
                    int i = indexLabel(lab.get(j));
                    if (i != -1) {
                        tmp.add(new Colonne((ArrayList<ArrayList<String>>) dataframes.get(i).getTab().clone(), dataframes.get(i).getLabel()));
                    }
                }
                data.setDataframes(tmp);
            }
        }
        return data;
    }

    private String showLabel() {
        String res = "";
        for (int i = 0; i < dataframes.size(); i++) {
            //System.out.print(dataframes.get(i).getLabel() + " ");
            res = res + dataframes.get(i).getLabel() + " ";
        }
        res = res + "\n";
        //System.out.print("\n");
        return res;
    }

    public String afficherDataframe() {
        String res = "Dataframe:\n";
        //System.out.println("Dataframe:");
        if (dataframes != null && !dataframes.isEmpty()) {
            res = res + showLabel();
            for (int i = 0; i < dataframes.get(0).getTab().size(); i++) {
                for (int j = 0; j < dataframes.size(); j++) {
                    //System.out.print(dataframes.get(j).getTab().get(i) + " ");
                    res = res + dataframes.get(j).getTab().get(i) + " ";
                }
                //System.out.print("\n");
                res = res + "\n";
            }
            //System.out.print("\n");
            res = res + "\n";
        }
        return res;
    }

    public String afficherPremieresLignes() {
        String res = "Dataframe premiere ligne:\n";
        //System.out.println("Dataframe premiere ligne:");

        if (dataframes != null && !dataframes.isEmpty()) {
            res = res + showLabel();
            int taille = 0;
            if (dataframes.get(0).getTab().size() > 1) {
                taille = 2;
            } else {
                taille = dataframes.get(0).getTab().size();
            }
            for (int i = 0; i < taille; i++) {
                for (int j = 0; j < dataframes.size(); j++) {
                    //System.out.print(dataframes.get(j).getTab().get(i) + " ");
                    res = res + dataframes.get(j).getTab().get(i) + " ";
                }
                //System.out.print("\n");
                res = res + "\n";
            }
        }
        return res;
    }

    public String afficherDernieresLignes() {
        String res = "Dataframe derniere ligne:\n";
        //System.out.println("Dataframe derniere ligne:");
        if (dataframes != null && !dataframes.isEmpty()) {
            res = res + showLabel();
            if (dataframes.get(0).getTab().size() > 0) {
                for (int i = dataframes.get(0).getTab().size() - 1; i < dataframes.get(0).getTab().size(); i++) {
                    for (int j = 0; j < dataframes.size(); j++) {
                        //System.out.print(dataframes.get(j).getTab().get(i) + " ");
                        res = res + dataframes.get(j).getTab().get(i) + " ";
                    }
                    //System.out.print("\n");
                    res = res + "\n";
                }
            }
        }
        return res;
    }

    private int indexLabel(String label) {
        if (dataframes != null && !dataframes.isEmpty()) {
            int i = 0;
            while ((i < dataframes.size()) && (!dataframes.get(i).getLabel().equals(label))) {
                i++;
            }
            if (i < dataframes.size()) {
                return i;
            }
        }
        return -1;
    }

    //Statistiques de base sur les colonnes -> sbc
    public double meanCol(String label) {
        int i = indexLabel(label);
        if (i != -1 && (dataframes.get(i).getType() == 1 || dataframes.get(i).getType() == 2)) {
            return dataframes.get(i).mean();
        } else {
            throw new ArgumentException("Impossible Mean !!");
        }
    }

    public double sumCol(String label) {
        int i = indexLabel(label);
        if (i != -1 && (dataframes.get(i).getType() == 1 || dataframes.get(i).getType() == 2)) {
            return dataframes.get(i).sum();
        } else {
            throw new ArgumentException("Impossible Sum !!");
        }
    }

    public double minCol(String label) {
        int i = indexLabel(label);
        if (i != -1 && (dataframes.get(i).getType() == 1 || dataframes.get(i).getType() == 2)) {
            return dataframes.get(i).min();
        } else {
            throw new ArgumentException("Impossible min !!");
        }
    }

    public double maxCol(String label) {
        int i = indexLabel(label);
        if (i != -1 && (dataframes.get(i).getType() == 1 || dataframes.get(i).getType() == 2)) {
            return dataframes.get(i).max();
        } else {
            throw new ArgumentException("Impossible Max !!");
        }
    }

    public void setDataframes(ArrayList<Colonne> dataframes) {
        this.dataframes = dataframes;
    }

    public ArrayList<Colonne> getDataframes() {
        return dataframes;
    }

    public Dataframe clone() {
        Dataframe data = null;
        if (dataframes != null && !dataframes.isEmpty()) {
            data = new Dataframe();
            ArrayList<Colonne> tmp = new ArrayList<>();

            for (int i = 0; i < dataframes.size(); i++) {
                tmp.add(new Colonne((ArrayList<ArrayList<String>>) dataframes.get(i).getTab().clone(), dataframes.get(i).getLabel()));
            }
            data.setDataframes(tmp);
        }
        return data;
    }

    public Dataframe groupby(String... label) {
        Dataframe data = this.clone();

        //Sécurité
        if (data != null && !dataframes.isEmpty() && label.length != 0) {

            boolean b = true;
            ArrayList<String> lab = new ArrayList<>();
            for (int i = 0; i < label.length; i++) {
                b = true;
                for (int j = i + 1; j < label.length; j++) {
                    if (label[i].equals(label[j])) {
                        b = false;
                    }
                }
                if (b) {
                    if (indexLabel(label[i]) != -1) {
                        lab.add(label[i]);
                    }
                }
            }

            if (lab.size() > 0) {
                ArrayList<ArrayList<String>> eq = new ArrayList();
                for (int i = 0; i < dataframes.size(); i++) {

                    for (int j = 0; j < lab.size(); j++) {
                        for (int k = 0; k < dataframes.get(i).getTab().size(); k++) {
                            if (dataframes.get(i).getLabel().equals(lab.get(j))) {
                                if (eq.size() == k) {
                                    eq.add(new ArrayList());
                                }
                                eq.get(k).add(dataframes.get(i).getTab().get(k).get(0));
                            }
                        }
                    }
                }

                //Algo distinc deux boucle
                for (int i = 0; i < eq.size(); i++) {
                    for (int j = 0; j < eq.size(); j++) {
                        if ((i != j) && (tabEquals(eq.get(i), eq.get(j)))) {
                            for (int k = 0; k < data.getDataframes().size(); k++) {
                                for (int l = 0; l < data.getDataframes().get(k).getTab().get(j).size(); l++) {
                                    int m = 0;
                                    while (m < lab.size() && !data.getDataframes().get(k).getLabel().equals(lab.get(m))) {
                                        m++;
                                    }
                                    if (m == lab.size()) {
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
                throw new ArgumentException("Erreur de group by");
            }
        } else {
            throw new EmptyDataException("Dataframe vide");
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
        if (d != null && !d.getDataframes().isEmpty()) {

            int i = indexLabel(label);
            if (i != -1 && (d.getDataframes().get(i).getType() == 2 || d.getDataframes().get(i).getType() == 1)) {
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
                            throw new OperationException("Erreur Opération !!");
                    }

                    d.getDataframes().get(i).getTab().remove(j);
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add(res + "");
                    d.getDataframes().get(i).getTab().add(j, tmp);
                }
                d.getDataframes().get(i).setType(2);

            } else {
                throw new TypeOrIndexException("Impossible Opération !!");
            }
        }
        return d;
    }

}
