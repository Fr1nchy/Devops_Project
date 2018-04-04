package pandas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataframes {

    private ArrayList<Colonne> dataframes;

    //Creation d'une dataFrame avec un CSV
    public Dataframes(String nomFichier) {
        System.out.println("Ajouter des sécurités sur les noms, Id unique et sur la taille");
        dataframes = new ArrayList();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            while ((line = br.readLine()) != null) {
                String[] tab = line.split(",");

                if (dataframes.isEmpty()) {
                    for (int i = 0; i < tab.length; i++) {
                        ArrayList tmp = new ArrayList();
                        tmp.add(tab[i].replaceAll("\"", ""));
                        dataframes.add(new Colonne(tmp));
                    }
                } else {
                    for (int i = 0; i < tab.length; i++) {
                        dataframes.get(i).getTab().add(tab[i].replaceAll("\"", ""));
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    //Creation d'une dataFrame avec une collection
    public Dataframes(ArrayList... values) {
        dataframes = new ArrayList();
        if ((values.length > 0) && (!values[0].isEmpty())) {
            int i = 0;
            boolean b = true;
            int taille = values[0].size();
            String nom = "";

            while (b && i < values.length) {
                dataframes.add(new Colonne(values[i]));
                b = b & (taille == values[i].size()) & (!nom.equals(values[i].get(0)));
                i++;
            }
            if (!b) {
                System.out.println("Erreur de taille ");
            }
        }
    }

    //Creation d'une dataFrame avec un sous-ensemble de lignes à partir de leur index
    public Dataframes selectDataLigne(Integer... index) {
        System.out.println("Sécurités sur les index, index unique");

        Dataframes data = new Dataframes();
        ArrayList tmp = new ArrayList<>();

        for (int i = 0; i < dataframes.size(); i++) {
            ArrayList tmpcol = new ArrayList<>();
            tmpcol.add(dataframes.get(i).getTab().get(0));
            for (Integer index1 : index) {
                tmpcol.add(dataframes.get(i).getTab().get(index1 + 1));
            }
            tmp.add(new Colonne(tmpcol));
        }
        data.setDataframes(tmp);
        return data;
    }

    //Creation d'une dataFrame avec un sous-ensemble de colonnes en utilisant les labels
    public Dataframes selectDataColonne(String... label) {
        System.out.println("Sécurités sur les label, index label");
        Dataframes data = new Dataframes();
        ArrayList tmp = new ArrayList<>();

        for (String label1 : label) {
            int i = 0;
            while ((i < dataframes.size()) && (!dataframes.get(i).getTab().get(0).equals(label1))) {
                i++;
            }
            if (i < dataframes.size()) {
                tmp.add(new Colonne((ArrayList<String>) dataframes.get(i).getTab().clone()));
            }
        }
        data.setDataframes(tmp);
        return data;
    }

    public void afficherDataframe() {
        System.out.println("Dataframe:");
        if (!dataframes.isEmpty()) {
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
        System.out.println("Dataframe:");
        if (!dataframes.isEmpty()) {
            int taille = 0;
            if (dataframes.get(0).getTab().size() > 2) {
                taille = 3;
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
        System.out.println("Dataframe:");
        if (!dataframes.isEmpty()) {

            for (int i = 0; i < dataframes.size(); i++) {
                System.out.print(dataframes.get(i).getTab().get(0) + " ");
            }
            System.out.print("\n");

            for (int i = dataframes.get(0).getTab().size() - 1; i < dataframes.get(0).getTab().size(); i++) {
                for (int j = 0; j < dataframes.size(); j++) {
                    System.out.print(dataframes.get(j).getTab().get(i) + " ");
                }
                System.out.print("\n");
            }
        }
    }

    //Statistiques de base sur les colonnes -> sbc
    public void mean(String label) {
        int i = 0;
        while ((i < dataframes.size()) && (!dataframes.get(i).getTab().get(0).equals(label))) {
            i++;
        }
        if (i < dataframes.size()) {
            if (dataframes.get(i).getTab().get(0).equals(label) && dataframes.get(i).getType() < 3 && dataframes.get(i).getType() > 0) {
                double resultat = 0;
                for (int j = 1; j < dataframes.get(i).getTab().size(); j++) {
                    System.out.println("j[" + j + "]:: " + dataframes.get(i).getTab().get(j));
                    resultat += Double.parseDouble(dataframes.get(i).getTab().get(j).toString());
                }
                resultat = (resultat / (dataframes.get(i).getTab().size() - 1));
                System.out.println("Mean: " + resultat);
            } else {
                System.out.println("Impossible Mean !!");
            }
        } else {
            System.out.println("Pas trouvé le label");
        }

    }

    public void sum(String label) {
        int i = 0;
        while ((i < dataframes.size()) && (!dataframes.get(i).getTab().get(0).equals(label))) {
            i++;
        }
        if (i < dataframes.size()) {
            if (dataframes.get(i).getTab().get(0).equals(label) && dataframes.get(i).getType() < 3 && dataframes.get(i).getType() > 0) {
                double resultat = 0;
                for (int j = 1; j < dataframes.get(i).getTab().size(); j++) {
                    resultat += Double.parseDouble(dataframes.get(i).getTab().get(j).toString());
                }
                System.out.println("Sum: " + resultat);
            } else {
                System.out.println("Impossible Sum !!");
            }
        } else {
            System.out.println("Pas trouvé le label");
        }
    }

    public void min(String label) {
        int i = 0;
        while ((i < dataframes.size()) && (!dataframes.get(i).getTab().get(0).equals(label))) {
            i++;
        }
        if (i < dataframes.size()) {
            if (dataframes.get(i).getTab().get(0).equals(label) && dataframes.get(i).getType() < 3 && dataframes.get(i).getType() > 0) {
                double resultat = Double.MAX_VALUE;
                double tmp = 0;
                for (int j = 1; j < dataframes.get(i).getTab().size(); j++) {
                    tmp = Double.parseDouble(dataframes.get(i).getTab().get(j).toString());
                    if (tmp < resultat) {
                        resultat = tmp;
                    }
                }
                System.out.println("Min: " + resultat);
            } else {
                System.out.println("Impossible Min !!");
            }
        } else {
            System.out.println("Pas trouvé le label");
        }
    }

    public void max(String label) {
        int i = 0;
        while ((i < dataframes.size()) && (!dataframes.get(i).getTab().get(0).equals(label))) {
            i++;
        }
        if (i < dataframes.size()) {
            if (dataframes.get(i).getTab().get(0).equals(label) && dataframes.get(i).getType() < 3 && dataframes.get(i).getType() > 0) {
                double resultat = 0;
                double tmp = 0;
                for (int j = 1; j < dataframes.get(i).getTab().size(); j++) {
                    tmp = Double.parseDouble(dataframes.get(i).getTab().get(j).toString());
                    if (tmp > resultat) {
                        resultat = tmp;
                    }
                }
                System.out.println("Max: " + resultat);
            } else {
                System.out.println("Impossible Max !!");
            }
        } else {
            System.out.println("Pas trouvé le label");
        }
    }

    public void cumsum(String label) {
        double resultat = 0;
        for (int i = 0; i < dataframes.size(); i++) {
            if (dataframes.get(i).getTab().get(0).equals(label) && dataframes.get(i).getType() < 3 && dataframes.get(i).getType() > 0) {
                System.out.println("CumSum: ");
                for (int j = 1; j < dataframes.get(i).getTab().size(); j++) {
                    resultat += Double.parseDouble(dataframes.get(i).getTab().get(j).toString());
                    System.out.println(resultat + " ");
                }
            }
        }
    }

    public void cumprod(String label) {
        double resultat = 1;
        for (int i = 0; i < dataframes.size(); i++) {
            if (dataframes.get(i).getTab().get(0).equals(label) && dataframes.get(i).getType() < 3 && dataframes.get(i).getType() > 0) {
                System.out.println("CumProd: ");
                for (int j = 1; j < dataframes.get(i).getTab().size(); j++) {
                    resultat *= Double.parseDouble(dataframes.get(i).getTab().get(j).toString());
                    System.out.println(resultat + " ");
                }
            }
        }
    }

    public void setDataframes(ArrayList<Colonne> dataframes) {
        this.dataframes = dataframes;
    }

    public ArrayList<Colonne> getDataframes() {
        return dataframes;
    }

    //Regrouper les données -> d //demander au prof
    public Dataframes groupbyAggreate(String... label) {
        Dataframes data = new Dataframes();
        if (!dataframes.isEmpty()) {
            int i = 0;
            int j = 0;
            int k = 0;
            boolean b = true;
            while (i < label.length && (b)) {
                j = 0;
                while (j < dataframes.size() && !dataframes.get(j).getTab().isEmpty()
                        && !(label[i].equals(dataframes.get(j).getTab().get(0)))) {
                    j++;
                }
                if (label[i].equals(dataframes.get(j).getTab().get(0))) {
                    b = true;
                } else {
                    b = false;
                }
                i++;
            }

            if ((i == label.length) && (b)) {

                ArrayList<ArrayList<ArrayList>> tmpdata = new ArrayList();
                ArrayList<ArrayList> eq = new ArrayList();
                ArrayList<ArrayList> tmp = null;
                for (i = 0; i < dataframes.size(); i++) {
                    for (j = 0; j < label.length; j++) {
                        tmp = new ArrayList();
                        for (k = 0; k < dataframes.get(i).getTab().size(); k++) {
                            if (dataframes.get(i).getTab().get(0).equals(label[j])) {
                                if (eq.size() == k) {
                                    eq.add(new ArrayList());
                                    eq.get(k).add(dataframes.get(i).getTab().get(k));
                                } else {
                                    eq.get(k).add(dataframes.get(i).getTab().get(k));
                                }
                            } else {
                                ArrayList tmp1 = new ArrayList();
                                tmp1.add(dataframes.get(i).getTab().get(k));
                                tmp.add(tmp1);
                            }
                        }
                    }
                    if (!tmp.isEmpty()) {
                        tmpdata.add(tmp);
                    }
                }

                for (i = 0; i < tmpdata.size(); i++) {
                    for (j = 0; j < label.length; j++) {
                        if (tmpdata.get(i).get(0).get(0).equals(label[j])) {
                            tmpdata.remove(i);
                        }
                    }
                }
                System.out.println("tmpdata deb:" + tmpdata);

                //Algo distinc deux boucle
                for (i = 0; i < eq.size(); i++) {
                    for (j = 0; j < eq.size(); j++) {
                        if ((i != j) && (tabEquals(eq.get(i), eq.get(j)))) {
                            //regrouper
                            for (k = 0; k < tmpdata.size(); k++) {
                                for (int l = 0; l < tmpdata.get(k).get(0).size(); l++) {
                                    tmpdata.get(k).get(i).add(tmpdata.get(k).get(j).get(l));
                                }
                                tmpdata.get(k).remove(j);
                            }
                            eq.remove(j);
                        }
                    }
                }
                tmpdata.add(eq);
                System.out.println("tmpdata fin:" + tmpdata);

            } else {
                System.out.println("Erreur de group by");
            }
        } else {
            System.out.println("Dataframe vide");
        }

        return data;
    }

    private boolean tabEquals(ArrayList a, ArrayList b) {
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
}
