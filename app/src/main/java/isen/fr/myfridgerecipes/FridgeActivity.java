package isen.fr.myfridgerecipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FridgeActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public List<Product> productList;

    private Button btnSelection;
    private Button btnDelete;
    public String scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        btnSelection = (Button) findViewById(R.id.btnShow);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        this.productList = new ArrayList<>();

        scan = null;



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new ProductAdapter(productList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        btnSelection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                String data2 = "";
                List<Product> stList = ((ProductAdapter) mAdapter)
                        .getStudentist();

                for (int i = 0; i < stList.size(); i++) {
                    Product singleStudent = stList.get(i);
                    if (singleStudent.isSelected() == true) {

                        data = data + "\n" + singleStudent.getName().toString();
                        if(data2!=""){
                            data2+=","+singleStudent.getName().toString();
                        }else{
                            data2+=singleStudent.getName().toString();
                        }
                    }

                }

                Toast.makeText(FridgeActivity.this,
                        "Selected Products: \n" + data, Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(FridgeActivity.this, RecipeActivity.class);
                intent.putExtra("productRecipe",  data2);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick (View v){
                 /*ArrayList <String> productList =new ArrayList<>();

                 List<Product> stList = ((ProductAdapter) mAdapter).getStudentist();



                 ArrayList<String> stringList =  getArrayList("ProductList");
                 if (stringList == null){
                     stringList = new ArrayList<>();
                 }

                 for (int i = 0; i < stList.size(); i++) {
                     Product singleStudent = stList.get(i);
                     if (singleStudent.isSelected() == false) {
                         productList.add(singleStudent.getName().toString());
                     }
                 }
                 saveArrayList(productList, "ProductList");
                 //saveArrayList(null, "ProductList");
                 Intent intent = new Intent(FridgeActivity.this, MainActivity.class);
                 startActivity(intent);*/
                 List<Product> stList = ((ProductAdapter) mAdapter).getStudentist();
                 ArrayList <String> productList2 =new ArrayList<>();
                 for (int i = 0; i < stList.size(); i++) {
                     Product singleStudent = stList.get(i);
                     if (singleStudent.isSelected() == false) {

                         productList2.add(singleStudent.getName().toString());
                     }

                 }
                 saveArrayList(productList2, "ProductList");
                 Intent intent = new Intent(FridgeActivity.this, MainActivity.class);
                 startActivity(intent);
             }
         });

        Intent intent = getIntent();
        if (intent != null) {
            String codeBarre = intent.getStringExtra("CODE_BARRE");
            if (codeBarre != null) {

                new FridgeActivityTask(new CallBackInterface() {
                    @Override
                    public void success(String json) {
                        Gson gson = new GsonBuilder().create();
                        ProductResults productResults =gson.fromJson(json,ProductResults.class);

                        int status=productResults.getStatus();
                        if(status==1){
                            String product= productResults.getProductName().getProduct();
                            if(product!=null) {
                                Intent intent = new Intent(FridgeActivity.this, FridgeActivity.class);
                                intent.putExtra("product", product);
                                startActivity(intent);
                            }

                        }

                        //((TextView) findViewById(R.id.ivMail)).setText(String.valueOf(status));

                    }

                    @Override
                    public void error() {

                    }
                },codeBarre).execute();

            }
        }
    }


    @Override
    protected void onResume() {
        ArrayList <String> productList2;
        super.onResume();

        ArrayList<String> stringList =  getArrayList("ProductList");
        productList2 = new ArrayList<>();
        if (stringList == null){
            stringList = new ArrayList<>();
        }
        for(String item : stringList) {
            productList.add(new Product(item,false));
            productList2.add(item);
        }
        Intent intent = getIntent();
        if (intent != null) {
            String scan = intent.getStringExtra("product");
            if(scan!=null) {
                //productList.add(new Product(scan, false));


                if(!productList2.contains(scan)) {
                    productList2.add(scan);
                }

                saveArrayList(productList2,"ProductList");

                Intent intent2 = new Intent(FridgeActivity.this, MainActivity.class);
                startActivity(intent2);

            }
        }
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }


}

