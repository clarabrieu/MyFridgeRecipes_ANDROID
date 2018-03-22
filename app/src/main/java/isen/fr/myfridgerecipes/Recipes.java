package isen.fr.myfridgerecipes;

/**
 * Created by Philippe on 22/03/2018.
 */

public class Recipes {
    private String text;
    private String imageUrl;

    public Recipes(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
