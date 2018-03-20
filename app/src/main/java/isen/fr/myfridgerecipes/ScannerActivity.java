package isen.fr.myfridgerecipes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ScannerActivity extends AppCompatActivity {

    final String DONNEE = "json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scanner);

        final Activity activity = this;

        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan your barcode");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();



        // Redirection vers la page d'accueil
        final Button returnBtn = (Button) findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Product scanned : " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ScannerActivity.this, MainActivityTask.class);
                writeFile(result.getContents().toString());
                String data2=readJSON();
                intent.putExtra(DONNEE, data2);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String readJSON(){
        String data="";
        FileInputStream load;
        try {
            load=openFileInput("save.json");
            int size=load.available();
            byte [] buffer =new byte[size];
            load.read(buffer);
            data = new String(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }

    public JSONObject writeJSON(String barecode) {
        JSONObject save = new JSONObject();
        try {
            save.put("barecode",barecode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return save;
    }
    public void writeFile(String barecode) {
        FileOutputStream file;
        String save=writeJSON(barecode).toString();
        try {
            file=openFileOutput("save.json",MODE_PRIVATE);
            file.write(save.getBytes());
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
