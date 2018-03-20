package isen.fr.myfridgerecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String DONNEE = "json";
        String barecode="";

        Intent intent = getIntent();
        if (intent != null) {
            try {
                JSONObject obj = new JSONObject(intent.getStringExtra(DONNEE));
                barecode=(obj.getString("barecode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        new MainActivityTask(new CallBackInterface() {
            @Override
            public void success(String json) {
                Gson gson = new GsonBuilder().create();
                UserResults userResults = gson.fromJson(json, UserResults.class);
                int status = userResults.getResults().get(0).getStatus();

                String wordEn = null;
                if (status == 1) {
                    String generic_name = userResults.getResults().get(0).getProduct().getGeneric_name();
                    if (generic_name == "" || generic_name == null) {
                        String product_name = userResults.getResults().get(0).getProduct().gatProduct_name();
                        //this.produit2 = this.removeAccents(product_name);
                    } else {
                        //this.produit2 = this.removeAccents(generic_name);
                    }

                }
            }

            @Override
            public void error() {

            }
        },barecode).execute();

    }














        // Redirection vers la page FRIDGE

        final ImageView fridge = (ImageView) findViewById(R.id.img_fridge);
        fridge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
            }
        });

        /*
        // Redirection vers la page MANUAL
        final ImageView fridge = (ImageView) findViewById(R.id.img_fridge);
        fridge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
            }
        });*/


        // Redirection vers la page SCANNER
        final ImageView barcode = (ImageView) findViewById(R.id.img_barcode);
        barcode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });

    }
}
