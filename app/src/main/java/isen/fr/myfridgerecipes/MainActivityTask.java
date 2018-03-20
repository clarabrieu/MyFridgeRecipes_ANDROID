package isen.fr.myfridgerecipes;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by joris.lacroix on 20/03/2018.
 */

public class MainActivityTask extends AsyncTask<String, Void, String> {

    private CallBackInterface callBackInterface;
    final String DONNEE = "json";
    final String monBareCode;
    public MainActivityTask(CallBackInterface callBackInterfaceImplementation, String barecode){
        this.callBackInterface=callBackInterfaceImplementation;
        monBareCode=barecode;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        try {
            String debutChaineProduit = "https://world.openfoodfacts.org/api/v0/product/";
            String finChaineProduit = ".json";
            String ChaineProduit=debutChaineProduit+monBareCode+finChaineProduit;

            URL url = new URL(ChaineProduit);
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                result = convertInputStreamToString(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuffer=new StringBuilder("");
        String readLine = bufferedReader.readLine();
        while (readLine != null){
            stringBuffer.append(readLine);
            stringBuffer.append("\n");
            readLine=bufferedReader.readLine();
        }
        inputStream.close();
        return stringBuffer.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result!=null) {
            Log.d("webServiceTask", result);
        }
        if(result!=null && !result.isEmpty()){
            callBackInterface.success(result);
        }else{
            callBackInterface.error();
        }
    }
}
