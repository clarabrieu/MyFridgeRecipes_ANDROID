package isen.fr.myfridgerecipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Redirection vers la page FRIDGE
        final ImageView fridge = (ImageView) findViewById(R.id.img_fridge);
        fridge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
            }
        });

        // Redirection vers la page SCANNER
        final ImageView barcode = (ImageView) findViewById(R.id.img_barcode);
        barcode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });

        // Redirection vers la page MANUAL
        final ImageView manual = (ImageView) findViewById(R.id.img_manual);
        manual.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });

    }
}
