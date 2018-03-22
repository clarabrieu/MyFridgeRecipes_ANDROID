package isen.fr.myfridgerecipes;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FridgeActivityTask extends AsyncTask<String, Void, String>
{
    String codeBarre=null;
    private CallBackInterface callBackInterface;
    public FridgeActivityTask(CallBackInterface callBackInterfaceImplementation,String codeBarre){
        this.callBackInterface=callBackInterfaceImplementation;
        this.codeBarre=codeBarre;

    }

    @Override
    protected String doInBackground(String... strings) {
        String debutChaineProduit = "https://world.openfoodfacts.org/api/v0/product/";
        String finChaineProduit = ".json";
        String result = null;
        try {
            URL url = new URL(debutChaineProduit+this.codeBarre+finChaineProduit);
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                result= convertInputStreamToString(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
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
            Log.d("FridgeActivityTask", result);
        }
        if(result!=null && !result.isEmpty()){
            callBackInterface.success(result);
        }else{
            callBackInterface.error();
        }
    }
}
