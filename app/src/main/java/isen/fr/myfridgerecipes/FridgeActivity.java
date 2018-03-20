package isen.fr.myfridgerecipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FridgeActivity extends AppCompatActivity {

    String productManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        //productManual = getIntent().getStringExtra(<StringName>);
    }
}
