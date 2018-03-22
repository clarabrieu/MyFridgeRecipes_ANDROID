package isen.fr.myfridgerecipes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product_name {

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("generic_name")
    @Expose
    private String generic_name;

    public String getProduct() {
        return product_name; }

    public String getGeneric_name() {
        return generic_name; }

    public void setProduct(String product_name) {
        this.product_name = product_name;
    }

    public void setGenericProduct(String generic_name) {
        this.generic_name = generic_name;
    }
}
