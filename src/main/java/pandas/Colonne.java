/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandas;

import java.util.ArrayList;

public class Colonne {

    private ArrayList tab;
    private Integer type;
    private boolean groupby;
    
    public Colonne(ArrayList<String> tab) {
        this.type = typeDef(tab);
        this.tab = new ArrayList<>(tab);
        this.groupby = false;
    }
    private int typeDef(ArrayList<String> col) {
        int max = -1;
        int typetmp = 0;
        for (int t = 1; t < col.size(); t++) {
            if (col.get(t).equals("true") || col.get(t).equals("false")) {
                typetmp = 0;
            } else {
                try {
                    Float.parseFloat(col.get(t));
                    typetmp = 2;
                    Integer.parseInt(col.get(t));
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
        return type;
    }

    public ArrayList getTab() {
        return tab;
    }

    public void setTab(ArrayList tab) {
        this.tab = tab;
    }
}
