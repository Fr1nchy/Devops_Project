/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandas;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author x5pid
 */
public class Pandas {

    public static void main(String[] args) {        
        Dataframe d = null;
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while ((choice = menuPrincipale()) != 3) {
            switch (choice) {
                case 1:
                    System.out.println("Importer un CSV");
                    System.out.println("Rentrer le nom du CSV");
                    d = new Dataframe(input.next());
                    break;
                case 2:
                                       
                    ArrayList<ArrayList<String>> arr = new ArrayList<>();
                    while ((choice = menuDonnee()) != 2) {
                        System.out.println("Rentrer un label");
                        ArrayList<String> tmp = new ArrayList<>();
                        tmp.add(input.next());
                        while ((choice = menuDonneeTab()) != 2) {
                            System.out.println("Rentrer une donnée:");
                            tmp.add(input.next());
                        }
                        arr.add(tmp);
                    }
                    d = new Dataframe(arr.toArray(new ArrayList[arr.size()]));
                    break;
            }
            while ((choice = menuSecondaire()) != 5) {
                switch (choice) {
                    case 1:
                        System.out.println("Afficher");
                        while ((choice = menu1()) != 4) {
                            switch (choice) {
                                case 1:
                                    System.out.println(d.afficherDataframe());
                                    break;
                                case 2:
                                    System.out.println(d.afficherPremieresLignes());
                                    break;
                                case 3:
                                    System.out.println(d.afficherDernieresLignes());
                                    break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Sélectionner des données");
                        while ((choice = menu2()) != 3) {
                            switch (choice) {
                                case 1:
                                    d = d.selectDataLigne(input.nextInt());
                                    break;
                                case 2:
                                    d = d.selectDataColonne(input.next());
                                    break;
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Opération sur colonne");
                        while ((choice = menu3()) != 5) {
                            System.out.println("Rentrer le nom du label");
                            switch (choice) {
                                case 1:
                                    d.sumCol(input.next());
                                    break;
                                case 2:
                                    d.minCol(input.next());
                                    break;
                                case 3:
                                    d.maxCol(input.next());
                                    break;
                                case 4:
                                    d.meanCol(input.next());
                                    break;
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Opération de group");
                        while ((choice = menu4()) != 3) {
                            System.out.println("Rentrer le nom du label");
                            switch (choice) {
                                case 1:
                                    d = d.groupby(input.next());
                                    break;
                                case 2:
                                    System.out.println("Opération");
                                    System.out.println("0 - Somme");
                                    System.out.println("1 - Minimum");
                                    System.out.println("2 - Maximum");
                                    System.out.println("3 - Moyenne");
                                    d = d.groupbyOperation(input.next(), input.nextInt());
                                    break;
                            }
                        }
                        break;
                }
            }

        }
                
    }

    public static int menuPrincipale() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Importer un CSV");
        System.out.println("2 - Rentrer manuellement les données");
        System.out.println("3 - Quit");
        selection = input.nextInt();
        return selection;
    }

    public static int menuDonnee() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Rentrer manuellement les données");
        System.out.println("2 - Fin");
        selection = input.nextInt();
        return selection;
    }

    public static int menuDonneeTab() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Rentrer une donnée");
        System.out.println("2 - Fin");
        selection = input.nextInt();
        return selection;
    }

    public static int menuSecondaire() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher");
        System.out.println("2 - Sélectionner des données");
        System.out.println("3 - Opération sur colonne");
        System.out.println("4 - Opération de group");
        System.out.println("5 - Retour");
        selection = input.nextInt();
        return selection;
    }

    public static int menu1() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher dataframe");
        System.out.println("2 - Afficher les premieres lignes");
        System.out.println("3 - Afficher les dernieres lignes");
        System.out.println("4 - Retour");
        selection = input.nextInt();
        return selection;
    }

    public static int menu2() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Sélectionner des lignes");
        System.out.println("2 - Sélectionner des colonnes");
        System.out.println("3 - Retour");
        selection = input.nextInt();
        return selection;
    }

    public static int menu3() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Somme d'une colonne");
        System.out.println("2 - Minimum d'une colonne");
        System.out.println("3 - Maximum d'une colonne");
        System.out.println("4 - Moyenne d'une colonne");
        System.out.println("5 - Retour");
        selection = input.nextInt();
        return selection;
    }

    public static int menu4() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisissez");
        System.out.println("-------------------------\n");
        System.out.println("1 - Effectuer un group by");
        System.out.println("2 - Opération sur le group by");
        System.out.println("3 - Retour");
        selection = input.nextInt();
        return selection;
    }

}
