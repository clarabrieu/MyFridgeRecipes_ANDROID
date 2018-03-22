package isen.fr.myfridgerecipes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * http://www.jsonschema2pojo.org/
 */

public class ProductResults {
    @SerializedName("product")
    @Expose
    private Product_name product = null;

    @SerializedName("status")
    @Expose
    private int status;



    public Product_name getProductName() { return product; }


    public int getStatus() { return status; }

}
