package isen.fr.myfridgerecipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Philippe on 22/03/2018.
 */

public class RecipesAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Recipes> list;

    //ajouter un constructeur prenant en entrée une liste
    public RecipesAdapter(List<Recipes> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_product,viewGroup,false);
        return new MyViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Recipes myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
