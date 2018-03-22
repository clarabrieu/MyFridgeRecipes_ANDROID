package isen.fr.myfridgerecipes;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        btnSelection = (Button) findViewById(R.id.btnShow);
        productList = new ArrayList<>();





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
                List<Product> stList = ((ProductAdapter) mAdapter)
                        .getStudentist();

                for (int i = 0; i < stList.size(); i++) {
                    Product singleStudent = stList.get(i);
                    if (singleStudent.isSelected() == true) {

                        data = data + "\n" + singleStudent.getName().toString();
      /*
       * Toast.makeText( CardViewActivity.this, " " +
       * singleStudent.getName() + " " +
       * singleStudent.getEmailId() + " " +
       * singleStudent.isSelected(),
       * Toast.LENGTH_SHORT).show();
       */
                    }

                }

                Toast.makeText(FridgeActivity.this,
                        "Selected Products: \n" + data, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<String> stringList =  getArrayList("ProductList");

        if (stringList == null){
            stringList = new ArrayList<>();
        }
        for(String item : stringList) {
            productList.add(new Product(item,false));
        }

    }


    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void clickDelete(){
        
    }
}

