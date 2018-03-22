package isen.fr.myfridgerecipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Recipes> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //puis créer un MyAdapter, lui fournir notre liste de villes.
        //cet adapter servira à remplir notre recyclerview
        recyclerView.setAdapter(new RecipesAdapter(cities));

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
                            String img;
                            String url;
                            String label;
                            List<String> ingredient;

                            int min = 10;
                            if(count<10){
                                min = count;
                            }
                            String[] tabImg = new String[]{"1","2","2","2","2","2","2","2","2","2"};
                            String[] tabLabel = new String[]{"1","2","2","2","2","2","2","2","2","2"};
                            for (int i=0;i<min;i++){
                                img = recipeResults.getHits().get(i).getRecipe().getImage();
                                url = recipeResults.getHits().get(i).getRecipe().getUrl();
                                label = recipeResults.getHits().get(i).getRecipe().getLabel();
                                ingredient = recipeResults.getHits().get(i).getRecipe().getIngredientLines();
                                tabImg[i]=img;
                                tabLabel[i]=label;
                                //cities.add(new Recipes(label,img));
                            }
                            Intent intent = new Intent(RecipeActivity.this, RecipeActivity.class);
                            intent.putExtra("affichageimg",  tabImg);
                            intent.putExtra("affichagelabel",  tabLabel);
                            startActivity(intent);
                        /*((TextView) findViewById(R.id.count)).setText(String.valueOf(count));
                        ((TextView) findViewById(R.id.img)).setText(String.valueOf(img));
                        ((TextView) findViewById(R.id.url)).setText(String.valueOf(url));
                        ((TextView) findViewById(R.id.ingredient)).setText(String.valueOf(ingredient));*/
                        }

                    }

                    @Override
                    public void error() {

                    }
                }, product).execute();}
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            String[] tabImg = intent.getStringArrayExtra("affichageimg");
            String[] tabLabel = intent.getStringArrayExtra("affichagelabel");

            if(tabImg!=null){
                for (int i=0;i<10;i++){
                    cities.add(new Recipes(tabLabel[i],tabImg[i]));
                }
            }



            }
        }

    }

