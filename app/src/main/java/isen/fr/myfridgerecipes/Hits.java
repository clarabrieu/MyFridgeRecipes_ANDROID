package isen.fr.myfridgerecipes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joris.lacroix on 22/03/2018.
 */

public class Hits {

    @SerializedName("recipe")
    @Expose
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe; }

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
}
