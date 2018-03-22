package isen.fr.myfridgerecipes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by joris.lacroix on 22/03/2018.
 */

public class RecipeResults {
    @SerializedName("hits")
    @Expose
    private List<Hits> hits = null;

    @SerializedName("count")
    @Expose
    private int count=0;



    public List<Hits> getHits() { return hits; }


    public int getCount() { return count; }
}
