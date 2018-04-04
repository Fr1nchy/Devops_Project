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
        ArrayList<Colonne> tmp = new ArrayList<>();
        for (int i = 0; i < dataframes.size(); i++) {
            ArrayList tmpcol = new ArrayList<>();
            for (Integer index1 : index) {
                tmpcol.add(dataframes.get(i).getTab().get(index1));
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
                System.out.println("test:" + dataframes.get(i).getTab().get(0) +"=" + label1 + " b:" + dataframes.get(i).getTab().get(0).equals(label1));

            }
            /*if ((i < dataframes.size()) && dataframes.get(i).getTab().get(0).equals(label1)) {
                System.out.println("test:" + dataframes.get(i).getTab().get(0) + "=" + label1 +" b:" + dataframes.get(i).getTab().get(0).equals(label1));
                tmp.add(dataframes.get(i).getTab().clone());
            }*/
        }
        //data.setDataframes(tmp);
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
        return null;
    }
}
