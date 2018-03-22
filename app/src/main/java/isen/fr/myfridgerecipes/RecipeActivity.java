package isen.fr.myfridgerecipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        if (intent != null) {
            String product = intent.getStringExtra("productRecipe");
            if(product!=null){
            new RecipeActivityTask(new CallBackInterface() {
                @Override
                public void success(String json) {
                    Gson gson = new GsonBuilder().create();
                    RecipeResults recipeResults = gson.fromJson(json, RecipeResults.class);
                    int count = recipeResults.getCount();
                    if(count>0) {
                        String img = recipeResults.getHits().get(0).getRecipe().getImage();
                        String url = recipeResults.getHits().get(0).getRecipe().getUrl();
                        List<String> ingredient = recipeResults.getHits().get(0).getRecipe().getIngredientLines();
                        ((TextView) findViewById(R.id.count)).setText(String.valueOf(count));
                        ((TextView) findViewById(R.id.img)).setText(String.valueOf(img));
                        ((TextView) findViewById(R.id.url)).setText(String.valueOf(url));
                        ((TextView) findViewById(R.id.ingredient)).setText(String.valueOf(ingredient));
                    }

                }

                @Override
                public void error() {

                }
            }, product).execute();}
        }
    }
}
