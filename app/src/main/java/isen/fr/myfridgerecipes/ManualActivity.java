package isen.fr.myfridgerecipes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManualActivity extends AppCompatActivity {
    public ArrayList <String> productList;

    public class Item {
        boolean checked;
        Drawable ItemDrawable;
        String ItemString;
        Item(Drawable drawable, String t, boolean b){
            ItemDrawable = drawable;
            ItemString = t;
            checked = b;
        }

        public boolean isChecked(){
            return checked;
        }
    }

    static class ViewHolder {
        CheckBox checkBox;
        ImageView icon;
        TextView text;
    }

    public class ItemsListAdapter extends BaseAdapter {

        private Context context;
        private List<Item> list;

        ItemsListAdapter(Context c, List<Item> l) {
            context = c;
            list = l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public boolean isChecked(int position) {
            return list.get(position).checked;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            // reuse views
            ViewHolder viewHolder = new ViewHolder();
            if (rowView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.row, null);

                viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.rowCheckBox);
                viewHolder.icon = (ImageView) rowView.findViewById(R.id.rowImageView);
                viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) rowView.getTag();
            }

            viewHolder.icon.setImageDrawable(list.get(position).ItemDrawable);
            viewHolder.checkBox.setChecked(list.get(position).checked);

            final String itemStr = list.get(position).ItemString;
            viewHolder.text.setText(itemStr);

            viewHolder.checkBox.setTag(position);


            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean newState = !list.get(position).isChecked();
                    list.get(position).checked = newState;
                }
            });

            viewHolder.checkBox.setChecked(isChecked(position));

            return rowView;

        }
    }

    Button btnLookup;
    Button btnBarcode;
    Button btnManual;
    List<Item> items;
    ListView listView;
    ItemsListAdapter myItemsListAdapter;
    EditText input;
    String getInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        listView = (ListView)findViewById(R.id.listview);
        btnLookup = (Button)findViewById(R.id.lookup);
        btnBarcode = (Button)findViewById(R.id.btn_barcode);
        btnManual = (Button)findViewById(R.id.btn_manual);

        initItems();
        myItemsListAdapter = new ItemsListAdapter(this, items);
        listView.setAdapter(myItemsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ManualActivity.this, ((Item)(parent.getItemAtPosition(position))).ItemString, Toast.LENGTH_LONG).show();

            }});

        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "Product(s) added :";

                ArrayList<String> temp = getArrayList("ProductList");
                if (temp == null){
                    productList = new ArrayList<>();
                }
                else{
                    productList = temp;
                }


               for (int i=0; i<items.size(); i++){
                    if (items.get(i).isChecked()){
                        switch(i){
                            case 0:
                                str += "\nBanana";
                                if(!productList.contains("Banana")){
                                    productList.add("Banana");
                                }
                                break;
                            case 1:
                                str += "\nCarrot";
                                if(!productList.contains("Carrot")){
                                    productList.add("Carrot");
                                }
                                break;
                            case 2:
                                str += "\nChicken";
                                if(!productList.contains("Chicken")){
                                    productList.add("Chicken");
                                }
                                break;
                            case 3:
                                str += "\nCorn";
                                if(!productList.contains("Corn")){
                                    productList.add("Corn");
                                }
                                break;
                            case 4:
                                str += "\nEgg";
                                if(!productList.contains("Egg")){
                                    productList.add("Egg");
                                }
                                break;
                            case 5:
                                str += "\nLemon";
                                if(!productList.contains("Lemon")){
                                    productList.add("Lemon");
                                }
                                break;
                            case 6:
                                str += "\nMushroom";
                                if(!productList.contains("Mushroom")){
                                    productList.add("Mushroom");
                                }
                                break;
                            case 7:
                                str += "\nOnion";
                                if(!productList.contains("Onion")){
                                    productList.add("Onion");
                                }
                                break;
                            case 8:
                                str += "\nOyster";
                                if(!productList.contains("Oyster")){
                                    productList.add("Oyster");
                                }
                                break;
                            case 9:
                                str += "\nPineapple";
                                if(!productList.contains("Pineapple")){
                                    productList.add("Pineapple");
                                }
                                break;
                            case 10:
                                str += "\nPasta";
                                if(!productList.contains("Pasta")){
                                    productList.add("Pasta");
                                }
                                break;
                            case 11:
                                str += "\nTomato";
                                if(!productList.contains("Tomato")){
                                    productList.add("Tomato");
                                }
                                break;
                        }
                    }
                }

                Toast.makeText(ManualActivity.this,
                        str,
                        Toast.LENGTH_LONG).show();

                saveArrayList(productList,"ProductList");


            }
        });

    }

    public void dialogEventBarcode(View view){
        btnBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder diag = new AlertDialog.Builder(ManualActivity.this);
                input = new EditText(ManualActivity.this);
                diag.setView(input).setCancelable(false)
                        .setPositiveButton("Validate", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getInput = input.getText().toString();
                                Intent intent = new Intent(ManualActivity.this, FridgeActivity.class);
                                intent.putExtra("CODE_BARRE",  getInput);
                                startActivity(intent);
                                Toast.makeText(ManualActivity.this, getInput, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alert = diag.create();
                alert.setTitle("Enter the barcode");
                alert.show();
            }
        });

    }

    public void dialogEventProduct(View view){
        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder diag = new AlertDialog.Builder(ManualActivity.this);
                input = new EditText(ManualActivity.this);
                diag.setView(input).setCancelable(false)
                        .setPositiveButton("Validate", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getInput = input.getText().toString();
                                Toast.makeText(ManualActivity.this, getInput, Toast.LENGTH_SHORT).show();

                                ArrayList<String> temp = getArrayList("ProductList");
                                if (temp == null){
                                    productList = new ArrayList<>();
                                }
                                else{
                                    productList = temp;
                                }

                                productList.add(getInput);
                                saveArrayList(productList,"ProductList");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alert = diag.create();
                alert.setTitle("Enter the product");
                alert.show();
            }
        });

    }

    private void initItems(){
        items = new ArrayList<Item>();

        TypedArray arrayDrawable = getResources().obtainTypedArray(R.array.resicon);
        TypedArray arrayText = getResources().obtainTypedArray(R.array.restext);

        for(int i=0; i<arrayDrawable.length(); i++){
            Drawable d = arrayDrawable.getDrawable(i);
            String s = arrayText.getString(i);
            boolean b = false;
            Item item = new Item(d, s, b);
            items.add(item);
        }

        arrayDrawable.recycle();
        arrayText.recycle();
    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }


}