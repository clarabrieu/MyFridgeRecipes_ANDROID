package isen.fr.myfridgerecipes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joris.lacroix on 22/03/2018.
 */

public class Recipe {

    @SerializedName("ingredientLines")
    @Expose
    private List<String> ingredientLines;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("url")
    @Expose
    private String url;

    public List<String> getIngredientLines() { return ingredientLines; }

    public String getImage() { return image; }

    public String getUrl() { return url; }

    public void setIngredientLines(List<String> ingredientLines) { this.ingredientLines = ingredientLines; }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.image = url;
    }
}
