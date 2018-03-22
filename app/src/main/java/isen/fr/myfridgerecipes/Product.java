package isen.fr.myfridgerecipes;

/**
 * Created by Philippe on 21/03/2018.
 */

public class Product {
    private String name;
    private boolean isSelected;

    public Product() {

    }

    public Product(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
